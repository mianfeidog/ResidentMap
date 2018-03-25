package com.ydl.residentmap.service.impl;

import com.ydl.residentmap.constants.ResultMessage;
import com.ydl.residentmap.dao.GridManagerDao;
import com.ydl.residentmap.model.GridManager;
import com.ydl.residentmap.model.vo.GridManagerVo;
import com.ydl.residentmap.service.GridManagerService;
import com.ydl.residentmap.util.IdWorker;
import com.ydl.residentmap.util.LatitudeUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class GridManagerServiceImpl implements GridManagerService {
    @Resource(name="gridManagerDao")
    private GridManagerDao gridManagerDao ;

    @Override
    public Boolean save(GridManager gridManager) {
        Long communityId = gridManager.getCommunityId();
        if(communityId==null)
        {
            throw new RuntimeException(ResultMessage.EMPTY_COMMUNITY_ID);
        }

//        String address = gridManager.getAddress().trim();
//        //地址不为空，获取经纬度
//        if(!"".equals(address))
//        {
//            Map<String,String> lngLat = LatitudeUtils.getGeocoderLatitude(address);
//            if(lngLat!=null){
//                String lng = lngLat.get("lng");
//                String lat = lngLat.get("lat");
//                gridManager.setLng(lng);
//                gridManager.setLat(lat);
//            }
//            else
//            {
//                throw new RuntimeException(ResultMessage.NO_LNG_LAT);
//            }
//        }
//        else
//        {
//            throw new RuntimeException(ResultMessage.EMPTY_ADDRESS);
//        }

        //创建时间
        Date now = new Date();
        String sdate=(new SimpleDateFormat("yyyyMMddHHmm")).format(now);
        Long dateLong = Long.parseLong(sdate);
        gridManager.setCreateAt(dateLong);

        Random random = new Random();
        gridManager.setId(new IdWorker((long)random.nextInt(15)).nextId());

        return gridManagerDao.save(gridManager);
    }

    @Override
    public Integer deleteList(List<String> idList) {
        return gridManagerDao.deleteList(idList);
    }

    @Override
    public Boolean update(GridManager gridManager) {
        Long communityId = gridManager.getCommunityId();
        if(communityId==null)
        {
            throw new RuntimeException(ResultMessage.EMPTY_COMMUNITY_ID);
        }

//        String address = gridManager.getAddress().trim();
//        //地址不为空，获取经纬度
//        if(!"".equals(address))
//        {
//            Map<String,String> lngLat = LatitudeUtils.getGeocoderLatitude(address);
//            if(lngLat!=null){
//                String lng = lngLat.get("lng");
//                String lat = lngLat.get("lat");
//                gridManager.setLng(lng);
//                gridManager.setLat(lat);
//            }
//            else
//            {
//                throw new RuntimeException(ResultMessage.NO_LNG_LAT);
//            }
//        }
//        else
//        {
//            throw new RuntimeException(ResultMessage.EMPTY_ADDRESS);
//        }

        //创建时间
        Date now = new Date();
        String sdate=(new SimpleDateFormat("yyyyMMddHHmm")).format(now);
        Long dateLong = Long.parseLong(sdate);
        gridManager.setCreateAt(dateLong);

        return gridManagerDao.update(gridManager);
    }

    @Override
    public List<GridManager> getAllGridManagers() {
        return gridManagerDao.getAllGridManagers();
    }

    @Override
    public List<GridManager> getKeyGridManagersByName(String name) {
        return gridManagerDao.getKeyGridManagersByName(name);
    }

    @Override
    public GridManager getGridManagerById(Long id) {
        return gridManagerDao.getGridManagerById(id);
    }

    @Override
    public List<GridManagerVo> getAllGridManagerVos() {
        return gridManagerDao.getAllGridManagerVos();
    }

    @Override
    public List<GridManagerVo> getKeyGridManagerVosByName(String name) {
        return gridManagerDao.getKeyGridManagerVosByName(name);
    }

    @Override
    public GridManagerVo getGridManagerVoById(Long id) {
        return gridManagerDao.getGridManagerVoById(id);
    }
}
