package com.ydl.residentmap.model.vo;

import com.ydl.residentmap.model.CommitteeMember;

public class CommitteeMemberVo extends CommitteeMember{
    private String positionName;

    private String minorityName;

    private String educationName;

    private String communityName;

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
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
