package com.ydl.residentmap.dao;

import com.ydl.residentmap.model.Pager;
import com.ydl.residentmap.model.KeyPerson;
import com.ydl.residentmap.model.vo.KeyPersonVo;

import java.util.List;

public interface KeyPersonDao {
    Boolean save(KeyPerson keyPerson);

    Integer deleteList(List<String> idList);

    Boolean update(KeyPerson keyPerson);

    KeyPersonVo getKeyPersonVoById(Long id);

    KeyPerson getKeyPersonById(Long id);

    List<KeyPerson> getAllKeyPersons();

    List<KeyPerson> getKeyPersonsByName(String name);

    List<KeyPerson> getKeyPersonsByBlockId(Long blockId);

    List<KeyPerson> getKeyPersonsByType(int type);

    List<KeyPersonVo> getKeyPersonVosByName(String name);

    List<KeyPersonVo> getKeyPersonVosByType(int type);

    List<KeyPersonVo> getKeyPersonVosByTypes(List<String> typeList);

    //根据居民类型与居民姓名查找
    List<KeyPersonVo> getKeyPersonVosByTypesName(List<String> typeList,String name);

    Pager<KeyPerson> getKeyPersonsByPage(int offset, int size);

    List<KeyPersonVo> getKeyPersonVosByPage(int offset, int size);

    List<KeyPersonVo> getAllKeyPersonVos();

    List<KeyPerson> getKeyPersonsByIdCard(String idCard,String action,Long id);
}
