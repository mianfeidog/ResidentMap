package com.ydl.residentmap.dao;

import com.ydl.residentmap.model.PartyOrg;
import com.ydl.residentmap.model.vo.PartyOrgVo;

import java.util.List;

public interface PartyOrgDao {
    Boolean save(PartyOrg partyOrg);

    Integer deleteList(List<String> idList);

    Boolean update(PartyOrg partyOrg);

    List<PartyOrg> getAllPartyOrgs();

    List<PartyOrg> getPartyOrgsByName(String name);

    List<PartyOrg> getPartyOrgsByCommunityId(Long communityId);

    List<PartyOrg> getPartyOrgsByOrgSystem(Integer orgSystem);

    List<PartyOrg> getPartyOrgsByOrgAttribute(Integer orgAttribute);

    PartyOrg getPartyOrgById(Long id);

    List<PartyOrgVo> getAllPartyOrgVos();

    List<PartyOrgVo> getPartyOrgVosByName(String name);

    PartyOrgVo getPartyOrgVoById(Long id);
}
