package com.ydl.residentmap.service;

import com.ydl.residentmap.model.Cadre;
import com.ydl.residentmap.model.vo.CadreVo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.HashMap;
import java.util.List;

public interface CadreService  {
    Boolean save(Cadre cadre);

    Integer deleteList(List<String> idList);

    Boolean update(Cadre cadre);

    List<Cadre> getAllCadres();

    List<Cadre> getCadresByName(String name);

    Cadre getCadreById(Long id);

    List<CadreVo> getAllCadreVos();

    List<CadreVo> getCadreVosByName(String name);

    List<CadreVo> getCadreVosByCondition(HashMap<String,String> map);

    HSSFWorkbook exportExcel(List<CadreVo> cadreVos);

    CadreVo getCadreVoById(Long id);

    List<Cadre> getCadresByIdCard(String idCard,String action,Long id);
}
