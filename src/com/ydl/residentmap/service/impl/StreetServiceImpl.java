package com.ydl.residentmap.service.impl;

import com.ydl.residentmap.dao.StreetDao;
import com.ydl.residentmap.model.Pager;
import com.ydl.residentmap.model.Street;
import com.ydl.residentmap.service.StreetService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StreetServiceImpl implements StreetService{
    @Resource
    private StreetDao streetDaoDao ;

    @Override
    public Boolean save(Street street) {
        return streetDaoDao.save(street);
    }

    @Override
    public List<Street> getAll() {
        return streetDaoDao.getAll();
    }

    @Override
    public List<Street> getStreetsByName(String name) {
        return streetDaoDao.getStreetsByName(name);
    }

    @Override
    public Integer deleteList(List<String> idList) {
        return streetDaoDao.deleteList(idList);
    }

    @Override
    public Boolean update(Street street) {
        return streetDaoDao.update(street);
    }

    @Override
    public Pager<Street> getStreetsByPage(int offset, int size) {
        return streetDaoDao.getStreetsByPage(offset,size);
    }
}
