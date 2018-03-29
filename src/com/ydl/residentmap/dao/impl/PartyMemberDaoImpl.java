package com.ydl.residentmap.dao.impl;

import com.ydl.residentmap.constants.CommonConst;
import com.ydl.residentmap.constants.DataDictionaryCode;
import com.ydl.residentmap.dao.BaseDao;
import com.ydl.residentmap.dao.PartyMemberDao;
import com.ydl.residentmap.model.PartyMember;
import com.ydl.residentmap.model.vo.PartyMemberVo;
import com.ydl.residentmap.util.IdWorker;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class PartyMemberDaoImpl implements PartyMemberDao {

    @Resource
    private BaseDao<PartyMember> baseDAO;

    @Resource
    private BaseDao<PartyMemberVo> baseVoDAO;

    private String commonSql="select t1.create_at createAt, ifnull(t4.name,'') minorityName,ifnull(t5.name,'') educationName,ifnull(t6.name,'') claimPostName, " +
            " t1.lat,t1.lng, t1.id,t1.name,t1.gender,t1.minority,t1.birthday, " +
            " t1.education,t1.join_date joinDate,t1.claim_post claimPost,t1.id_card idCard,t1.address,t1.link, " +
            " t1.difficult,t1.street_id streetId,t1.community_id communityId,t1.block_id blockId,t1.building_id buildingId, " +
            " ifnull(t2.name,'') streetName,ifnull(t3.name,'') communityName " +
            " from party_member t1 " +
            " left join community t3 on t1.community_id=t3.id " +
            " left join street t2 on t3.street_id=t2.id " +
            " left join data_dictionary t4 on t1.minority=t4.value and t4.data_type="+ DataDictionaryCode.DATA_TYPE_MINORITY +
            " left join data_dictionary t5 on t1.education=t5.value and t5.data_type="+DataDictionaryCode.DATA_TYPE_EDUCATION +
            " left join data_dictionary t6 on t1.claim_post=t6.value and t6.data_type="+DataDictionaryCode.DATA_TYPE_POST;

    @Override
    public Boolean save(PartyMember partyMember) {
        Boolean flag = true;
        try {
            baseDAO.save(partyMember);
            System.out.println("添加党员 OK   党员ID：" + partyMember.getId());
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
            String hql="delete PartyMember as r where r.id in ("+String.join(",", idList)+")";
            cnt=baseDAO.executeHql(hql);
        } catch (Exception e) {
            e.printStackTrace();
            cnt = 0;
        }
        return cnt;
    }

    @Override
    public Boolean update(PartyMember partyMember) {
        Boolean flag = true;
        try {
            baseDAO.update(partyMember);
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    @Override
    public List<PartyMember> getAllPartyMembers() {
        String hql="from PartyMember";
        return baseDAO.find(hql);
    }

    @Override
    public List<PartyMember> getPartyMembersByName(String name) {
        String hql="from PartyMember where name like ?";
        Object[] params = new Object[1];
        params[0] = "%"+name+"%";
        List<PartyMember> partyMembers = baseDAO.find(hql, params);
        return partyMembers;
    }

    @Override
    public List<PartyMember> getPartyMembersByCommunityId(Long communityId) {
        String hql="from PartyMember where communityId = ?";
        Object[] params = new Object[1];
        params[0] = communityId;
        List<PartyMember> partyMembers = baseDAO.find(hql, params);
        return partyMembers;
    }

    @Override
    public List<PartyMember> getPartyMembersByMinority(Integer minority) {
        String hql="from PartyMember where minority = ?";
        Object[] params = new Object[1];
        params[0] = minority;
        List<PartyMember> partyMembers = baseDAO.find(hql, params);
        return partyMembers;
    }

    @Override
    public List<PartyMember> getPartyMembersByEducation(Integer education) {
        String hql="from PartyMember where education = ?";
        Object[] params = new Object[1];
        params[0] = education;
        List<PartyMember> partyMembers = baseDAO.find(hql, params);
        return partyMembers;
    }

    @Override
    public List<PartyMember> getPartyMembersByPost(Integer post) {
        String hql="from PartyMember where claimPost = ?";
        Object[] params = new Object[1];
        params[0] = post;
        List<PartyMember> partyMembers = baseDAO.find(hql, params);
        return partyMembers;
    }

    @Override
    public PartyMember getPartyMemberById(Long id) {
        return baseDAO.get(PartyMember.class, id);
    }

    @Override
    public List<PartyMemberVo> getAllPartyMemberVos() {
        PartyMemberVo partyMemberVo=new PartyMemberVo();
        String hql=this.commonSql + " order by t1.create_at desc";
        List<PartyMemberVo> partyMemberVoList = baseVoDAO.getResultBySQL(hql,new Object[]{},PartyMemberVo.class);
        return partyMemberVoList;
    }

    @Override
    public List<PartyMemberVo> getPartyMemberVosByName(String name) {
        PartyMemberVo partyMemberVo=new PartyMemberVo();
        String hql=this.commonSql + " where t1.name like ? order by t1.create_at desc";
        Object[] params = new Object[1];
        params[0] = "%"+name+"%";
        List<PartyMemberVo> partyMemberVoList = baseVoDAO.getResultBySQL(hql,params,PartyMemberVo.class);
        return partyMemberVoList;
    }

    @Override
    public List<PartyMemberVo> getPartyMemberVosByCondition(HashMap<String,String> map) {
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
        //是否困难党员
        if(map.containsKey("difficult"))
        {
            String val = map.get("difficult");
            addSql+=" and t1.difficult="+val;
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
        //文化程度
        if(map.containsKey("education"))
        {
            String val = map.get("education");
            addSql += " and t1.education="+val ;
        }
        //联系电话模糊
        if(map.containsKey("linkLike"))
        {
            addSql+=" and t1.link like ? ";
            String val = map.get("linkLike");
            paramList.add("%"+val+"%");
        }
        //入党时间起始
        if(map.containsKey("joinDateBegin"))
        {
            String val = map.get("joinDateBegin");
            addSql += " and t1.join_date>="+val;
        }
        //入党时间截止
        if(map.containsKey("joinDateEnd"))
        {
            String val = map.get("joinDateEnd");
            addSql += " and t1.join_date<="+val;
        }
        //所属社区
        if(map.containsKey("communityId"))
        {
            String val = map.get("communityId");
            addSql+= " and t1.community_id="+val;
        }
        //认领岗位
        if(map.containsKey("claimPost"))
        {
            String val = map.get("claimPost");
            addSql+= " and t1.claim_post="+val;
        }
        //家庭住址模糊
        if(map.containsKey("addressLike"))
        {
            addSql+=" and t1.address like ? ";
            String val = map.get("addressLike");
            paramList.add("%"+val+"%");
        }
        //身份证号
        if(map.containsKey("idCardLike"))
        {
            addSql+=" and t1.id_card like ? ";
            String val = map.get("idCardLike");
            paramList.add("%"+val+"%");
        }


        String hql=this.commonSql + " where 1=1 " + addSql + " order by t1.create_at desc";
        Object[] params = paramList.toArray();
        List<PartyMemberVo> partyMemberVoList = baseVoDAO.getResultBySQL(hql,params,PartyMemberVo.class);
        return partyMemberVoList;
    }

    @Override
    public List<PartyMemberVo> getPartyMemberVosByCondition(Map<String,String> map) {
        PartyMemberVo partyMemberVo=new PartyMemberVo();
        String addSql="";
        List<String> params = new ArrayList<>();
        if(map.containsKey("name"))
        {
            String name = map.get("name");
            name="%"+name+"%";
            addSql+=" and t1.name like ? ";
            params.add(name);
        }
        if(map.containsKey("difficult"))
        {
            String difficult=map.get("difficult");
            addSql+=" and t1.difficult = ? ";
            params.add(difficult);
        }

        String hql=this.commonSql + " where 1=1 "+addSql+" order by t1.create_at desc";
        List<PartyMemberVo> partyMemberVoList = baseVoDAO.getResultBySQL(hql,params.toArray(),PartyMemberVo.class);
        return partyMemberVoList;
    }

    @Override
    public PartyMemberVo getPartyMemberVoById(Long id) {
        PartyMemberVo partyMemberVo=new PartyMemberVo();
        String hql=this.commonSql + " where t1.id="+id;
        List<PartyMemberVo> partyMemberVoList = baseVoDAO.getResultBySQL(hql,new Object[]{},PartyMemberVo.class);
        if(partyMemberVoList.size()>0){
            partyMemberVo=partyMemberVoList.get(0);
        }
        return partyMemberVo;
    }

    @Override
    public List<PartyMember> getPartyMembersByIdCard(String idCard,String action,Long id)
    {
        String hql="from PartyMember where idCard =?";
        if(CommonConst.ACTION_ADD.equals(action))
        {}
        else if(CommonConst.ACTION_EDIT.equals(action))
        {
            hql+=" and id<>"+id;
        }

        Object[] params = new Object[1];
        params[0] = idCard;
        List<PartyMember> partyMembers = baseDAO.find(hql, params);
        return partyMembers;
    }
}
