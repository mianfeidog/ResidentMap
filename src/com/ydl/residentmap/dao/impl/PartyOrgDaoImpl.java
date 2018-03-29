package com.ydl.residentmap.dao.impl;

import com.ydl.residentmap.constants.DataDictionaryCode;
import com.ydl.residentmap.dao.BaseDao;
import com.ydl.residentmap.dao.PartyOrgDao;
import com.ydl.residentmap.model.KeyPerson;
import com.ydl.residentmap.model.PartyOrg;
import com.ydl.residentmap.model.vo.PartyOrgVo;
import com.ydl.residentmap.util.IdWorker;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Repository
public class PartyOrgDaoImpl implements PartyOrgDao {

    @Resource
    private BaseDao<PartyOrg> baseDAO;

    @Resource
    private BaseDao<PartyOrgVo> baseVoDAO;

    private String commonSql="select t1.lng,t1.lat, t1.par_party_name parPartyName,IFNULL(t3.name,'') orgSystemName, " +
        " IFNULL(t4.name,'') orgAttributeName,t5.name communityName, " +
        " t1.id,t1.name,t1.org_system orgSystem,t1.org_attribute orgAttribute, " +
        " t1.par_id parId,t1.secretary_name secretaryName,t1.member_cnt memberCnt,t1.address, " +
        " t1.telephone,t1.community_id communityId,t1.create_at createAt " +
        " from party_org t1 " +
        " left join data_dictionary t3 on t1.org_system=t3.value and t3.data_type= " + DataDictionaryCode.DATA_TYPE_ORG_SYSTEM +
        " left join data_dictionary t4 on t1.org_attribute=t4.value and t4.data_type= " + DataDictionaryCode.DATA_TYPE_ORG_ATTRIBUTE +
        " left join community t5 on t1.community_id=t5.id ";

    @Override
    public Boolean save(PartyOrg partyOrg) {
        Boolean flag = true;
        try {
            baseDAO.save(partyOrg);
            System.out.println("添加党组织 OK   重点人员ID："+partyOrg.getId());
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
            String hql="delete PartyOrg as r where r.id in ("+String.join(",", idList)+")";
            cnt=baseDAO.executeHql(hql);
        } catch (Exception e) {
            e.printStackTrace();
            cnt = 0;
        }
        return cnt;
    }

    @Override
    public Boolean update(PartyOrg partyOrg) {
        Boolean flag = true;
        try {
            baseDAO.update(partyOrg);
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    @Override
    public List<PartyOrg> getAllPartyOrgs() {
        String hql="from PartyOrg";
        return baseDAO.find(hql);
    }

    @Override
    public List<PartyOrg> getPartyOrgsByName(String name) {
        String hql="from PartyOrg where name like ?";
        Object[] params = new Object[1];
        params[0] = "%"+name+"%";
        List<PartyOrg> partyOrgs = baseDAO.find(hql, params);
        return partyOrgs;
    }

    @Override
    public List<PartyOrg> getPartyOrgsByCommunityId(Long communityId) {
        String hql="from PartyOrg where communityId= ?";
        Object[] params = new Object[1];
        params[0] = communityId;
        List<PartyOrg> partyOrgs = baseDAO.find(hql, params);
        return partyOrgs;
    }

    @Override
    public List<PartyOrg> getPartyOrgsByOrgSystem(Integer orgSystem) {
        String hql="from PartyOrg where orgSystem =?";
        Object[] params = new Object[1];
        params[0] = orgSystem;
        List<PartyOrg> partyOrgs = baseDAO.find(hql, params);
        return partyOrgs;
    }

    @Override
    public List<PartyOrg> getPartyOrgsByOrgAttribute(Integer orgAttribute) {
        String hql="from PartyOrg where orgAttribute =?";
        Object[] params = new Object[1];
        params[0] = orgAttribute;
        List<PartyOrg> partyOrgs = baseDAO.find(hql, params);
        return partyOrgs;
    }

    @Override
    public PartyOrg getPartyOrgById(Long id) {
        return baseDAO.get(PartyOrg.class, id);
    }

    @Override
    public List<PartyOrgVo> getAllPartyOrgVos() {
        String hql=this.commonSql + " order by t1.create_at desc ";
        List<PartyOrgVo> partyOrgVos = baseVoDAO.getResultBySQL(hql,new Object[]{},PartyOrgVo.class);
        return partyOrgVos;
    }

    @Override
    public List<PartyOrgVo> getPartyOrgVosByName(String name) {
        String hql=this.commonSql +" where t1.name like ? order by t1.create_at desc ";
        Object[] params = new Object[1];
        params[0] = "%"+name+"%";
        List<PartyOrgVo> partyOrgVos = baseVoDAO.getResultBySQL(hql,params,PartyOrgVo.class);
        return partyOrgVos;
    }

    @Override
    public List<PartyOrgVo> getPartyOrgVosByCondition(HashMap<String,String> map) {
        String addSql = "";
        List<String> paramList = new ArrayList<String>();

        //名称模糊
        if(map.containsKey("nameLike"))
        {
            addSql+=" and t1.name like ? ";
            String val = map.get("nameLike");
            paramList.add("%"+val+"%");
        }
        //组织建制
        if(map.containsKey("orgSystem"))
        {
            String val = map.get("orgSystem");
            addSql+=" and t1.org_system="+val;
        }
        //党组织属性
        if(map.containsKey("orgAttribute"))
        {
            String val = map.get("orgAttribute");
            addSql+=" and t1.org_attribute="+val;
        }
        //上级党组织模糊
        if(map.containsKey("parPartyNameLike"))
        {
            addSql+=" and t1.par_party_name like ? ";
            String val = map.get("parPartyNameLike");
            paramList.add("%"+val+"%");
        }
        //书记名称模糊
        if(map.containsKey("secretaryNameLike"))
        {
            addSql+=" and t1.secretary_name like ? ";
            String val = map.get("secretaryNameLike");
            paramList.add("%"+val+"%");
        }
        //党员人数起始
        if(map.containsKey("memberCntBegin"))
        {
            String val = map.get("memberCntBegin");
            addSql += " and t1.member_cnt>="+val;
        }
        //党员人数截止
        if(map.containsKey("memberCntEnd"))
        {
            String val = map.get("memberCntEnd");
            addSql += " and t1.member_cnt<="+val;
        }
        //联系电话模糊
        if(map.containsKey("telephoneLike"))
        {
            addSql+=" and t1.telephone like ? ";
            String val = map.get("telephoneLike");
            paramList.add("%"+val+"%");
        }
        //地址模糊
        if(map.containsKey("addressLike"))
        {
            addSql+=" and t1.address like ? ";
            String val = map.get("addressLike");
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
        List<PartyOrgVo> partyOrgVos = baseVoDAO.getResultBySQL(hql,params,PartyOrgVo.class);
        return partyOrgVos;
    }

    @Override
    public PartyOrgVo getPartyOrgVoById(Long id) {
        String hql=this.commonSql + " where t1.id="+id;
        List<PartyOrgVo> partyOrgVos = baseVoDAO.getResultBySQL(hql,new Object[]{},PartyOrgVo.class);
        PartyOrgVo partyOrgVo = new PartyOrgVo();
        if(partyOrgVos.size()>0){
            partyOrgVo=partyOrgVos.get(0);
        }
        return partyOrgVo;
    }
}
