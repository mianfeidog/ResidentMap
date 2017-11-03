package com.ydl.residentmap.service.impl;

import com.ydl.residentmap.constants.ResultMessage;
import com.ydl.residentmap.dao.BuildingDao;
import com.ydl.residentmap.model.Building;
import com.ydl.residentmap.model.vo.BuildingVo;
import com.ydl.residentmap.service.BuildingService;
import com.ydl.residentmap.util.IdWorker;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class BuildingServiceImpl implements BuildingService {
    @Resource
    private BuildingDao buildingDao ;

    @Override
    public Boolean save(Building building) {
        Long blockId = building.getBlockId();
        if(blockId==null)
        {
            throw new RuntimeException(ResultMessage.EMPTY_BLOCK_ID);
        }

        //创建时间
        Date now = new Date();
        String sdate=(new SimpleDateFormat("yyyyMMddHHmm")).format(now);
        Long dateLong = Long.parseLong(sdate);
        building.setCreateAt(dateLong);

        Random random = new Random();
        building.setId(new IdWorker((long)random.nextInt(15)).nextId());

        return buildingDao.save(building);
    }

    @Override
    public Integer deleteList(List<String> idList) {
        return buildingDao.deleteList(idList);
    }

    @Override
    public Boolean update(Building building) {
        Long blockId = building.getBlockId();
        if(blockId==null)
        {
            throw new RuntimeException(ResultMessage.EMPTY_BLOCK_ID);
        }

        //创建时间
        Date now = new Date();
        String sdate=(new SimpleDateFormat("yyyyMMddHHmm")).format(now);
        Long dateLong = Long.parseLong(sdate);
        building.setCreateAt(dateLong);

        return buildingDao.update(building);
    }

    @Override
    public List<Building> getAllBuildings() {
        return buildingDao.getAllBuildings();
    }

    @Override
    public List<Building> getBuildingsByName(String name) {
        return buildingDao.getBuildingsByName(name);
    }

    @Override
    public Building getBuildingById(Long id) {
        return buildingDao.getBuildingById(id);
    }

    @Override
    public List<BuildingVo> getAllBuildingVos() {
        return buildingDao.getAllBuildingVos();
    }

    @Override
    public List<BuildingVo> getBuildingVosByName(String name) {
        return buildingDao.getBuildingVosByName(name);
    }

    @Override
    public BuildingVo getBuildingVoById(Long id) {
        return buildingDao.getBuildingVoById(id);
    }
}
