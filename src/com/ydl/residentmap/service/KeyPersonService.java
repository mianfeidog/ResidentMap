package com.ydl.residentmap.service;

import com.ydl.residentmap.model.KeyPerson;
import com.ydl.residentmap.model.Pager;
import com.ydl.residentmap.model.vo.KeyPersonVo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.HashMap;
import java.util.List;

public interface KeyPersonService {
    Boolean save(KeyPerson keyPerson);

    Integer deleteList(List<String> idList);

    Boolean update(KeyPerson keyPerson);

    KeyPerson getKeyPersonById(Long id);

    List<KeyPerson> getAllKeyPersons();

    List<KeyPerson> getKeyPersonsByName(String name);

    List<KeyPerson> getKeyPersonsByType(int type);

    List<KeyPersonVo> getKeyPersonVosByName(String name);

    List<KeyPersonVo> getKeyPersonVosByCondition(HashMap<String,String> map);

    HSSFWorkbook exportExcel(List<KeyPersonVo> keyPersonVos);

    KeyPersonVo getKeyPersonVoById(Long id);

    List<KeyPersonVo> getKeyPersonVosByType(int type);

    List<KeyPersonVo> getKeyPersonVosByTypes(List<String> typeList);

    List<KeyPersonVo> getKeyPersonVosByTypesName(List<String> typeList,String name);

    Pager<KeyPerson> getKeyPersonsByPage(int offset, int size);

    List<KeyPersonVo> getKeyPersonVosByPage(int offset, int size);

    List<KeyPersonVo> getAllKeyPersonVos();

    List<KeyPerson> getKeyPersonsByIdCard(String idCard,String action,Long id);
}
