package com.ydl.residentmap.dao;

import com.ydl.residentmap.model.PartyOrg;

import java.util.List;

public interface PartyOrgDao {
    Boolean save(PartyOrg partyOrg);

    Integer deleteList(List<String> idList);

    Boolean update(PartyOrg partyOrg);

    List<PartyOrg> getAllPartyOrgs();

    List<PartyOrg> getPartyOrgsByName(String name);

    PartyOrg getPartyOrgById(Long id);
}
