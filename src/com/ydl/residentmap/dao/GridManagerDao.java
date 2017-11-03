package com.ydl.residentmap.dao;

import com.ydl.residentmap.model.GridManager;
import com.ydl.residentmap.model.vo.GridManagerVo;

import java.util.List;

public interface GridManagerDao {
    Boolean save(GridManager gridManager);

    Integer deleteList(List<String> idList);

    Boolean update(GridManager gridManager);

    List<GridManager> getAllGridManagers();

    List<GridManager> getKeyGridManagersByName(String name);

    List<GridManager> getGridManagersByGridRole(Integer gridRole);

    List<GridManager> getGridManagersByMinority(Integer minority);

    List<GridManager> getGridManagersByEducation(Integer education);

    GridManager getGridManagerById(Long id);

    List<GridManagerVo> getAllGridManagerVos();

    List<GridManagerVo> getKeyGridManagerVosByName(String name);

    GridManagerVo getGridManagerVoById(Long id);


}
