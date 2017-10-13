package com.ydl.residentmap.dao;

import com.ydl.residentmap.model.AidStep;

import java.util.List;

/**
 * 帮扶措施
 * Created by 小强 on 2017/7/20.
 */
public interface AidStepDao {
    //保存
    Boolean save(AidStep aidStep);
    //更新
    Boolean update(AidStep aidStep);
    //删除
    Boolean delete(Long id);
    //查询所有
    List<AidStep> getAll();
    //查询单个帮扶措施
    AidStep get(Long id);
}
