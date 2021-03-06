package com.ydl.residentmap.dao;

import com.ydl.residentmap.model.Community;
import com.ydl.residentmap.model.Pager;
import com.ydl.residentmap.model.vo.BlockVo;
import com.ydl.residentmap.model.vo.CommunityVo;

import java.util.HashMap;
import java.util.List;

public interface CommunityDao {
    Boolean save(Community community);

    Integer deleteList(List<String> idList);

    Boolean update(Community community);

    Community getCommunityById(Long id);

    List<Community> getAllCommunities();

    List<Community> getCommunitiesByName(String name);

    List<Community> getCommunitiesByType(Integer type);

    CommunityVo getCommunityVoById(Long id);

    List<CommunityVo> getAllCommunityVos();

    List<CommunityVo> getCommunityVosByName(String name);

    List<CommunityVo> getCommunitiyVosByCondition(HashMap<String,String> map);

    Pager<Community> getCommunitiesByPage(int offset, int size);

    List<Community> getCommunitiesByStreetId(Long streetId);
}
