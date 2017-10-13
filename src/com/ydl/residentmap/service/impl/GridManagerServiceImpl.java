package com.ydl.residentmap.service.impl;

import com.ydl.residentmap.dao.GridManagerDao;
import com.ydl.residentmap.model.GridManager;
import com.ydl.residentmap.service.GridManagerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GridManagerServiceImpl implements GridManagerService {
    @Resource
    private GridManagerDao gridManagerDao ;

    @Override
    public Boolean save(GridManager gridManager) {
        return gridManagerDao.save(gridManager);
    }

    @Override
    public Integer deleteList(List<String> idList) {
        return gridManagerDao.deleteList(idList);
    }

    @Override
    public Boolean update(GridManager gridManager) {
        return gridManagerDao.update(gridManager);
    }

    @Override
    public List<GridManager> getAllGridManagers() {
        return gridManagerDao.getAllGridManagers();
    }

    @Override
    public List<GridManager> getKeyGridManagersByName(String name) {
        return gridManagerDao.getKeyGridManagersByName(name);
    }

    @Override
    public GridManager getGridManagerById(Long id) {
        return gridManagerDao.getGridManagerById(id);
    }
}