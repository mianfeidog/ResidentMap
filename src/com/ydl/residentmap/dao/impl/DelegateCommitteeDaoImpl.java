package com.ydl.residentmap.dao.impl;

import com.ydl.residentmap.dao.BaseDao;
import com.ydl.residentmap.dao.DelegateCommitteeDao;
import com.ydl.residentmap.model.Cadre;
import com.ydl.residentmap.model.DelegateCommittee;
import com.ydl.residentmap.util.IdWorker;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@Repository
public class DelegateCommitteeDaoImpl implements DelegateCommitteeDao {

    @Resource
    private BaseDao<DelegateCommittee> baseDAO;

    @Override
    public Boolean save(DelegateCommittee delegateCommittee) {
        Boolean flag = true;
        try {
            Random random = new Random();
            delegateCommittee.setId(new IdWorker((long) random.nextInt(15)).nextId());
            baseDAO.save(delegateCommittee);
            System.out.println("添加两代表一委员 OK   ID：" + delegateCommittee.getId());
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    @Override
    public Integer deleteList(List<String> idList) {
        Integer cnt = 0;
        try {
            String hql="delete DelegateCommittee as r where r.id in ("+String.join(",", idList)+")";
            cnt=baseDAO.executeHql(hql);
        } catch (Exception e) {
            e.printStackTrace();
            cnt = 0;
        }
        return cnt;
    }

    @Override
    public Boolean update(DelegateCommittee delegateCommittee) {
        Boolean flag = true;
        try {
            baseDAO.update(delegateCommittee);
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    @Override
    public List<DelegateCommittee> getAllDelegateCommittees() {
        String hql="from DelegateCommittee";
        return baseDAO.find(hql);
    }

    @Override
    public List<DelegateCommittee> getDelegateCommitteesByName(String name) {
        String hql="from DelegateCommittee where name like ?";
        Object[] params = new Object[1];
        params[0] = "%"+name+"%";
        List<DelegateCommittee> delegateCommittees = baseDAO.find(hql, params);
        return delegateCommittees;
    }

    @Override
    public DelegateCommittee getDelegateCommitteeById(Long id) {
        return baseDAO.get(DelegateCommittee.class, id);
    }
}
