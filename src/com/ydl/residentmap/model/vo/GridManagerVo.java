package com.ydl.residentmap.model.vo;

import com.ydl.residentmap.model.GridManager;

public class GridManagerVo extends GridManager{
    private String gridRoleName;
    private String minorityName;
    private String educationName;
    private String communityName;

    public String getGridRoleName() {
        return gridRoleName;
    }

    public void setGridRoleName(String gridRoleName) {
        this.gridRoleName = gridRoleName;
    }

    public String getMinorityName() {
        return minorityName;
    }

    public void setMinorityName(String minorityName) {
        this.minorityName = minorityName;
    }

    public String getEducationName() {
        return educationName;
    }

    public void setEducationName(String educationName) {
        this.educationName = educationName;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }
}
