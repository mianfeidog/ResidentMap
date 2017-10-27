package com.ydl.residentmap.service;

import com.ydl.residentmap.model.Cadre;
import com.ydl.residentmap.model.vo.CadreVo;

import java.util.List;

public interface CadreService  {
    Boolean save(Cadre cadre);

    Integer deleteList(List<String> idList);

    Boolean update(Cadre cadre);

    List<Cadre> getAllCadres();

    List<Cadre> getCadresByName(String name);

    Cadre getCadreById(Long id);

    List<CadreVo> getAllCadreVos();

    List<CadreVo> getCadreVosByName(String name);

    CadreVo getCadreVoById(Long id);

    List<Cadre> getCadresByIdCard(String idCard,String action,Long id);
}
