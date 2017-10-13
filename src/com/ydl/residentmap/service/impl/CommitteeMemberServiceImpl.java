package com.ydl.residentmap.service.impl;

import com.ydl.residentmap.dao.CommitteeMemberDao;
import com.ydl.residentmap.model.CommitteeMember;
import com.ydl.residentmap.model.vo.CommitteeMemberVo;
import com.ydl.residentmap.service.CommitteeMemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommitteeMemberServiceImpl implements CommitteeMemberService {
    @Resource
    private CommitteeMemberDao committeeMemberDao ;

    @Override
    public Boolean save(CommitteeMember committeeMember) {
        return committeeMemberDao.save(committeeMember);
    }

    @Override
    public Integer deleteList(List<String> idList) {
        return committeeMemberDao.deleteList(idList);
    }

    @Override
    public Boolean update(CommitteeMember committeeMember) {
        return committeeMemberDao.update(committeeMember);
    }

    @Override
    public List<CommitteeMember> getAllCommitteeMembers() {
        return committeeMemberDao.getAllCommitteeMembers();
    }

    @Override
    public List<CommitteeMember> getCommitteeMembersByName(String name) {
        return committeeMemberDao.getCommitteeMembersByName(name);
    }

    @Override
    public CommitteeMember getCommitteeMemberById(Long id) {
        return committeeMemberDao.getCommitteeMemberById(id);
    }

    @Override
    public List<CommitteeMemberVo> getAllCommitteeMemberVos() {
        return committeeMemberDao.getAllCommitteeMemberVos();
    }

    @Override
    public List<CommitteeMemberVo> getCommitteeMemberVosByName(String name) {
        return committeeMemberDao.getCommitteeMemberVosByName(name);
    }

    @Override
    public CommitteeMemberVo getCommitteeMemberVoById(Long id) {
        return committeeMemberDao.getCommitteeMemberVoById(id);
    }
}
