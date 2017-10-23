package com.ydl.residentmap.service.impl;

import com.ydl.residentmap.dao.DelegateCommitteeDao;
import com.ydl.residentmap.model.DelegateCommittee;
import com.ydl.residentmap.model.vo.DelegateCommitteeVo;
import com.ydl.residentmap.service.DelegateCommitteeService;
import com.ydl.residentmap.util.IdWorker;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class DelegateCommitteeServiceImpl implements DelegateCommitteeService {

    @Resource
    private DelegateCommitteeDao delegateCommitteeDao ;

    @Override
    public Boolean save(DelegateCommittee delegateCommittee) {
        Random random = new Random();
        delegateCommittee.setId(new IdWorker((long) random.nextInt(15)).nextId());

        //创建时间
        Date now = new Date();
        String sdate=(new SimpleDateFormat("yyyyMMddHHmm")).format(now);
        Long dateLong = Long.parseLong(sdate);
        delegateCommittee.setCreateAt(dateLong);
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

    @Override
    public List<DelegateCommitteeVo> getAllDelegateCommitteeVos() {
        return delegateCommitteeDao.getAllDelegateCommitteeVos();
    }

    @Override
    public List<DelegateCommitteeVo> getDelegateCommitteeVosByName(String name) {
        return delegateCommitteeDao.getDelegateCommitteeVosByName(name);
    }

    @Override
    public DelegateCommitteeVo getDelegateCommitteeVoById(Long id) {
        return delegateCommitteeDao.getDelegateCommitteeVoById(id);
    }
}
