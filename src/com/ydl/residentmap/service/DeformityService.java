package com.ydl.residentmap.service;

import com.ydl.residentmap.model.Deformity;

import java.util.List;

public interface DeformityService {
    Boolean save(Deformity deformity);

    Integer deleteList(List<String> idList);

    Boolean update(Deformity deformity);

    List<Deformity> getAllDeformities();

    List<Deformity> getDeformitiesByName(String name);

    Deformity getDeformityById(Long id);
}
