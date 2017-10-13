package com.ydl.residentmap.dao;

import com.ydl.residentmap.model.DelegateCommittee;

import java.util.List;

public interface DelegateCommitteeDao {
    Boolean save(DelegateCommittee delegateCommittee);

    Integer deleteList(List<String> idList);

    Boolean update(DelegateCommittee delegateCommittee);

    List<DelegateCommittee> getAllDelegateCommittees();

    List<DelegateCommittee> getDelegateCommitteesByName(String name);

    DelegateCommittee getDelegateCommitteeById(Long id);
}
