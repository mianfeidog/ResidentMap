package com.ydl.residentmap.service;

import com.ydl.residentmap.model.Pager;
import com.ydl.residentmap.model.Street;

import java.util.HashMap;
import java.util.List;

public interface StreetService {
    Boolean save(Street street);

    List<Street> getAll();

    List<Street> getStreetsByName(String name);

    List<Street> getStreetsByCondition(HashMap<String,String> map);

    Street getStreetById(Long id);

    Integer deleteList(List<String> idList);

    Boolean update(Street street);

    Pager<Street> getStreetsByPage(int offset, int size);
}
