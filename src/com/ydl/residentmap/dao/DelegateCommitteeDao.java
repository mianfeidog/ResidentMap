package com.ydl.residentmap.dao;

import com.ydl.residentmap.model.DelegateCommittee;
import com.ydl.residentmap.model.vo.DelegateCommitteeVo;

import java.util.HashMap;
import java.util.List;

public interface DelegateCommitteeDao {
    Boolean save(DelegateCommittee delegateCommittee);

    Integer deleteList(List<String> idList);

    Boolean update(DelegateCommittee delegateCommittee);

    List<DelegateCommittee> getAllDelegateCommittees();

    List<DelegateCommittee> getDelegateCommitteesByName(String name);

    List<DelegateCommittee> getDelegateCommitteesByCommunityId(Long communityId);

    List<DelegateCommittee> getDelegateCommitteesByMinority(Integer minority);

    List<DelegateCommittee> getDelegateCommitteesByEducation(Integer education);

    List<DelegateCommittee> getDelegateCommitteesByParty(Integer party);

    DelegateCommittee getDelegateCommitteeById(Long id);

    List<DelegateCommitteeVo> getAllDelegateCommitteeVos();

    List<DelegateCommitteeVo> getDelegateCommitteeVosByName(String name);

    List<DelegateCommitteeVo> getDelegateCommitteeVosByCondition(HashMap<String,String> map);

    DelegateCommitteeVo getDelegateCommitteeVoById(Long id);
}
