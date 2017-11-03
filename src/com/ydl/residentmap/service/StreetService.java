package com.ydl.residentmap.service;

import com.ydl.residentmap.model.Pager;
import com.ydl.residentmap.model.Street;

import java.util.List;

public interface StreetService {
    Boolean save(Street street);

    List<Street> getAll();

    List<Street> getStreetsByName(String name);

    Street getStreetById(Long id);

    Integer deleteList(List<String> idList);

    Boolean update(Street street);

    Pager<Street> getStreetsByPage(int offset, int size);
}
