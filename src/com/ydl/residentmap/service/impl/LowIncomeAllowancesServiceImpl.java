package com.ydl.residentmap.service.impl;

import com.ydl.residentmap.dao.LowIncomeAllowancesDao;
import com.ydl.residentmap.model.LowIncomeAllowances;
import com.ydl.residentmap.service.LowIncomeAllowancesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LowIncomeAllowancesServiceImpl implements LowIncomeAllowancesService {

    @Resource
    private LowIncomeAllowancesDao lowIncomeAllowancesDao ;

    @Override
    public Boolean save(LowIncomeAllowances lowIncomeAllowances) {
        return lowIncomeAllowancesDao.save(lowIncomeAllowances);
    }

    @Override
    public Integer deleteList(List<String> idList) {
        return lowIncomeAllowancesDao.deleteList(idList);
    }

    @Override
    public Boolean update(LowIncomeAllowances lowIncomeAllowances) {
        return lowIncomeAllowancesDao.update(lowIncomeAllowances);
    }

    @Override
    public LowIncomeAllowances getLowIncomeAllowancesById(Long id) {
        return lowIncomeAllowancesDao.getLowIncomeAllowancesById(id);
    }

    @Override
    public List<LowIncomeAllowances> getAllLowIncomeAllowances() {
        return lowIncomeAllowancesDao.getAllLowIncomeAllowances();
    }

    @Override
    public List<LowIncomeAllowances> getLowIncomeAllowancesByName(String name) {
        return lowIncomeAllowancesDao.getLowIncomeAllowancesByName(name);
    }
}
