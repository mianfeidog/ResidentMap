package com.ydl.residentmap.service.impl;

import com.ydl.residentmap.dao.CadreDao;
import com.ydl.residentmap.model.Cadre;
import com.ydl.residentmap.service.CadreService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CadreServiceImpl implements CadreService{
    @Resource
    private CadreDao cadreDao ;

    @Override
    public Boolean save(Cadre cadre) {
        return cadreDao.save(cadre);
    }

    @Override
    public Integer deleteList(List<String> idList) {
        return cadreDao.deleteList(idList);
    }

    @Override
    public Boolean update(Cadre cadre) {
        return cadreDao.update(cadre);
    }

    @Override
    public List<Cadre> getAllCadres() {
        return cadreDao.getAllCadres();
    }

    @Override
    public List<Cadre> getCadresByName(String name) {
        return cadreDao.getCadresByName(name);
    }

    @Override
    public Cadre getCadreById(Long id) {
        return cadreDao.getCadreById(id);
    }
}
