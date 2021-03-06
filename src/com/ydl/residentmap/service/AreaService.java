package com.ydl.residentmap.service;
import com.ydl.residentmap.model.Area;

import java.util.List;

/**
 * Created by 小强 on 2017/7/11.
 */
public interface AreaService {
    Boolean saveArea(Area area);
    //删除所有行政数据
    Boolean deleteAll();
    //根据父级id查询行政区域
    List<Area> getAreasByPid(Long pid);
    //查询所有地区
    List<Area> getAll();
}
