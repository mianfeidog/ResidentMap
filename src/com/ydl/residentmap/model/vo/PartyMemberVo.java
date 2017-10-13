package com.ydl.residentmap.model.vo;

import com.ydl.residentmap.model.PartyMember;

public class PartyMemberVo extends PartyMember {
    private String streetName;

    private String communityName;

    private String minorityName;

    private String educationName;

    private String claimPostName;

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

    public String getClaimPostName() {
        return claimPostName;
    }

    public void setClaimPostName(String claimPostName) {
        this.claimPostName = claimPostName;
    }

    //    private String blockName;
//
//    private String buildingName;

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

//    public String getBlockName() {
//        return blockName;
//    }
//
//    public void setBlockName(String blockName) {
//        this.blockName = blockName;
//    }
//
//    public String getBuildingName() {
//        return buildingName;
//    }
//
//    public void setBuildingName(String buildingName) {
//        this.buildingName = buildingName;
//    }
}
