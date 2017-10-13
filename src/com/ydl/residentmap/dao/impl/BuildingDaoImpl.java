package com.ydl.residentmap.dao.impl;

import com.ydl.residentmap.dao.BaseDao;
import com.ydl.residentmap.dao.BuildingDao;
import com.ydl.residentmap.model.Building;
import com.ydl.residentmap.model.KeyPerson;
import com.ydl.residentmap.model.vo.BuildingVo;
import com.ydl.residentmap.util.IdWorker;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@Repository
public class BuildingDaoImpl implements BuildingDao {
    @Resource
    private BaseDao<Building> baseDAO;

    @Resource
    private BaseDao<BuildingVo> baseVoDAO;

    private String commonSql="select t1.id,t1.name,t1.floor_count floorCount,t1.liver_count liverCount, " +
        " t1.lng,t1.lat,t1.address,t1.street_id streetId, " +
        " t1.community_id communityId,t1.block_id blockId, " +
        " t1.person_in_charge personInCharge,t1.person_in_charge_tel personInChargeTel, " +
        " ifnull(t2.name,'') streetName,ifnull(t3.name,'') communityName,ifnull(t4.name,'') blockName " +
        " from building t1 " +
        " left join street t2 on t1.street_id=t2.id " +
        " left join community t3 on t1.community_id=t3.id " +
        " left join block t4 on t1.block_id=t4.id ";

    @Override
    public Boolean save(Building building) {
        Boolean flag = true;
        try {
            Random random = new Random();
            building.setId(new IdWorker((long)random.nextInt(15)).nextId());
            baseDAO.save(building);
            System.out.println("添加楼栋 OK   重点人员ID："+building.getId());
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
            String hql="delete Building as r where r.id in ("+String.join(",", idList)+")";
            cnt=baseDAO.executeHql(hql);
        } catch (Exception e) {
            e.printStackTrace();
            cnt = 0;
        }
        return cnt;
    }

    @Override
    public Boolean update(Building building) {
        Boolean flag = true;
        try {
            baseDAO.update(building);
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    @Override
    public List<Building> getAllBuildings() {
        String hql="from Building";
        return baseDAO.find(hql);
    }

    @Override
    public List<Building> getBuildingsByName(String name) {
        String hql="from Building where name like ?";
        Object[] params = new Object[1];
        params[0] = "%"+name+"%";
        List<Building> buildings = baseDAO.find(hql, params);
        return buildings;
    }

    @Override
    public Building getBuildingById(Long id) {
        return baseDAO.get(Building.class, id);
    }

    @Override
    public List<BuildingVo> getAllBuildingVos() {
        String hql=this.commonSql;
        List<BuildingVo> buildingVos = baseVoDAO.getResultBySQL(hql,new Object[]{},BuildingVo.class);
        return buildingVos;
    }

    @Override
    public List<BuildingVo> getBuildingVosByName(String name) {
        String hql=this.commonSql + " where t1.name like ?";
        Object[] params = new Object[1];
        params[0] = "%"+name+"%";
        List<BuildingVo> buildingVos = baseVoDAO.getResultBySQL(hql,params,BuildingVo.class);
        return buildingVos;
    }

    @Override
    public BuildingVo getBuildingVoById(Long id) {
        BuildingVo buildingVo = new BuildingVo();
        String hql=this.commonSql + " where t1.id="+id;
        List<BuildingVo> buildingVos = baseVoDAO.getResultBySQL(hql,new Object[]{},BuildingVo.class);
        if(buildingVos.size()>0) {
            buildingVo=buildingVos.get(0);
        }
        return buildingVo;
    }
}
