package com.ydl.residentmap.service;

import com.ydl.residentmap.model.Building;
import com.ydl.residentmap.model.vo.BuildingVo;

import java.util.List;

public interface BuildingService {
    Boolean save(Building building);

    Integer deleteList(List<String> idList);

    Boolean update(Building building);

    List<Building> getAllBuildings();

    List<Building> getBuildingsByName(String name);

    Building getBuildingById(Long id);

    List<BuildingVo> getAllBuildingVos();

    List<BuildingVo> getBuildingVosByName(String name);

    BuildingVo getBuildingVoById(Long id);
}
