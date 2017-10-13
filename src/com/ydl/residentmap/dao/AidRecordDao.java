package com.ydl.residentmap.dao;

import com.ydl.residentmap.model.AidRecord;
import com.ydl.residentmap.model.vo.AidRecordVo;

import java.util.List;

/**
 * 帮扶记录
 * Created by 小强 on 2017/7/19.
 */
public interface AidRecordDao {
    Boolean save(AidRecord aidRecord);

    Boolean update(AidRecord aidRecord);

    Boolean delete(Long id);

    //获取某个用户的帮扶记录
    List<AidRecordVo> getAidRecordsByUserId(Long userId);
    //根据id获取帮扶记录
    AidRecordVo getVo(Long id) throws Exception;
    //根据id获取帮扶记录
    AidRecord get(Long id);
}
