package com.ydl.residentmap.service;

import com.ydl.residentmap.model.AssistResident;
import com.ydl.residentmap.model.vo.AssistResidentVo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.HashMap;
import java.util.List;

public interface AssistResidentService {
    Boolean save(AssistResident assistResident);

    Integer deleteList(List<String> idList);

    Boolean update(AssistResident lowIncomeAllowances);

    List<AssistResident> getAllAssistResidents();

    List<AssistResident> getAssistResidentsByName(String name);

    List<AssistResident> getAssistResidentsByTypes(List<String> types);

    AssistResident getAssistResidentById(Long id);

    List<AssistResidentVo> getAllAssistResidentVos();

    List<AssistResidentVo> getAssistResidentVosByName(String name);

    List<AssistResidentVo> getAssistResidentVosByCondition(HashMap<String,String> map);

    HSSFWorkbook exportExcel(List<AssistResidentVo> assistResidentVos);

    List<AssistResidentVo> getAssistResidentVosByTypes(List<String> types);

    List<AssistResidentVo> getAssistResidentVosByTypesName(List<String> types,String name);

    AssistResidentVo getAssistResidentVoById(Long id);
}
