package com.ydl.residentmap.service;

import com.ydl.residentmap.model.GridManager;
import com.ydl.residentmap.model.vo.GridManagerVo;

import java.util.HashMap;
import java.util.List;

public interface GridManagerService {
    Boolean save(GridManager gridManager);

    Integer deleteList(List<String> idList);

    Boolean update(GridManager gridManager);

    List<GridManager> getAllGridManagers();

    List<GridManager> getKeyGridManagersByName(String name);

    GridManager getGridManagerById(Long id);

    List<GridManagerVo> getAllGridManagerVos();

    List<GridManagerVo> getKeyGridManagerVosByName(String name);

    List<GridManagerVo> getKeyGridManagerVosByCondition(HashMap<String,String> map);

    GridManagerVo getGridManagerVoById(Long id);
}
