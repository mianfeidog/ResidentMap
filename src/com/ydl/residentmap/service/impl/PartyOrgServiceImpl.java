package com.ydl.residentmap.service.impl;

import com.ydl.residentmap.dao.PartyOrgDao;
import com.ydl.residentmap.model.PartyOrg;
import com.ydl.residentmap.service.PartyOrgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PartyOrgServiceImpl implements PartyOrgService {

    @Resource
    private PartyOrgDao partyOrgDao ;

    @Override
    public Boolean save(PartyOrg partyOrg) {
        return partyOrgDao.save(partyOrg);
    }

    @Override
    public Integer deleteList(List<String> idList) {
        return partyOrgDao.deleteList(idList);
    }

    @Override
    public Boolean update(PartyOrg partyOrg) {
        return partyOrgDao.update(partyOrg);
    }

    @Override
    public List<PartyOrg> getAllPartyOrgs() {
        return partyOrgDao.getAllPartyOrgs();
    }

    @Override
    public List<PartyOrg> getPartyOrgsByName(String name) {
        return partyOrgDao.getPartyOrgsByName(name);
    }

    @Override
    public PartyOrg getPartyOrgById(Long id) {
        return partyOrgDao.getPartyOrgById(id);
    }
}
