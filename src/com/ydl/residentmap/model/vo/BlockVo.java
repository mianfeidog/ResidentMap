package com.ydl.residentmap.model.vo;

import com.ydl.residentmap.model.Block;

public class BlockVo extends Block {
    private String streetName;

    private String communityName;

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
}
