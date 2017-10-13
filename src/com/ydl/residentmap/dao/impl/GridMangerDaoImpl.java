package com.ydl.residentmap.dao.impl;

import com.ydl.residentmap.dao.BaseDao;
import com.ydl.residentmap.dao.GridManagerDao;
import com.ydl.residentmap.model.GridManager;
import com.ydl.residentmap.util.IdWorker;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@Repository
public class GridMangerDaoImpl implements GridManagerDao{
    @Resource
    private BaseDao<GridManager> baseDAO;

    @Override
    public Boolean save(GridManager gridManager) {
        Boolean flag = true;
        try {
            Random random = new Random();
            gridManager.setId(new IdWorker((long)random.nextInt(15)).nextId());
            baseDAO.save(gridManager);
            System.out.println("添加网格化管理人员 OK   人员ID："+gridManager.getId());
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
            String hql="delete GridManager as r where r.id in ("+String.join(",", idList)+")";
            cnt=baseDAO.executeHql(hql);
        } catch (Exception e) {
            e.printStackTrace();
            cnt = 0;
        }
        return cnt;
    }

    @Override
    public Boolean update(GridManager gridManager) {
        Boolean flag = true;
        try {
            baseDAO.update(gridManager);
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    @Override
    public List<GridManager> getAllGridManagers() {
        String hql="from GridManager";
        return baseDAO.find(hql);
    }

    @Override
    public List<GridManager> getKeyGridManagersByName(String name) {
        String hql="from GridManager where name like ?";
        Object[] params = new Object[1];
        params[0] = "%"+name+"%";
        List<GridManager> gridManagers = baseDAO.find(hql, params);
        return gridManagers;
    }

    @Override
    public GridManager getGridManagerById(Long id) {
        return baseDAO.get(GridManager.class, id);
    }
}
