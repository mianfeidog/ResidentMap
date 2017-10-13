package com.ydl.residentmap.service.impl;

import com.ydl.residentmap.dao.CadreDao;
import com.ydl.residentmap.dao.DeformityDao;
import com.ydl.residentmap.model.Deformity;
import com.ydl.residentmap.service.DeformityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DeformityServiceImpl implements DeformityService {
    @Resource
    private DeformityDao deformityDao ;

    @Override
    public Boolean save(Deformity deformity) {
        return deformityDao.save(deformity);
    }

    @Override
    public Integer deleteList(List<String> idList) {
        return deformityDao.deleteList(idList);
    }

    @Override
    public Boolean update(Deformity deformity) {
        return deformityDao.update(deformity);
    }

    @Override
    public List<Deformity> getAllDeformities() {
        return deformityDao.getAllDeformities();
    }

    @Override
    public List<Deformity> getDeformitiesByName(String name) {
        return deformityDao.getDeformitiesByName(name);
    }

    @Override
    public Deformity getDeformityById(Long id) {
        return deformityDao.getDeformityById(id);
    }
}
