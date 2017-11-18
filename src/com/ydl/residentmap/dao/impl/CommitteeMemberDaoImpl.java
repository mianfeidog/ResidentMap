package com.ydl.residentmap.dao.impl;

import com.ydl.residentmap.constants.CommonConst;
import com.ydl.residentmap.constants.DataDictionaryCode;
import com.ydl.residentmap.dao.BaseDao;
import com.ydl.residentmap.dao.CommitteeMemberDao;
import com.ydl.residentmap.model.CommitteeMember;
import com.ydl.residentmap.model.vo.CommitteeMemberVo;
import com.ydl.residentmap.util.IdWorker;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Repository
public class CommitteeMemberDaoImpl implements CommitteeMemberDao {
    @Resource
    private BaseDao<CommitteeMember> baseDAO;

    @Resource
    private BaseDao<CommitteeMemberVo> baseVoDAO;

    private String commonSql="select t1.lat,t1.lng, t1.create_at createAt,  t1.id,t1.position,t1.name,t1.gender,t1.minority,t1.birthday, " +
            " t1.education,t1.join_date joinDate,t1.id_card idCard,t1.address,t1.link,t1.community_id communityId, " +
            " ifnull(t2.name,'') communityName,ifnull(t3.name,'') positionName,ifnull(t4.name,'') minorityName, " +
            " ifnull(t5.name,'') educationName " +
            " from committee_member t1 " +
            " left join community t2 on t1.community_id=t2.id " +
            " left join data_dictionary t3 on t1.position=t3.value and t3.data_type= " + DataDictionaryCode.DATA_TYPE_POSITION +
            " left join data_dictionary t4 on t1.minority=t4.value and t4.data_type= " +DataDictionaryCode.DATA_TYPE_MINORITY +
            " left join data_dictionary t5 on t1.education=t5.value and t5.data_type= "+DataDictionaryCode.DATA_TYPE_EDUCATION ;

    @Override
    public Boolean save(CommitteeMember committeeMember) {
        Boolean flag = true;
        try {
            baseDAO.save(committeeMember);
            System.out.println("添加社区大党委成员 OK   成员ID：" + committeeMember.getId());
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
            String hql="delete CommitteeMember as r where r.id in ("+String.join(",", idList)+")";
            cnt=baseDAO.executeHql(hql);
        } catch (Exception e) {
            e.printStackTrace();
            cnt = 0;
        }
        return cnt;
    }

    @Override
    public Boolean update(CommitteeMember committeeMember) {
        Boolean flag = true;
        try {
            baseDAO.update(committeeMember);
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    @Override
    public List<CommitteeMember> getAllCommitteeMembers() {
        String hql="from CommitteeMember ";
        return baseDAO.find(hql);
    }

    @Override
    public List<CommitteeMember> getCommitteeMembersByName(String name) {
        String hql="from CommitteeMember where name like ?";
        Object[] params = new Object[1];
        params[0] = "%"+name+"%";
        List<CommitteeMember> committeeMembers = baseDAO.find(hql, params);
        return committeeMembers;
    }

    @Override
    public List<CommitteeMember> getCommitteeMembersByCommunityId(Long communityId) {
        String hql="from CommitteeMember where communityId = ?";
        Object[] params = new Object[1];
        params[0] = communityId;
        List<CommitteeMember> committeeMembers = baseDAO.find(hql, params);
        return committeeMembers;
    }

    @Override
    public List<CommitteeMember> getCommitteeMembersByEducation(Integer education) {
        String hql="from CommitteeMember where education = ?";
        Object[] params = new Object[1];
        params[0] = education;
        List<CommitteeMember> committeeMembers = baseDAO.find(hql, params);
        return committeeMembers;
    }

    @Override
    public List<CommitteeMember> getCommitteeMembersByPosition(Integer position) {
        String hql="from CommitteeMember where position = ?";
        Object[] params = new Object[1];
        params[0] = position;
        List<CommitteeMember> committeeMembers = baseDAO.find(hql, params);
        return committeeMembers;
    }

    @Override
    public CommitteeMember getCommitteeMemberById(Long id) {
        return baseDAO.get(CommitteeMember.class, id);
    }

    @Override
    public List<CommitteeMemberVo> getAllCommitteeMemberVos() {
        CommitteeMemberVo committeeMemberVo = new CommitteeMemberVo();
        String hql=this.commonSql + " order by t1.create_at desc";
        Object[] params = new Object[0];
        List<CommitteeMemberVo> committeeMemberVos = baseVoDAO.getResultBySQL(hql,params,CommitteeMemberVo.class);
        return committeeMemberVos;
    }

    @Override
    public List<CommitteeMemberVo> getCommitteeMemberVosByName(String name) {
        CommitteeMemberVo committeeMemberVo = new CommitteeMemberVo();
        String hql=this.commonSql +" where t1.name like ? order by t1.create_at desc";
        Object[] params = new Object[1];
        params[0] = "%"+name+"%";
        List<CommitteeMemberVo> committeeMemberVos = baseVoDAO.getResultBySQL(hql,params,CommitteeMemberVo.class);
        return committeeMemberVos;
    }

    @Override
    public CommitteeMemberVo getCommitteeMemberVoById(Long id) {
        CommitteeMemberVo committeeMemberVo = new CommitteeMemberVo();
        String hql=this.commonSql+ " where t1.id="+id ;
        Object[] params = new Object[0];
        List<CommitteeMemberVo> committeeMemberVos = baseVoDAO.getResultBySQL(hql,params,CommitteeMemberVo.class);
        if(committeeMemberVos.size()>0){
            committeeMemberVo=committeeMemberVos.get(0);
        }
        return committeeMemberVo;
    }

    @Override
    public List<CommitteeMember> getCommitteeMemebersByIdCard(String idCard, String action,Long id) {
        String hql="from CommitteeMember where idCard = ?";
        if(CommonConst.ACTION_ADD.equals(action))
        {
        }
        else if(CommonConst.ACTION_EDIT.equals(action))
        {
            hql+=" and id<>"+id+"";
        }
        Object[] params = new Object[1];
        params[0] = idCard;
        List<CommitteeMember> committeeMembers = baseDAO.find(hql, params);
        return committeeMembers;
    }
}
