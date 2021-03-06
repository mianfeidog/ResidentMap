package com.ydl.residentmap.dao.impl;

import com.ydl.residentmap.constants.DataDictionaryCode;
import com.ydl.residentmap.dao.AssistResidentDao;
import com.ydl.residentmap.dao.BaseDao;
import com.ydl.residentmap.model.AssistResident;
import com.ydl.residentmap.model.vo.AssistResidentVo;
import com.ydl.residentmap.util.IdWorker;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class AssistResidentDaoImpl implements AssistResidentDao {

    private String commonSql="select t1.create_at createAt, t1.lng,t1.lat, t1.id,t1.name,t1.type,t1.gender,t1.birthday,t1.family_member_count familyMemberCount, " +
    " t1.family_year_income familyYearIncome,t1.deformity_card_rank deformityCardRank, " +
    " t1.deformity_certificate_num deformityCertificateNum, " +
    " t1.receive_policy_standard receivePolicyStandard,t1.address,t1.link, " +
    " t1.block_id blockId,t1.building_id buildingId,t1.lng,t1.lat, " +
    " ifnull(t2.name,'') typeName, " +
    " ifnull(t4.name,'') streetName,ifnull(t5.name,'') communityName,ifnull(t6.name,'') blockName,ifnull(t7.name,'') buildingName " +
    " from assist_resident t1 " +
    " left join data_dictionary t2 on t1.type=t2.value and t2.data_type= " + DataDictionaryCode.DATA_TYPE_ASSIST_RESIDENT +
    " left join block t6 on t1.block_id=t6.id " +
    " left join community t5 on t6.community_id=t5.id " +
    " left join street t4 on t5.street_id=t4.id " +
    " left join building t7 on t1.building_id=t7.id ";

    @Resource
    private BaseDao<AssistResident> baseDAO;

    @Resource
    private BaseDao<AssistResidentVo> baseVoDAO;

    @Override
    public Boolean save(AssistResident assistResident) {
        Boolean flag = true;
        try {
            baseDAO.save(assistResident);
            System.out.println("添加帮扶人员 OK   ID："+assistResident.getId());
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
            String hql="delete AssistResident as r where r.id in ("+String.join(",", idList)+")";
            cnt=baseDAO.executeHql(hql);
        } catch (Exception e) {
            e.printStackTrace();
            cnt = 0;
        }
        return cnt;
    }

    @Override
    public Boolean update(AssistResident assistResident) {
        Boolean flag = true;
        try {
            baseDAO.update(assistResident);
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    @Override
    public AssistResident getAssistResidentById(Long id) {
        return baseDAO.get(AssistResident.class, id);
    }

    @Override
    public List<AssistResidentVo> getAllAssistResidentVos() {
        String hql=this.commonSql + " order by t1.create_at desc ";
        List<AssistResidentVo> assistResidentVos = baseVoDAO.getResultBySQL(hql,new Object[]{},AssistResidentVo.class);
        return assistResidentVos;
    }

    @Override
    public List<AssistResidentVo> getAssistResidentVosByName(String name) {
        String hql=this.commonSql +" where t1.name like ? order by t1.create_at desc ";
        Object[] params = new Object[1];
        params[0] = "%"+name+"%";
        List<AssistResidentVo> assistResidentVos = baseVoDAO.getResultBySQL(hql,params,AssistResidentVo.class);
        return assistResidentVos;
    }

    @Override
    public List<AssistResidentVo> getAssistResidentVosByCondition(HashMap<String,String> map) {
        String addSql = "";
        List<String> paramList = new ArrayList<String>();
        //名称模糊
        if(map.containsKey("nameLike"))
        {
            addSql+=" and t1.name like ? ";
            String val = map.get("nameLike");
            paramList.add("%"+val+"%");
        }
        //性别
        if(map.containsKey("gender"))
        {
            String val = map.get("gender");
            addSql+=" and t1.gender="+val;
        }
        //残疾证等级
        if(map.containsKey("deformityCardRankLike"))
        {
            addSql+=" and t1.deformity_card_rank like ? ";
            String val = map.get("deformityCardRankLike");
            paramList.add("%"+val+"%");
        }
        //残疾证号码
        if(map.containsKey("deformityCertificateNumLike"))
        {
            addSql+=" and t1.deformity_certificate_num like ? ";
            String val = map.get("deformityCertificateNumLike");
            paramList.add("%"+val+"%");
        }
        //出生年月起始
        if(map.containsKey("birthdayBegin"))
        {
            String val = map.get("birthdayBegin");
            addSql += " and t1.birthday>="+val;
        }
        //出生年月截止
        if(map.containsKey("birthdayEnd"))
        {
            String val = map.get("birthdayEnd");
            addSql += " and t1.birthday<="+val;
        }
        //家庭年收入起始
        if(map.containsKey("familyYearIncomeBegin"))
        {
            String val = map.get("familyYearIncomeBegin");
            addSql += " and t1.family_year_income>="+val;
        }
        //家庭年收入截止
        if(map.containsKey("familyYearIncomeEnd"))
        {
            String val = map.get("familyYearIncomeEnd");
            addSql += " and t1.family_year_income<="+val;
        }
        //家庭人口起始
        if(map.containsKey("familyMemberCountBegin"))
        {
            String val = map.get("familyMemberCountBegin");
            addSql += " and t1.family_member_count>="+val;
        }
        //家庭人口截止
        if(map.containsKey("familyMemberCountEnd"))
        {
            String val = map.get("familyMemberCountEnd");
            addSql += " and t1.family_member_count<="+val;
        }
        //家庭住址模糊
        if(map.containsKey("addressLike"))
        {
            addSql+=" and t1.address like ? ";
            String val = map.get("addressLike");
            paramList.add("%"+val+"%");
        }
        //所属小区
        if(map.containsKey("blockId"))
        {
            String val = map.get("blockId");
            addSql+= " and t1.block_id="+val;
        }
        //居民类型
        if(map.containsKey("type"))
        {
            String val = map.get("type");
            addSql+= " and t1.type="+val;
        }

        String hql=this.commonSql + " where 1=1 " + addSql + " order by t1.create_at desc";
        Object[] params = paramList.toArray();
        List<AssistResidentVo> assistResidentVos = baseVoDAO.getResultBySQL(hql,params,AssistResidentVo.class);
        return assistResidentVos;
    }

    @Override
    public List<AssistResidentVo> getAssistResidentVosByTypes(List<String> types) {
        String hql=this.commonSql + " where t1.type in ("+String.join(",",types)+") order by t1.create_at desc";
        List<AssistResidentVo> assistResidentVos = baseVoDAO.getResultBySQL(hql,new Object[]{},AssistResidentVo.class);
        return assistResidentVos;
    }

    @Override
    public List<AssistResidentVo> getAssistResidentVosByTypesName(List<String> types,String name) {
        String hql=this.commonSql + " where t1.type in ("+String.join(",",types)+") and t1.name like ? order by t1.create_at desc";
        Object[] params = new Object[1];
        params[0] = "%"+name+"%";
        List<AssistResidentVo> assistResidentVos = baseVoDAO.getResultBySQL(hql,params,AssistResidentVo.class);
        return assistResidentVos;
    }

    @Override
    public AssistResidentVo getAssistResidentVoById(Long id) {
        String hql=this.commonSql + " where t1.id = " +id;
        List<AssistResidentVo> assistResidentVos = baseVoDAO.getResultBySQL(hql,new Object[]{},AssistResidentVo.class);
        AssistResidentVo assistResidentVo  = new AssistResidentVo();
        if(assistResidentVos.size()>0){
            assistResidentVo=assistResidentVos.get(0);
        }
        return assistResidentVo;
    }

    @Override
    public List<AssistResident> getAllAssistResidents() {
        String hql="from AssistResident";
        return baseDAO.find(hql);
    }

    @Override
    public List<AssistResident> getAssistResidentsByName(String name) {
        String hql="from AssistResident where name like ?";
        Object[] params = new Object[1];
        params[0] = "%"+name+"%";
        List<AssistResident> assistResidents = baseDAO.find(hql, params);
        return assistResidents;
    }

    @Override
    public List<AssistResident> getAssistResidentsByBlockId(Long blockId) {
        String hql="from AssistResident where blockId = ?";
        Object[] params = new Object[1];
        params[0] = blockId;
        List<AssistResident> assistResidents = baseDAO.find(hql, params);
        return assistResidents;
    }

    @Override
    public List<AssistResident> getAssistResidentsByType(Integer type) {
        String hql="from AssistResident where type = ?";
        Object[] params = new Object[1];
        params[0] = type;
        List<AssistResident> assistResidents = baseDAO.find(hql, params);
        return assistResidents;
    }

    @Override
    public List<AssistResident> getAssistResidentsByTypes(List<String> types) {
        String hql="from AssistResident where type in("+String.join(",",types)+")";
        List<AssistResident> assistResidents = baseDAO.find(hql);
        return assistResidents;
    }
}
