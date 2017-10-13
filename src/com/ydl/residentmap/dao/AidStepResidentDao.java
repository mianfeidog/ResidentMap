package com.ydl.residentmap.dao;

import com.ydl.residentmap.model.AidResident;
import com.ydl.residentmap.model.AidStepResident;

import java.util.List;

/**
 * 帮扶措施与帮扶对象关联
 * Created by 小强 on 2017/7/20.
 */
public interface AidStepResidentDao {
    //保存
    Boolean save(AidStepResident aidStepResident);
    //根据帮扶措施id删除关联
    Boolean deleteByStepId(Long stepId);
    //查询帮扶对象 根据帮扶措施id
    List<AidResident> getAidResidentByStepId(Long stepId);
}
