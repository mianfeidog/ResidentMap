package com.ydl.residentmap.service.impl;

import com.ydl.residentmap.constants.ResultMessage;
import com.ydl.residentmap.dao.BlockDao;
import com.ydl.residentmap.dao.CommunityDao;
import com.ydl.residentmap.model.Community;
import com.ydl.residentmap.model.Pager;
import com.ydl.residentmap.model.vo.BlockVo;
import com.ydl.residentmap.model.vo.CommunityVo;
import com.ydl.residentmap.service.CommunityService;
import com.ydl.residentmap.util.IdWorker;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class CommunityServiceImpl implements CommunityService {
    @Resource
    private CommunityDao communityDao ;

    @Resource
    private BlockDao blockDao;

    @Override
    public Boolean save(Community community) {
        Long streetId = community.getStreetId();
        if(streetId==null)
        {
            throw new RuntimeException(ResultMessage.EMPTY_STREET_ID);
        }

        //创建时间
        Date now = new Date();
        String sdate=(new SimpleDateFormat("yyyyMMddHHmm")).format(now);
        Long dateLong = Long.parseLong(sdate);
        community.setCreateAt(dateLong);

        Random random = new Random();
        community.setId(new IdWorker((long)random.nextInt(15)).nextId());

        return communityDao.save(community);
    }

    @Override
    public Integer deleteList(List<String> idList) {
        return communityDao.deleteList(idList);
    }

    @Override
    public Boolean update(Community community) {
        Long streetId = community.getStreetId();
        if(streetId==null)
        {
            throw new RuntimeException(ResultMessage.EMPTY_STREET_ID);
        }

        //创建时间
        Date now = new Date();
        String sdate=(new SimpleDateFormat("yyyyMMddHHmm")).format(now);
        Long dateLong = Long.parseLong(sdate);
        community.setCreateAt(dateLong);

        return communityDao.update(community);
    }

    @Override
    public Community getKeyCommunityById(Long id) {
        return communityDao.getCommunityById(id);
    }

    @Override
    public List<Community> getAllCommunities() {
        return communityDao.getAllCommunities();
    }

    @Override
    public List<Community> getCommunitiesByName(String name) {
        return communityDao.getCommunitiesByName(name);
    }

    @Override
    public CommunityVo getCommunityVoById(Long id) {
        return communityDao.getCommunityVoById(id);
    }

    @Override
    public List<CommunityVo> getAllCommunityVos() {
        return communityDao.getAllCommunityVos();
    }

    @Override
    public List<CommunityVo> getCommunityVosByName(String name) {
        return communityDao.getCommunityVosByName(name);
    }

    @Override
    public List<BlockVo> getBlockVosByCommunityId(Long communityId) {
        return blockDao.getBlockVosByCommunityId(communityId);
    }

    @Override
    public Pager<Community> getCommunitiesByPage(int offset, int size) {
        return communityDao.getCommunitiesByPage(offset,size);
    }
}
