package com.ydl.residentmap.dao.impl;

import com.ydl.residentmap.dao.BaseDao;
import com.ydl.residentmap.dao.PartyOrgDao;
import com.ydl.residentmap.model.KeyPerson;
import com.ydl.residentmap.model.PartyOrg;
import com.ydl.residentmap.util.IdWorker;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@Repository
public class PartyOrgDaoImpl implements PartyOrgDao {

    @Resource
    private BaseDao<PartyOrg> baseDAO;

    @Override
    public Boolean save(PartyOrg partyOrg) {
        Boolean flag = true;
        try {
            Random random = new Random();
            partyOrg.setId(new IdWorker((long)random.nextInt(15)).nextId());
            baseDAO.save(partyOrg);
            System.out.println("添加党组织 OK   重点人员ID："+partyOrg.getId());
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
            String hql="delete PartyOrg as r where r.id in ("+String.join(",", idList)+")";
            cnt=baseDAO.executeHql(hql);
        } catch (Exception e) {
            e.printStackTrace();
            cnt = 0;
        }
        return cnt;
    }

    @Override
    public Boolean update(PartyOrg partyOrg) {
        Boolean flag = true;
        try {
            baseDAO.update(partyOrg);
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    @Override
    public List<PartyOrg> getAllPartyOrgs() {
        String hql="from PartyOrg";
        return baseDAO.find(hql);
    }

    @Override
    public List<PartyOrg> getPartyOrgsByName(String name) {
        String hql="from PartyOrg where name like ?";
        Object[] params = new Object[1];
        params[0] = "%"+name+"%";
        List<PartyOrg> partyOrgs = baseDAO.find(hql, params);
        return partyOrgs;
    }

    @Override
    public PartyOrg getPartyOrgById(Long id) {
        return baseDAO.get(PartyOrg.class, id);
    }
}
