package com.ydl.residentmap.service;

import java.util.List;

public interface DelegateCommitteeService  {
    Boolean save(com.ydl.residentmap.model.DelegateCommittee delegateCommittee);

    Integer deleteList(List<String> idList);

    Boolean update(com.ydl.residentmap.model.DelegateCommittee delegateCommittee);

    List<com.ydl.residentmap.model.DelegateCommittee> getAllDelegateCommittees();

    List<com.ydl.residentmap.model.DelegateCommittee> getDelegateCommitteesByName(String name);

    com.ydl.residentmap.model.DelegateCommittee getDelegateCommitteeById(Long id);
}
