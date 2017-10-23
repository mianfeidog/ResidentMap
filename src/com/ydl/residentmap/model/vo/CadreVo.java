package com.ydl.residentmap.model.vo;

import com.ydl.residentmap.model.Cadre;

public class CadreVo extends Cadre {
    private String positionName;
    private String communityName;
    private String educationName;

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getEducationName() {
        return educationName;
    }

    public void setEducationName(String educationName) {
        this.educationName = educationName;
    }
}
