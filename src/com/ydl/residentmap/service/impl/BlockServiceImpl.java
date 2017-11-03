package com.ydl.residentmap.service.impl;

import com.ydl.residentmap.constants.ResultMessage;
import com.ydl.residentmap.dao.BlockDao;
import com.ydl.residentmap.model.Block;
import com.ydl.residentmap.model.Pager;
import com.ydl.residentmap.model.vo.BlockVo;
import com.ydl.residentmap.service.BlockService;
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
public class BlockServiceImpl implements BlockService {
    @Resource
    private BlockDao blockDao ;

    @Override
    public Boolean save(Block block) {
        Long communityId = block.getCommunityId();
        if(communityId==null)
        {
            throw new RuntimeException("小区的所属社区不能为空！");
        }

        String address = block.getAddress().trim();
        //地址不为空，获取经纬度
        if(!"".equals(address)){
            Map<String,String> lngLat = LatitudeUtils.getGeocoderLatitude(address);
            if(lngLat!=null)
            {
                String lng = lngLat.get("lng");
                String lat = lngLat.get("lat");
                block.setLng(lng);
                block.setLat(lat);
            }
            else
            {
                throw new RuntimeException(ResultMessage.NO_LNG_LAT);
            }
        }

        //创建时间
        Date now = new Date();
        String sdate=(new SimpleDateFormat("yyyyMMddHHmm")).format(now);
        Long dateLong = Long.parseLong(sdate);
        block.setCreateAt(dateLong);

        Random random = new Random();
        block.setId(new IdWorker((long)random.nextInt(15)).nextId());
        return blockDao.save(block);
    }

    @Override
    public Integer deleteList(List<String> idList) {
        return blockDao.deleteList(idList);
    }

    @Override
    public Boolean update(Block block) {
        Long communityId = block.getCommunityId();
        if(communityId==null)
        {
            throw new RuntimeException("小区的所属社区不能为空！");
        }

        String address = block.getAddress().trim();
        //地址不为空，获取经纬度
        if(!"".equals(address)){
            Map<String,String> lngLat = LatitudeUtils.getGeocoderLatitude(address);
            if(lngLat!=null)
            {
                String lng = lngLat.get("lng");
                String lat = lngLat.get("lat");
                block.setLng(lng);
                block.setLat(lat);
            }
            else
            {
                throw new RuntimeException(ResultMessage.NO_LNG_LAT);
            }
        }

        //创建时间
        Date now = new Date();
        String sdate=(new SimpleDateFormat("yyyyMMddHHmm")).format(now);
        Long dateLong = Long.parseLong(sdate);
        block.setCreateAt(dateLong);

        return blockDao.update(block);
    }

    @Override
    public Block getBlockById(Long id) {
        return blockDao.getBlockById(id);
    }

    @Override
    public List<Block> getAllBlocks() {
        return blockDao.getAllBlocks();
    }

    @Override
    public List<Block> getBlocksByName(String name) {
        return blockDao.getBlocksByName(name);
    }

    @Override
    public BlockVo getBlockVoById(Long id) {
        return blockDao.getBlockVoById(id);
    }

    @Override
    public List<BlockVo> getAllBlockVos() {
        return blockDao.getAllBlockVos();
    }

    @Override
    public List<BlockVo> getBlockVosByName(String name) {
        return blockDao.getBlockVosByName(name);
    }

    @Override
    public Pager<Block> getBlocksByPage(int offset, int size) {
        return blockDao.getBlocksByPage(offset,size);
    }
}
