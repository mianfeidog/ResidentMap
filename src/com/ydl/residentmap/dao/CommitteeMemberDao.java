package com.ydl.residentmap.dao;

import com.ydl.residentmap.model.CommitteeMember;
import com.ydl.residentmap.model.vo.CommitteeMemberVo;

import java.util.List;

public interface CommitteeMemberDao {
    Boolean save(CommitteeMember committeeMember);

    Integer deleteList(List<String> idList);

    Boolean update(CommitteeMember committeeMember);

    List<CommitteeMember> getAllCommitteeMembers();

    List<CommitteeMember> getCommitteeMembersByName(String name);

    List<CommitteeMember> getCommitteeMembersByCommunityId(Long communityId);

    List<CommitteeMember> getCommitteeMembersByEducation(Integer education);

    List<CommitteeMember> getCommitteeMembersByPosition(Integer position);

    CommitteeMember getCommitteeMemberById(Long id);

    List<CommitteeMemberVo> getAllCommitteeMemberVos();

    List<CommitteeMemberVo> getCommitteeMemberVosByName(String name);

    CommitteeMemberVo getCommitteeMemberVoById(Long id);

    List<CommitteeMember> getCommitteeMemebersByIdCard(String idCard,String action,Long id);
}
