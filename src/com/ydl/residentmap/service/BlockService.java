package com.ydl.residentmap.service;

import com.ydl.residentmap.model.Block;
import com.ydl.residentmap.model.Pager;
import com.ydl.residentmap.model.vo.BlockVo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.HashMap;
import java.util.List;

public interface BlockService {
    Boolean save(Block block);

    Integer deleteList(List<String> idList);

    Boolean update(Block block);

    Block getBlockById(Long id);

    List<Block> getAllBlocks();

    List<Block> getBlocksByName(String name);

    BlockVo getBlockVoById(Long id);

    List<BlockVo> getAllBlockVos();

    List<BlockVo> getBlockVosByName(String name);

    List<BlockVo> getBlockVosByCondition(HashMap<String,String> map);

    HSSFWorkbook exportExcel(List<BlockVo> blockVos);

    Pager<Block> getBlocksByPage(int offset, int size);


}
