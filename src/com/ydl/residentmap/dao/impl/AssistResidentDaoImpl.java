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
import java.util.Date;
import java.util.List;
import java.util.Random;

@Repository
public class AssistResidentDaoImpl implements AssistResidentDao {

    private String commonSql="select t1.create_at createAt, t1.lng,t1.lat, t1.id,t1.name,t1.type,t1.gender,t1.birthday,t1.family_member_count familyMemberCount, " +
    " t1.family_year_income familyYearIncome,t1.deformity_card_rank deformityCardRank, " +
    " t1.deformity_certificate_num deformityCertificateNum, " +
    " t1.receive_policy_standard receivePolicyStandard,t1.address,t1.link, " +
    " t1.block_id blockId,t1.building_id buildingId,t1.lng,t1.lat, " +
    " ifnull(t2.name,'') typeName,ifnull(t3.name,'') deformityCardRankName, " +
    " t4.name streetName,t5.name communityName,t6.name blockName,t7.name buildingName " +
    " from assist_resident t1 " +
    " left join data_dictionary t2 on t1.type=t2.value and t2.data_type= " + DataDictionaryCode.DATA_TYPE_ASSIST_RESIDENT +
    " left join data_dictionary t3 on t1.deformity_card_rank=t3.value and t3.data_type= " + DataDictionaryCode.DATA_TYPE_DEFORMITY_CARD_RANK +
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
    public List<AssistResident> getAssistResidentsByTypes(List<String> types) {
        String hql="from AssistResident where type in("+String.join(",",types)+")";
        List<AssistResident> assistResidents = baseDAO.find(hql);
        return assistResidents;
    }
}
