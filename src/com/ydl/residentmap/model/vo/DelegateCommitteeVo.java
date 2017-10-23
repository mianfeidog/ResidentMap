package com.ydl.residentmap.model.vo;

import com.ydl.residentmap.model.DelegateCommittee;

public class DelegateCommitteeVo extends DelegateCommittee {
    private String minorityName;
    private String educationName;
    private String partyName;
    private String communityName;

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

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }
}
