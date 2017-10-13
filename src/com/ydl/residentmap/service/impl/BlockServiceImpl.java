package com.ydl.residentmap.service.impl;

import com.ydl.residentmap.dao.BlockDao;
import com.ydl.residentmap.model.Block;
import com.ydl.residentmap.model.Pager;
import com.ydl.residentmap.model.vo.BlockVo;
import com.ydl.residentmap.service.BlockService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BlockServiceImpl implements BlockService {
    @Resource
    private BlockDao blockDao ;

    @Override
    public Boolean save(Block block) {
        return blockDao.save(block);
    }

    @Override
    public Integer deleteList(List<String> idList) {
        return blockDao.deleteList(idList);
    }

    @Override
    public Boolean update(Block block) {
        return blockDao.update(block);
    }

    @Override
    public Block getBlockById(Long id) {
        return blockDao.getBlockById(id);
    }

    @Override
    public List<Block> getAllBlocks() {
        return blockDao.getAllBlocks();
    }

    @Override
    public List<Block> getBlocksByName(String name) {
        return blockDao.getBlocksByName(name);
    }

    @Override
    public BlockVo getBlockVoById(Long id) {
        return blockDao.getBlockVoById(id);
    }

    @Override
    public List<BlockVo> getAllBlockVos() {
        return blockDao.getAllBlockVos();
    }

    @Override
    public List<BlockVo> getBlockVosByName(String name) {
        return blockDao.getBlockVosByName(name);
    }

    @Override
    public Pager<Block> getBlocksByPage(int offset, int size) {
        return blockDao.getBlocksByPage(offset,size);
    }
}
