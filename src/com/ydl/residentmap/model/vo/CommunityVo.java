package com.ydl.residentmap.model.vo;

import com.ydl.residentmap.model.Community;

public class CommunityVo extends Community{
    private String streetName;

    private String typeName;

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
