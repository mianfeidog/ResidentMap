package com.ydl.residentmap.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 帮扶措施 和 帮扶对象 关联表
 * Created by 小强 on 2017/7/20.
 */
@Entity
@Table(name = "aid_step_resident")
public class AidStepResident {
    @Id
    private Long id;

    //帮扶对象id
    @Column(name = "resident_id")
    private Long residentId;
    //帮扶措施id
    @Column(name = "step_id")
    private Long aidStepId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getResidentId() {
        return residentId;
    }

    public void setResidentId(Long residentId) {
        this.residentId = residentId;
    }

    public Long getAidStepId() {
        return aidStepId;
    }

    public void setAidStepId(Long aidStepId) {
        this.aidStepId = aidStepId;
    }
}
