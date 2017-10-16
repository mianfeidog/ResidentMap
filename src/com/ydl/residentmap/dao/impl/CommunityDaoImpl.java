package com.ydl.residentmap.dao.impl;

import com.ydl.residentmap.constants.DataDictionaryCode;
import com.ydl.residentmap.dao.BaseDao;
import com.ydl.residentmap.dao.CommunityDao;
import com.ydl.residentmap.model.Community;
import com.ydl.residentmap.model.Pager;
import com.ydl.residentmap.model.vo.BlockVo;
import com.ydl.residentmap.model.vo.CommunityVo;
import com.ydl.residentmap.util.IdWorker;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Repository
public class CommunityDaoImpl implements CommunityDao {

    @Resource
    private BaseDao<Community> baseDAO;

    @Resource
    private BaseDao<CommunityVo> baseVoDAO;

    @Resource
    private BaseDao<BlockVo> baseBlockVoDAO;

    private String commonSql="select t1.create_at createAt, t1.map_range mapRange, t1.id,t1.name,t1.area,t1.block_count blockCount,t1.family_count familyCount,t1.people_count peopleCount,t1.resident_family_count residentFamilyCount, " +
            " t1.resident_people_count residentPeopleCount,t1.temporary_family_count temporaryFamilyCount,t1.temporary_people_count temporaryPeopleCount,t1.income_people_count incomePeopleCount," +
            " t1.out_people_count outPeopleCount,t1.party_member_count partyMemberCount,t1.income_party_member_count incomePartyMemberCount,t1.out_party_member_count outPartyMemberCount,t1.childbearing_count childbearingCount," +
            " t1.deformity_count deformityCount,t1.allowances_family_count allowancesFamilyCount,t1.low_income_family_count lowIncomeFamilyCount,t1.old_people_count oldPeopleCount," +
            " t1.medical_insurance_count medicalInsuranceCount ,t1.old_insurance_count oldInsuranceCount,t1.key_person_count keyPersonCount,t1.grid_manager_count gridManagerCount," +
            " t1.water_reform_family_count waterReformFamilyCount,t1.electricity_reform_family_count electricityReformFamilyCount,t1.heating_supply_family_count heatingSupplyFamilyCount," +
            " t1.gas_family_count gasFamilyCount,t1.security_system_count securitySystemCount,t1.camera_count cameraCount,t1.secretary_name secretaryName,t1.director_name directorName," +
            " t1.in_charge_leader_name inChargeLeaderName,t1.work_day_leader_name workDayLeaderName,t1.secretary_tel secretaryTel,t1.director_tel directorTel,t1.in_charge_leader_tel inChargeLeaderTel," +
            " t1.work_day_leader_tel workDayLeaderTel,t1.lng,t1.lat,t1.description,t1.street_id streetId,t1.type,ifnull(t2.name,'') streetName,ifnull(t3.name,'') typeName " +
            " from community t1 " +
            " left join street t2 on t1.street_id=t2.id " +
            " left join data_dictionary t3 on t1.type=t3.value and t3.data_type="+DataDictionaryCode.DATA_TYPE_COMMUNITY ;

    @Override
    public Boolean save(Community community) {
        Boolean flag = true;
        try {

            //创建时间
            Date now = new Date();
            String sdate=(new SimpleDateFormat("yyyyMMddHHmm")).format(now);
            Long dateLong = Long.parseLong(sdate);
            community.setCreateAt(dateLong);

            Random random = new Random();
            community.setId(new IdWorker((long)random.nextInt(15)).nextId());
            baseDAO.save(community);
            System.out.println("添加社区 OK   社区ID："+community.getId());
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
            String hql="delete Community as r where r.id in ("+String.join(",", idList)+")";
            cnt=baseDAO.executeHql(hql);
        } catch (Exception e) {
            e.printStackTrace();
            cnt = 0;
        }
        return cnt;
    }

    @Override
    public Boolean update(Community community) {
        Boolean flag = true;
        try {
            baseDAO.update(community);
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    @Override
    public Community getCommunityById(Long id) {
        return baseDAO.get(Community.class, id);
    }

    @Override
    public List<Community> getAllCommunities() {
        String hql="from Community";
        return baseDAO.find(hql);
    }

    @Override
    public List<Community> getCommunitiesByName(String name) {
        String hql="from Community where name like ?";
        Object[] params = new Object[1];
        params[0] = "%"+name+"%";
        List<Community> communities = baseDAO.find(hql, params);
        return communities;
    }

    @Override
    public CommunityVo getCommunityVoById(Long id) {
        CommunityVo communityVo = new CommunityVo();
        String hql= this.commonSql + " where t1.id="+id;
        List<CommunityVo> communityVoList = baseVoDAO.getResultBySQL(hql,new Object[]{},CommunityVo.class);
        if(communityVoList.size()>0) {
            communityVo=communityVoList.get(0);
        }
        return communityVo;
    }

    @Override
    public List<CommunityVo> getAllCommunityVos() {
        CommunityVo communityVo = new CommunityVo();
        String hql=this.commonSql + " order by t1.create_at desc ";
        List<CommunityVo> communityVoList = baseVoDAO.getResultBySQL(hql,new Object[]{},CommunityVo.class);
        return communityVoList;

    }

    @Override
    public List<CommunityVo> getCommunityVosByName(String name) {
        CommunityVo communityVo = new CommunityVo();
        String hql=this.commonSql + " where t1.name like ? order by t1.create_at desc";
        Object[] params = new Object[1];
        params[0] = "%"+name+"%";
        List<CommunityVo> communityVoList = baseVoDAO.getResultBySQL(hql,params,CommunityVo.class);
        return communityVoList;
    }

    @Override
    public Pager<Community> getCommunitiesByPage(int offset, int size) {
        //查询共多少数据
        String totalSql = "select count(*) from Community";
        Long total = baseDAO.count(totalSql);
        String dateHql = "from Community";
        List<Community> communityList = baseDAO.find(dateHql, new Object[]{}, offset, size);
        Pager<Community> pager = new Pager<Community>();
        pager.setOffset(offset);
        pager.setSize(size);
        pager.setTotal(total);
        pager.setData(communityList);
        return pager;
    }
}
