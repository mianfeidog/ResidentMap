package com.ydl.residentmap.service.impl;
import com.ydl.residentmap.dao.AreaDao;
import com.ydl.residentmap.model.Area;
import com.ydl.residentmap.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 行政区域
 * Created by 小强 on 2017/7/11.
 */
@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    AreaDao areaDao;

    /**
     * 保存
     * @param area
     * @return
     */
    @Override
    public Boolean saveArea(Area area) {
        return areaDao.saveArea(area);
    }

    @Override
    public Boolean deleteAll() {
        return areaDao.deleteAll();
    }

    @Override
    public List<Area> getAreasByPid(Long pid) {
        if(pid == null){
            pid = 0l;
        }
        return areaDao.getAreasByPid(pid);
    }



    /**
     * 找出区域码对应的区域
     * @param code
     * @param areas
     * @return
     */
    private Area findByCode(String code,List<Area> areas){
        for(int i=0;i<areas.size();i++){
            if(areas.get(i).getId().toString().equals(code)){
                return areas.get(i);
            }
        }
        return null;
    }

    @Override
    public List<Area> getAll() {
        return areaDao.getAll();
    }
}
