package com.ydl.residentmap.dao.impl;

import com.ydl.residentmap.dao.BaseDao;
import com.ydl.residentmap.dao.LowIncomeAllowancesDao;
import com.ydl.residentmap.model.KeyPerson;
import com.ydl.residentmap.model.LowIncomeAllowances;
import com.ydl.residentmap.util.IdWorker;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@Repository
public class LowIncomeAllowancesDaoImpl implements LowIncomeAllowancesDao {

    @Resource
    private BaseDao<LowIncomeAllowances> baseDAO;

    @Override
    public Boolean save(LowIncomeAllowances lowIncomeAllowances) {
        Boolean flag = true;
        try {
            Random random = new Random();
            lowIncomeAllowances.setId(new IdWorker((long)random.nextInt(15)).nextId());
            baseDAO.save(lowIncomeAllowances);
            System.out.println("添加贫困户低保户 OK   ID："+lowIncomeAllowances.getId());
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
            String hql="delete LowIncomeAllowances as r where r.id in ("+String.join(",", idList)+")";
            cnt=baseDAO.executeHql(hql);
        } catch (Exception e) {
            e.printStackTrace();
            cnt = 0;
        }
        return cnt;
    }

    @Override
    public Boolean update(LowIncomeAllowances lowIncomeAllowances) {
        Boolean flag = true;
        try {
            baseDAO.update(lowIncomeAllowances);
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    @Override
    public LowIncomeAllowances getLowIncomeAllowancesById(Long id) {
        return baseDAO.get(LowIncomeAllowances.class, id);
    }

    @Override
    public List<LowIncomeAllowances> getAllLowIncomeAllowances() {
        String hql="from LowIncomeAllowances";
        return baseDAO.find(hql);
    }

    @Override
    public List<LowIncomeAllowances> getLowIncomeAllowancesByName(String name) {
        String hql="from LowIncomeAllowances where name like ?";
        Object[] params = new Object[1];
        params[0] = "%"+name+"%";
        List<LowIncomeAllowances> lowIncomeAllowances = baseDAO.find(hql, params);
        return lowIncomeAllowances;
    }
}
