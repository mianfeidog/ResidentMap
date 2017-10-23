package com.ydl.residentmap.dao.impl;

import com.ydl.residentmap.constants.CommonConst;
import com.ydl.residentmap.constants.DataDictionaryCode;
import com.ydl.residentmap.dao.BaseDao;
import com.ydl.residentmap.dao.KeyPersonDao;
import com.ydl.residentmap.model.KeyPerson;
import com.ydl.residentmap.model.Pager;
import com.ydl.residentmap.model.vo.KeyPersonVo;
import com.ydl.residentmap.util.IdWorker;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Repository
public class KeyPersonDaoImpl implements KeyPersonDao{


    @Resource
    private BaseDao<KeyPerson> baseDAO;

    @Resource
    private BaseDao<KeyPersonVo> baseVoDAO;

    private String commonSql="select t1.id,t1.name,t1.gender,t1.age,t1.type,t1.base_condition baseCondition,t1.id_card idCard," +
            " t1.address,t1.link,t1.block_id blockId,t1.building_id buildingId," +
            " ifnull(t2.name,'') streetName,ifnull(t6.name,'') typeName, " +
            " t3.name communityName,t4.name blockName,t5.name buildingName,t1.create_at createAt, t1.lng,t1.lat "+
            " from key_person t1 " +
            " left join data_dictionary t6 on t1.type=t6.value and t6.data_type="+ DataDictionaryCode.DATA_TYPE_KEY_PERSON +
            " left join block t4 on t1.block_id=t4.id " +
            " left join community t3 on t4.community_id=t3.id " +
            " left join street t2 on t3.street_id=t2.id " +
            " left join building t5 on t1.building_id=t5.id ";

    @Override
    public Boolean save(KeyPerson keyPerson) {
        Boolean flag = true;
        try {
            baseDAO.save(keyPerson);
            System.out.println("添加重点人员 OK   重点人员ID："+keyPerson.getId());
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    @Override
    public Integer deleteList(List<String> idList) {
        Integer cnt = 0;
        try {
            String hql="delete KeyPerson as r where r.id in ("+String.join(",", idList)+")";
            cnt=baseDAO.executeHql(hql);
        } catch (Exception e) {
            e.printStackTrace();
            cnt = 0;
        }
        return cnt;
    }

    @Override
    public Boolean update(KeyPerson keyPerson) {
        Boolean flag = true;
        try {
            baseDAO.update(keyPerson);
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    @Override
    public KeyPersonVo getKeyPersonVoById(Long id) {
        String hql=this.commonSql + " where t1.id="+id;
        List<KeyPersonVo> keyPersons = baseVoDAO.getResultBySQL(hql,new Object[]{},KeyPersonVo.class);
        KeyPersonVo keyPersonVo = new KeyPersonVo();
        if(keyPersons.size()>0){
            keyPersonVo=keyPersons.get(0);
        }
        return keyPersonVo;
    }

    @Override
    public KeyPerson getKeyPersonById(Long id) {
        return baseDAO.get(KeyPerson.class, id);
    }

    @Override
    public List<KeyPerson> getAllKeyPersons() {
        String hql="from KeyPerson";
        return baseDAO.find(hql);
    }

    @Override
    public List<KeyPersonVo> getAllKeyPersonVos() {
        String hql=this.commonSql + " order by t1.create_at desc ";
        List<KeyPersonVo> keyPersons = baseVoDAO.getResultBySQL(hql,new Object[]{},KeyPersonVo.class);
        return keyPersons;
    }

    @Override
    public List<KeyPerson> getKeyPersonsByName(String name) {
        String hql="from KeyPerson where name like ?";
        Object[] params = new Object[1];
        params[0] = "%"+name+"%";
        List<KeyPerson> keyPersons = baseDAO.find(hql, params);
        return keyPersons;
    }

    @Override
    public List<KeyPerson> getKeyPersonsByIdCard(String idCard,String action,Long id)
    {
        String hql="from KeyPerson where idCard = ?";
        if(CommonConst.ACTION_ADD.equals(action))
        {}
        else if(CommonConst.ACTION_EDIT.equals(action))
        {
            hql+= " and id<>"+id;
        }
        Object[] params = new Object[1];
        params[0] = idCard;
        List<KeyPerson> keyPersons = baseDAO.find(hql, params);
        return keyPersons;
    }

    @Override
    public List<KeyPerson> getKeyPersonsByType(int type) {
        String hql="from KeyPerson where type = "+type+"";
        return baseDAO.find(hql);
    }

    @Override
    public List<KeyPersonVo> getKeyPersonVosByName(String name) {
        String hql=this.commonSql +" where t1.name like ? order by t1.create_at desc ";
        Object[] params = new Object[1];
        params[0] = "%"+name+"%";
        List<KeyPersonVo> keyPersons = baseVoDAO.getResultBySQL(hql,params,KeyPersonVo.class);
        return keyPersons;
    }

    @Override
    public List<KeyPersonVo> getKeyPersonVosByType(int type) {
        String hql=this.commonSql + " where t1.type="+type + " order by t1.create_at desc ";
        List<KeyPersonVo> keyPersons = baseVoDAO.getResultBySQL(hql,new Object[]{},KeyPersonVo.class);
        return keyPersons;
    }

    @Override
    public List<KeyPersonVo> getKeyPersonVosByTypesName(List<String> typeList,String name) {
        String typeStr = String.join(",",typeList);
        String hql=this.commonSql + " where t1.type in ("+typeStr+") and t1.name like ? order by t1.create_at desc";
        Object[] params = new Object[1];
        params[0] = "%"+name+"%";
        List<KeyPersonVo> keyPersons = baseVoDAO.getResultBySQL(hql,params,KeyPersonVo.class);
        return keyPersons;
    }

    @Override
    public List<KeyPersonVo> getKeyPersonVosByTypes(List<String> typeList) {
        String typeStr = String.join(",",typeList);
        String hql=this.commonSql + " where t1.type in ("+typeStr+") order by t1.create_at desc ";
        List<KeyPersonVo> keyPersons = baseVoDAO.getResultBySQL(hql,new Object[]{},KeyPersonVo.class);
        return keyPersons;
    }

    @Override
    public Pager<KeyPerson> getKeyPersonsByPage(int offset, int size) {
        //查询共多少数据
        String totalSql = "select count(*) from Resident";
        Long total = baseDAO.count(totalSql);
        String dateHql = "from Resident";
        List<KeyPerson> residentList = baseDAO.find(dateHql, new Object[]{}, offset, size);
        Pager<KeyPerson> pager = new Pager<KeyPerson>();
        pager.setOffset(offset);
        pager.setSize(size);
        pager.setTotal(total);
        pager.setData(residentList);
        return pager;
    }

    @Override
    public List<KeyPersonVo> getKeyPersonVosByPage(int offset, int size) {
        String hql="select t1.id,t1.name,t1.gender,t1.age,t1.type,t1.base_condition baseCondition,t1.id_card idCard," +
                " t1.address,t1.link,t1.block_id blockId,ifnull(t2.name,'') blockName,ifnull(t3.name,'') typeName " +
                " from key_person t1 " +
                " left join block t2 on t1.block_id=t2.id " +
                " left join data_dictionary t3 on t1.type=t3.value and t3.data_type="+ DataDictionaryCode.DATA_TYPE_KEY_PERSON +
                " limit " + offset + ","+size;
        List<KeyPersonVo> keyPersons = baseVoDAO.getResultBySQL(hql,new Object[]{},KeyPersonVo.class);
        return keyPersons;
    }
}
