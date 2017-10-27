package com.ydl.residentmap.dao.impl;

import com.ydl.residentmap.constants.CommonConst;
import com.ydl.residentmap.constants.DataDictionaryCode;
import com.ydl.residentmap.dao.BaseDao;
import com.ydl.residentmap.dao.CadreDao;
import com.ydl.residentmap.model.Cadre;
import com.ydl.residentmap.model.vo.CadreVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class CadreDaoImpl implements CadreDao {
    @Resource
    private BaseDao<Cadre> baseDAO;

    @Resource
    private BaseDao<CadreVo> baseVoDAO;

    private String commonSql="select IFNULL(t2.name,'') positionName,IFNULL(t3.name,'') educationName,IFNULL(t4.name,'') communityName, " +
        " t1.id,t1.name,t1.position,t1.position_begin_date positionBeginDate,t1.gender, " +
        " t1.birthday,t1.education,t1.join_date joinDate,t1.id_card idCard,t1.address,t1.link,t1.community_id communityId " +
        " from cadre t1 " +
        " left join data_dictionary t2 on t1.position=t2.value and t2.data_type= " + DataDictionaryCode.DATA_TYPE_POSITION +
        " left join data_dictionary t3 on t1.education=t3.value and t3.data_type= " + DataDictionaryCode.DATA_TYPE_EDUCATION +
        " left join community t4 on t1.community_id=t4.id ";

    @Override
    public Boolean save(Cadre cadre) {
        Boolean flag = true;
        try {
            baseDAO.save(cadre);
            System.out.println("添加社区干部 OK   干部ID：" + cadre.getId());
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
            String hql="delete Cadre as r where r.id in ("+String.join(",", idList)+")";
            cnt=baseDAO.executeHql(hql);
        } catch (Exception e) {
            e.printStackTrace();
            cnt = 0;
        }
        return cnt;
    }

    @Override
    public Boolean update(Cadre cadre) {
        Boolean flag = true;
        try {
            baseDAO.update(cadre);
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    @Override
    public List<Cadre> getAllCadres() {
        String hql="from Cadre";
        return baseDAO.find(hql);
    }

    @Override
    public List<Cadre> getCadresByName(String name) {
        String hql="from Cadre where name like ?";
        Object[] params = new Object[1];
        params[0] = "%"+name+"%";
        List<Cadre> cadres = baseDAO.find(hql, params);
        return cadres;
    }

    @Override
    public List<Cadre> getCadresByIdCard(String idCard,String action,Long id)
    {
        String hql="from Cadre where idCard = ?";
        if(CommonConst.ACTION_ADD.equals(action))
        {}
        else if(CommonConst.ACTION_EDIT.equals(action))
        {
            hql+= " and id<>"+id;
        }
        Object[] params = new Object[1];
        params[0] = idCard;
        List<Cadre> cadres = baseDAO.find(hql, params);
        return cadres;
    }

    @Override
    public Cadre getCadreById(Long id) {
        return baseDAO.get(Cadre.class, id);
    }

    @Override
    public List<CadreVo> getAllCadreVos() {
        String hql=this.commonSql + " order by t1.create_at desc ";
        List<CadreVo> cadreVos = baseVoDAO.getResultBySQL(hql,new Object[]{},CadreVo.class);
        return cadreVos;
    }

    @Override
    public List<CadreVo> getCadreVosByName(String name) {
        String hql=this.commonSql +" where t1.name like ? order by t1.create_at desc ";
        Object[] params = new Object[1];
        params[0] = "%"+name+"%";
        List<CadreVo> cadreVos = baseVoDAO.getResultBySQL(hql,params,CadreVo.class);
        return cadreVos;
    }

    @Override
    public CadreVo getCadreVoById(Long id) {
        String hql=this.commonSql + " where t1.id="+id;
        List<CadreVo> cadreVos = baseVoDAO.getResultBySQL(hql,new Object[]{},CadreVo.class);
        CadreVo cadreVo = new CadreVo();
        if(cadreVos.size()>0){
            cadreVo=cadreVos.get(0);
        }
        return cadreVo;
    }
}
