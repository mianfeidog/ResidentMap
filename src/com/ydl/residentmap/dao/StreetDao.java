package com.ydl.residentmap.dao;

import com.ydl.residentmap.model.Street;
import java.util.List;
import com.ydl.residentmap.model.Pager;

public interface StreetDao {
    Boolean save(Street street);

    List<Street> getAll();

    List<Street> getStreetsByName(String name);

    Street getStreetById(Long id);

    Integer deleteList(List<String> idList);

    Boolean update(Street street);

    Pager<Street> getStreetsByPage(int offset, int size);
}
