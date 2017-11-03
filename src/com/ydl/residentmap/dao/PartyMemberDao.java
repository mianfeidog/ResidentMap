package com.ydl.residentmap.dao;

import com.ydl.residentmap.model.PartyMember;
import com.ydl.residentmap.model.vo.PartyMemberVo;

import java.util.List;

public interface PartyMemberDao {
    Boolean save(PartyMember partyMember);

    Integer deleteList(List<String> idList);

    Boolean update(PartyMember partyMember);

    List<PartyMember> getAllPartyMembers();

    List<PartyMember> getPartyMembersByName(String name);

    List<PartyMember> getPartyMembersByMinority(Integer minority);

    List<PartyMember> getPartyMembersByEducation(Integer education);

    List<PartyMember> getPartyMembersByPost(Integer post);

    PartyMember getPartyMemberById(Long id);

    List<PartyMemberVo> getAllPartyMemberVos();

    List<PartyMemberVo> getPartyMemberVosByName(String name);

    PartyMemberVo getPartyMemberVoById(Long id);

    List<PartyMember> getPartyMembersByIdCard(String idCard,String action,Long id);
}
