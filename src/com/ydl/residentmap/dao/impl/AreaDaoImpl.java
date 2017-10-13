package com.ydl.residentmap.dao.impl;
import com.ydl.residentmap.dao.AreaDao;
import com.ydl.residentmap.dao.BaseDao;
import com.ydl.residentmap.model.Area;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 小强 on 2017/7/11.
 */
@Repository
public class AreaDaoImpl implements AreaDao {
    @Resource
    BaseDao<Area> baseDao;
    @Override
    public Boolean saveArea(Area area) {
        Boolean rs = true;
        try{
            baseDao.save(area);
        }catch (Exception e){
            rs = false;
            e.printStackTrace();
        }

        return rs;
    }


    @Override
    public Boolean deleteAll() {
        Boolean rs = true;
        try{
            String hql="delete from Area";
            baseDao.executeHql(hql);
        }catch (Exception e){
            rs = false;
            e.printStackTrace();
        }

        return rs;
    }


    /**
     * 根据父级id获取行政区域
     * @param pid
     * @return
     */
    @Override
    public List<Area> getAreasByPid(Long pid) {
        String hql="from Area where pid=?";
        return baseDao.find(hql,new Object[]{pid});
    }


    @Override
    public List<Area> getAll() {
        String hql="from Area";
        return baseDao.find(hql);
    }

    @Override
    public Area get(Long id) {
        return baseDao.get(Area.class,id);
    }
}
