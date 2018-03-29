package com.ydl.residentmap.service.impl;

import com.ydl.residentmap.constants.ResultMessage;
import com.ydl.residentmap.dao.AssistResidentDao;
import com.ydl.residentmap.dao.BlockDao;
import com.ydl.residentmap.dao.KeyPersonDao;
import com.ydl.residentmap.model.AssistResident;
import com.ydl.residentmap.model.Block;
import com.ydl.residentmap.model.KeyPerson;
import com.ydl.residentmap.model.Pager;
import com.ydl.residentmap.model.vo.BlockVo;
import com.ydl.residentmap.service.BlockService;
import com.ydl.residentmap.util.CommonUtil;
import com.ydl.residentmap.util.IdWorker;
import com.ydl.residentmap.util.LatitudeUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BlockServiceImpl implements BlockService {
    @Resource
    private BlockDao blockDao ;

    @Resource
    private AssistResidentDao assistResidentDao ;

    @Resource
    private KeyPersonDao keyPersonDao ;

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
        Set<String> assistResidentNames = new HashSet<String>();
        Set<String> keyPersonNames = new HashSet<String>();

        for(int i=0;i<idList.size();i++) {
            String blockIdStr = idList.get(i);
            Long blockId = Long.parseLong(blockIdStr);
            //根据所属小区查找帮扶人员
            List<AssistResident> assistResidentList = assistResidentDao.getAssistResidentsByBlockId(blockId);
            if (assistResidentList.size() > 0) {
                for (int j = 0; j < assistResidentList.size(); j++) {
                    assistResidentNames.add(assistResidentList.get(j).getName());
                }
            }
            if(assistResidentNames.size()>0)
            {
                throw new RuntimeException("所删小区已被帮扶人员（"+String.join(",",assistResidentNames)+"）引用，无法删除。");
            }

            //根据所属小区查找重点人员
            List<KeyPerson> keyPersonList=keyPersonDao.getKeyPersonsByBlockId(blockId);
            if (keyPersonList.size() > 0) {
                for (int j = 0; j < keyPersonList.size(); j++) {
                    keyPersonNames.add(keyPersonList.get(j).getName());
                }
            }
            if(keyPersonNames.size()>0)
            {
                throw new RuntimeException("所删小区已被重点人员（"+String.join(",",keyPersonNames)+"）引用，无法删除。");
            }
        }
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
    public List<BlockVo> getBlockVosByCondition(HashMap<String,String> map) {
        return blockDao.getBlockVosByCondition(map);
    }

    @Override
    public Pager<Block> getBlocksByPage(int offset, int size) {
        return blockDao.getBlocksByPage(offset,size);
    }
}
