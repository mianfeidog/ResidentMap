package com.ydl.residentmap.service;

import com.ydl.residentmap.model.LowIncomeAllowances;

import java.util.List;

public interface LowIncomeAllowancesService {
    Boolean save(LowIncomeAllowances lowIncomeAllowances);

    Integer deleteList(List<String> idList);

    Boolean update(LowIncomeAllowances lowIncomeAllowances);

    LowIncomeAllowances getLowIncomeAllowancesById(Long id);

    List<LowIncomeAllowances> getAllLowIncomeAllowances();

    List<LowIncomeAllowances> getLowIncomeAllowancesByName(String name);
}
