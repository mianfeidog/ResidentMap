package com.ydl.residentmap.dao.impl;

import com.ydl.residentmap.dao.BaseDao;
import com.ydl.residentmap.dao.DeformityDao;
import com.ydl.residentmap.model.Cadre;
import com.ydl.residentmap.model.Deformity;
import com.ydl.residentmap.util.IdWorker;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@Repository
public class DeformityDaoImpl implements DeformityDao {
    @Resource
    private BaseDao<Deformity> baseDAO;

    @Override
    public Boolean save(Deformity deformity) {
        Boolean flag = true;
        try {
            Random random = new Random();
            deformity.setId(new IdWorker((long) random.nextInt(15)).nextId());
            baseDAO.save(deformity);
            System.out.println("添加残疾人 OK   ID：" + deformity.getId());
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
            String hql="delete Deformity as r where r.id in ("+String.join(",", idList)+")";
            cnt=baseDAO.executeHql(hql);
        } catch (Exception e) {
            e.printStackTrace();
            cnt = 0;
        }
        return cnt;
    }

    @Override
    public Boolean update(Deformity deformity) {
        Boolean flag = true;
        try {
            baseDAO.update(deformity);
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    @Override
    public List<Deformity> getAllDeformities() {
        String hql="from Deformity";
        return baseDAO.find(hql);
    }

    @Override
    public List<Deformity> getDeformitiesByName(String name) {
        String hql="from Deformity where name like ?";
        Object[] params = new Object[1];
        params[0] = "%"+name+"%";
        List<Deformity> deformities = baseDAO.find(hql, params);
        return deformities;
    }

    @Override
    public Deformity getDeformityById(Long id) {
        return baseDAO.get(Deformity.class, id);
    }
}
