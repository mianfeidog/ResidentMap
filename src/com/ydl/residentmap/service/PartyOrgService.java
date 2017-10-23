package com.ydl.residentmap.service;

import com.ydl.residentmap.model.PartyOrg;
import com.ydl.residentmap.model.vo.PartyOrgVo;

import java.util.List;

public interface PartyOrgService {
    Boolean save(PartyOrg partyOrg);

    Integer deleteList(List<String> idList);

    Boolean update(PartyOrg partyOrg);

    List<PartyOrg> getAllPartyOrgs();

    List<PartyOrg> getPartyOrgsByName(String name);

    PartyOrg getPartyOrgById(Long id);

    List<PartyOrgVo> getAllPartyOrgVos();

    List<PartyOrgVo> getPartyOrgVosByName(String name);

    PartyOrgVo getPartyOrgVoById(Long id);
}
