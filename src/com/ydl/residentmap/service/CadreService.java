package com.ydl.residentmap.service;

import com.ydl.residentmap.model.Cadre;

import java.util.List;

public interface CadreService  {
    Boolean save(Cadre cadre);

    Integer deleteList(List<String> idList);

    Boolean update(Cadre cadre);

    List<Cadre> getAllCadres();

    List<Cadre> getCadresByName(String name);

    Cadre getCadreById(Long id);
}
