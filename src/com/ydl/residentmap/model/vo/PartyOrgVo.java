package com.ydl.residentmap.model.vo;

import com.ydl.residentmap.model.PartyOrg;

public class PartyOrgVo extends PartyOrg {
    private String orgSystemName;
    private String orgAttributeName;
    private String communityName;

    public String getOrgSystemName() {
        return orgSystemName;
    }

    public void setOrgSystemName(String orgSystemName) {
        this.orgSystemName = orgSystemName;
    }

    public String getOrgAttributeName() {
        return orgAttributeName;
    }

    public void setOrgAttributeName(String orgAttributeName) {
        this.orgAttributeName = orgAttributeName;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }
}
