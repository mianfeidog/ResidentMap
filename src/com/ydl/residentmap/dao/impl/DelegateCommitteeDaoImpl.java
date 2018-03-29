package com.ydl.residentmap.dao.impl;

import com.ydl.residentmap.constants.DataDictionaryCode;
import com.ydl.residentmap.dao.BaseDao;
import com.ydl.residentmap.dao.DelegateCommitteeDao;
import com.ydl.residentmap.model.Cadre;
import com.ydl.residentmap.model.DelegateCommittee;
import com.ydl.residentmap.model.vo.DelegateCommitteeVo;
import com.ydl.residentmap.util.IdWorker;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Repository
public class DelegateCommitteeDaoImpl implements DelegateCommitteeDao {
    @Resource
    private BaseDao<DelegateCommittee> baseDAO;

    @Resource
    private BaseDao<DelegateCommitteeVo> baseVoDAO;

    private String commonSql="select IFNULL(t2.name,'') minorityName,IFNULL(t3.name,'') educationName, " +
        " IFNULL(t4.name,'') partyName,IFNULL(t5.name,'') communityName, " +
        " t1.lat,t1.lng,t1.id,t1.name,t1.gender,t1.minority,t1.birthday,t1.education, " +
        " t1.party,t1.appoint_post appointPost,t1.address,t1.link,t1.community_id communityId,t1.create_at createAt " +
        " from delegate_committee t1 " +
        " left join data_dictionary t2 on t1.minority=t2.value and t2.data_type= " + DataDictionaryCode.DATA_TYPE_MINORITY +
        " left join data_dictionary t3 on t1.education=t3.value and t3.data_type= " +DataDictionaryCode.DATA_TYPE_EDUCATION +
        " left join data_dictionary t4 on t1.party=t4.value and t4.data_type= " + DataDictionaryCode.DATA_TYPE_PARTY +
        " left join community t5 on t1.community_id=t5.id ";

    @Override
    public Boolean save(DelegateCommittee delegateCommittee) {
        Boolean flag = true;
        try {
            baseDAO.save(delegateCommittee);
            System.out.println("添加两代表一委员 OK   ID：" + delegateCommittee.getId());
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
            String hql="delete DelegateCommittee as r where r.id in ("+String.join(",", idList)+")";
            cnt=baseDAO.executeHql(hql);
        } catch (Exception e) {
            e.printStackTrace();
            cnt = 0;
        }
        return cnt;
    }

    @Override
    public Boolean update(DelegateCommittee delegateCommittee) {
        Boolean flag = true;
        try {
            baseDAO.update(delegateCommittee);
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    @Override
    public List<DelegateCommittee> getAllDelegateCommittees() {
        String hql="from DelegateCommittee";
        return baseDAO.find(hql);
    }

    @Override
    public List<DelegateCommittee> getDelegateCommitteesByName(String name) {
        String hql="from DelegateCommittee where name like ?";
        Object[] params = new Object[1];
        params[0] = "%"+name+"%";
        List<DelegateCommittee> delegateCommittees = baseDAO.find(hql, params);
        return delegateCommittees;
    }

    @Override
    public List<DelegateCommittee> getDelegateCommitteesByCommunityId(Long communityId) {
        String hql="from DelegateCommittee where communityId = ?";
        Object[] params = new Object[1];
        params[0] = communityId;
        List<DelegateCommittee> delegateCommittees = baseDAO.find(hql, params);
        return delegateCommittees;
    }

    @Override
    public List<DelegateCommittee> getDelegateCommitteesByMinority(Integer minority) {
        String hql="from DelegateCommittee where minority = ?";
        Object[] params = new Object[1];
        params[0] = minority;
        List<DelegateCommittee> delegateCommittees = baseDAO.find(hql, params);
        return delegateCommittees;
    }

    @Override
    public List<DelegateCommittee> getDelegateCommitteesByEducation(Integer education) {
        String hql="from DelegateCommittee where education = ?";
        Object[] params = new Object[1];
        params[0] = education;
        List<DelegateCommittee> delegateCommittees = baseDAO.find(hql, params);
        return delegateCommittees;
    }

    @Override
    public List<DelegateCommittee> getDelegateCommitteesByParty(Integer party) {
        String hql="from DelegateCommittee where party = ?";
        Object[] params = new Object[1];
        params[0] = party;
        List<DelegateCommittee> delegateCommittees = baseDAO.find(hql, params);
        return delegateCommittees;
    }

    @Override
    public DelegateCommittee getDelegateCommitteeById(Long id) {
        return baseDAO.get(DelegateCommittee.class, id);
    }

    @Override
    public List<DelegateCommitteeVo> getAllDelegateCommitteeVos() {
        String hql=this.commonSql + " order by t1.create_at desc ";
        List<DelegateCommitteeVo> delegateCommitteeVos = baseVoDAO.getResultBySQL(hql,new Object[]{},DelegateCommitteeVo.class);
        return delegateCommitteeVos;
    }

    @Override
    public List<DelegateCommitteeVo> getDelegateCommitteeVosByName(String name) {
        String hql=this.commonSql +" where t1.name like ? order by t1.create_at desc ";
        Object[] params = new Object[1];
        params[0] = "%"+name+"%";
        List<DelegateCommitteeVo> delegateCommitteeVos = baseVoDAO.getResultBySQL(hql,params,DelegateCommitteeVo.class);
        return delegateCommitteeVos;
    }

    @Override
    public List<DelegateCommitteeVo> getDelegateCommitteeVosByCondition(HashMap<String,String> map) {
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
        //民族
        if(map.containsKey("minority"))
        {
            String val = map.get("minority");
            addSql+=" and t1.minority="+val;
        }
        //党派
        if(map.containsKey("party"))
        {
            String val = map.get("party");
            addSql+=" and t1.party="+val;
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
        //家庭住址模糊
        if(map.containsKey("addressLike"))
        {
            addSql+=" and t1.address like ? ";
            String val = map.get("addressLike");
            paramList.add("%"+val+"%");
        }
        //联系电话模糊
        if(map.containsKey("linkLike"))
        {
            addSql+=" and t1.link like ? ";
            String val = map.get("linkLike");
            paramList.add("%"+val+"%");
        }
        //文化程度
        if(map.containsKey("education"))
        {
            String val = map.get("education");
            addSql += " and t1.education="+val ;
        }
        //何届省、市、区党代表或人大代表或政协委员
        if(map.containsKey("appointPostLike"))
        {
            addSql+=" and t1.appoint_post like ? ";
            String val = map.get("appointPostLike");
            paramList.add("%"+val+"%");
        }
        //所属社区
        if(map.containsKey("communityId"))
        {
            String val = map.get("communityId");
            addSql+= " and t1.community_id="+val;
        }

        String hql=this.commonSql + " where 1=1 " + addSql + " order by t1.create_at desc";
        Object[] params = paramList.toArray();
        List<DelegateCommitteeVo> delegateCommitteeVos = baseVoDAO.getResultBySQL(hql,params,DelegateCommitteeVo.class);
        return delegateCommitteeVos;
    }

    @Override
    public DelegateCommitteeVo getDelegateCommitteeVoById(Long id) {
        String hql=this.commonSql + " where t1.id="+id;
        List<DelegateCommitteeVo> delegateCommitteeVos = baseVoDAO.getResultBySQL(hql,new Object[]{},DelegateCommitteeVo.class);
        DelegateCommitteeVo delegateCommitteeVo = new DelegateCommitteeVo();
        if(delegateCommitteeVos.size()>0){
            delegateCommitteeVo=delegateCommitteeVos.get(0);
        }
        return delegateCommitteeVo;
    }
}
