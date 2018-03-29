package com.ydl.residentmap.service;

import com.ydl.residentmap.model.CommitteeMember;
import com.ydl.residentmap.model.vo.CommitteeMemberVo;

import java.util.List;

public interface CommitteeMemberService {
    Boolean save(CommitteeMember committeeMember);

    Integer deleteList(List<String> idList);

    Boolean update(CommitteeMember committeeMember);

    List<CommitteeMember> getAllCommitteeMembers();

    List<CommitteeMember> getCommitteeMembersByName(String name);

    CommitteeMember getCommitteeMemberById(Long id);

    List<CommitteeMemberVo> getAllCommitteeMemberVos();

    List<CommitteeMemberVo> getCommitteeMemberVosByName(String name);

    List<CommitteeMemberVo> getCommitteeMemberVosByCondition(String condition);

    CommitteeMemberVo getCommitteeMemberVoById(Long id);

    List<CommitteeMember> getCommitteeMemebersByIdCard(String idCard,String action,Long id);
}
