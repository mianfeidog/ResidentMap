package com.ydl.residentmap.service;

import com.ydl.residentmap.model.vo.DelegateCommitteeVo;

import java.util.HashMap;
import java.util.List;

public interface DelegateCommitteeService  {
    Boolean save(com.ydl.residentmap.model.DelegateCommittee delegateCommittee);

    Integer deleteList(List<String> idList);

    Boolean update(com.ydl.residentmap.model.DelegateCommittee delegateCommittee);

    List<com.ydl.residentmap.model.DelegateCommittee> getAllDelegateCommittees();

    List<com.ydl.residentmap.model.DelegateCommittee> getDelegateCommitteesByName(String name);

    com.ydl.residentmap.model.DelegateCommittee getDelegateCommitteeById(Long id);

    List<DelegateCommitteeVo> getAllDelegateCommitteeVos();

    List<DelegateCommitteeVo> getDelegateCommitteeVosByName(String name);

    List<DelegateCommitteeVo> getDelegateCommitteeVosByCondition(HashMap<String,String> map);

    DelegateCommitteeVo getDelegateCommitteeVoById(Long id);
}
