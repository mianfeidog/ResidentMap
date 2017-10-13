package com.ydl.residentmap.service.impl;

import com.ydl.residentmap.dao.DelegateCommitteeDao;
import com.ydl.residentmap.model.DelegateCommittee;
import com.ydl.residentmap.service.DelegateCommitteeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DelegateCommitteeServiceImpl implements DelegateCommitteeService {

    @Resource
    private DelegateCommitteeDao delegateCommitteeDao ;

    @Override
    public Boolean save(DelegateCommittee delegateCommittee) {
        return delegateCommitteeDao.save(delegateCommittee);
    }

    @Override
    public Integer deleteList(List<String> idList) {
        return delegateCommitteeDao.deleteList(idList);
    }

    @Override
    public Boolean update(DelegateCommittee delegateCommittee) {
        return delegateCommitteeDao.update(delegateCommittee);
    }

    @Override
    public List<DelegateCommittee> getAllDelegateCommittees() {
        return delegateCommitteeDao.getAllDelegateCommittees();
    }

    @Override
    public List<DelegateCommittee> getDelegateCommitteesByName(String name) {
        return delegateCommitteeDao.getDelegateCommitteesByName(name);
    }

    @Override
    public DelegateCommittee getDelegateCommitteeById(Long id) {
        return delegateCommitteeDao.getDelegateCommitteeById(id);
    }
}
