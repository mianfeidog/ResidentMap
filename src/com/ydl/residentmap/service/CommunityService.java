package com.ydl.residentmap.service;

import com.ydl.residentmap.model.Community;
import com.ydl.residentmap.model.Pager;
import com.ydl.residentmap.model.vo.BlockVo;
import com.ydl.residentmap.model.vo.CommunityVo;

import java.util.List;

public interface CommunityService {
    Boolean save(Community community);

    Integer deleteList(List<String> idList);

    Boolean update(Community community);

    Community getKeyCommunityById(Long id);

    List<Community> getAllCommunities();

    List<Community> getCommunitiesByName(String name);

    CommunityVo getCommunityVoById(Long id);

    List<CommunityVo> getAllCommunityVos();

    List<CommunityVo> getCommunityVosByName(String name);

    List<BlockVo> getBlockVosByCommunityId(Long communityId);

    Pager<Community> getCommunitiesByPage(int offset, int size);
}
