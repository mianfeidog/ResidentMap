package com.ydl.residentmap.dao;

import com.ydl.residentmap.model.Block;
import com.ydl.residentmap.model.Pager;
import com.ydl.residentmap.model.vo.BlockVo;

import java.util.List;

public interface BlockDao {
    Boolean save(Block block);

    Integer deleteList(List<String> idList);

    Boolean update(Block block);

    Block getBlockById(Long id);

    List<Block> getAllBlocks();

    List<Block> getBlocksByName(String name);

    BlockVo getBlockVoById(Long id);

    List<BlockVo> getAllBlockVos();

    List<BlockVo> getBlockVosByName(String name);

    List<BlockVo> getBlockVosByCommunityId(Long communityId);

    Pager<Block> getBlocksByPage(int offset, int size);
}
