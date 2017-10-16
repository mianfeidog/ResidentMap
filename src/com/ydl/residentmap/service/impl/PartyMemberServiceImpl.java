package com.ydl.residentmap.service.impl;

import com.ydl.residentmap.dao.PartyMemberDao;
import com.ydl.residentmap.model.PartyMember;
import com.ydl.residentmap.model.vo.PartyMemberVo;
import com.ydl.residentmap.service.PartyMemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PartyMemberServiceImpl implements PartyMemberService {

    @Resource
    private PartyMemberDao partyMemberDao ;

    @Override
    public Boolean save(PartyMember partyMember) {
        return partyMemberDao.save(partyMember);
    }

    @Override
    public Integer deleteList(List<String> idList) {
        return partyMemberDao.deleteList(idList);
    }

    @Override
    public Boolean update(PartyMember partyMember) {
        return partyMemberDao.update(partyMember);
    }

    @Override
    public List<PartyMember> getAllPartyMembers() {
        return partyMemberDao.getAllPartyMembers();
    }

    @Override
    public List<PartyMember> getPartyMembersByName(String name) {
        return partyMemberDao.getPartyMembersByName(name);
    }

    @Override
    public PartyMember getPartyMemberById(Long id) {
        return partyMemberDao.getPartyMemberById(id);
    }

    @Override
    public List<PartyMemberVo> getAllPartyMemberVos() {
        return partyMemberDao.getAllPartyMemberVos();
    }

    @Override
    public List<PartyMemberVo> getPartyMemberVosByName(String name) {
        return partyMemberDao.getPartyMemberVosByName(name);
    }

    @Override
    public PartyMemberVo getPartyMemberVoById(Long id) {
        return partyMemberDao.getPartyMemberVoById(id);
    }

    @Override
    public List<PartyMember> getPartyMembersByIdCard(String idCard, String action, Long id) {
        return partyMemberDao.getPartyMembersByIdCard(idCard,action,id);
    }
}
