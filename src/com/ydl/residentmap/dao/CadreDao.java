package com.ydl.residentmap.dao;

import com.ydl.residentmap.model.Cadre;
import com.ydl.residentmap.model.vo.CadreVo;

import java.util.HashMap;
import java.util.List;

public interface CadreDao {
    Boolean save(Cadre cadre);

    Integer deleteList(List<String> idList);

    Boolean update(Cadre cadre);

    List<Cadre> getAllCadres();

    List<Cadre> getCadresByName(String name);

    List<Cadre> getCadresByCommunityId(Long communityId);

    List<Cadre> getCadresByEducation(Integer education);

    List<Cadre> getCadresByPosition(Integer position);

    List<Cadre> getCadresByIdCard(String idCard,String action,Long id);

    Cadre getCadreById(Long id);

    List<CadreVo> getAllCadreVos();

    List<CadreVo> getCadreVosByName(String name);

    List<CadreVo> getCadreVosByCondition(HashMap<String,String> map);

    CadreVo getCadreVoById(Long id);
}
