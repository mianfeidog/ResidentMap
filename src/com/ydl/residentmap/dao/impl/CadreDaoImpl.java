package com.ydl.residentmap.dao.impl;

import com.ydl.residentmap.dao.BaseDao;
import com.ydl.residentmap.dao.CadreDao;
import com.ydl.residentmap.model.Block;
import com.ydl.residentmap.model.Cadre;
import com.ydl.residentmap.util.IdWorker;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@Repository
public class CadreDaoImpl implements CadreDao {
    @Resource
    private BaseDao<Cadre> baseDAO;

    @Override
    public Boolean save(Cadre cadre) {
        Boolean flag = true;
        try {
            Random random = new Random();
            cadre.setId(new IdWorker((long) random.nextInt(15)).nextId());
            baseDAO.save(cadre);
            System.out.println("添加社区干部 OK   干部ID：" + cadre.getId());
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
            String hql="delete Cadre as r where r.id in ("+String.join(",", idList)+")";
            cnt=baseDAO.executeHql(hql);
        } catch (Exception e) {
            e.printStackTrace();
            cnt = 0;
        }
        return cnt;
    }

    @Override
    public Boolean update(Cadre cadre) {
        Boolean flag = true;
        try {
            baseDAO.update(cadre);
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    @Override
    public List<Cadre> getAllCadres() {
        String hql="from Cadre";
        return baseDAO.find(hql);
    }

    @Override
    public List<Cadre> getCadresByName(String name) {
        String hql="from Cadre where name like ?";
        Object[] params = new Object[1];
        params[0] = "%"+name+"%";
        List<Cadre> cadres = baseDAO.find(hql, params);
        return cadres;
    }

    @Override
    public Cadre getCadreById(Long id) {
        return baseDAO.get(Cadre.class, id);
    }
}
