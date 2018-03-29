package com.ydl.residentmap.dao;

import com.ydl.residentmap.model.Street;

import java.util.HashMap;
import java.util.List;
import com.ydl.residentmap.model.Pager;

public interface StreetDao {
    Boolean save(Street street);

    List<Street> getAll();

    List<Street> getStreetsByName(String name);

    List<Street> getStreetsByCondition(HashMap<String,String> map);

    Street getStreetById(Long id);

    Integer deleteList(List<String> idList);

    Boolean update(Street street);

    Pager<Street> getStreetsByPage(int offset, int size);
}
