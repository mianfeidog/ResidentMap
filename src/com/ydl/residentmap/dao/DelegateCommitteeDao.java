package com.ydl.residentmap.dao;

import com.ydl.residentmap.model.DelegateCommittee;
import com.ydl.residentmap.model.vo.DelegateCommitteeVo;

import java.util.List;

public interface DelegateCommitteeDao {
    Boolean save(DelegateCommittee delegateCommittee);

    Integer deleteList(List<String> idList);

    Boolean update(DelegateCommittee delegateCommittee);

    List<DelegateCommittee> getAllDelegateCommittees();

    List<DelegateCommittee> getDelegateCommitteesByName(String name);

    DelegateCommittee getDelegateCommitteeById(Long id);

    List<DelegateCommitteeVo> getAllDelegateCommitteeVos();

    List<DelegateCommitteeVo> getDelegateCommitteeVosByName(String name);

    DelegateCommitteeVo getDelegateCommitteeVoById(Long id);
}
