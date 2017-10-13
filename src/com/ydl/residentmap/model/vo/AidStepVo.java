package com.ydl.residentmap.model.vo;

import com.ydl.residentmap.model.AidResident;
import com.ydl.residentmap.model.AidStep;

import java.util.List;

/**
 * 返回APP的帮扶措施
 * Created by 小强 on 2017/7/20.
 */
public class AidStepVo extends AidStep {
    //帮扶对象列表
    private List<AidResident> residentList;

    public List<AidResident> getResidentList() {
        return residentList;
    }

    public void setResidentList(List<AidResident> residentList) {
        this.residentList = residentList;
    }
}
