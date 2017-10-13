package com.ydl.residentmap.service;

import com.ydl.residentmap.model.GridManager;

import java.util.List;

public interface GridManagerService {
    Boolean save(GridManager gridManager);

    Integer deleteList(List<String> idList);

    Boolean update(GridManager gridManager);

    List<GridManager> getAllGridManagers();

    List<GridManager> getKeyGridManagersByName(String name);

    GridManager getGridManagerById(Long id);
}
