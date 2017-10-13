package com.ydl.residentmap.model.vo;

import com.ydl.residentmap.model.KeyPerson;

public class MergeResidentVo {
    private Long id;

    private String name;

    private String address;

    private String link;

    private int dataType;

    private int type;

    private String typeName;

    private String lng;

    private String lat;

    private String blockName;

    private Long blockId;

    private Long buildingId;

    private String buildingName;

    private int gender;

    public MergeResidentVo(KeyPersonVo keyPersonVo)
    {
        this.id=keyPersonVo.getId();
        this.name=keyPersonVo.getName();
        this.address=keyPersonVo.getAddress();
        this.link=keyPersonVo.getLink();
        this.type=keyPersonVo.getType();
        this.typeName=keyPersonVo.getTypeName();
        this.lng=keyPersonVo.getLng();
        this.lat=keyPersonVo.getLat();
        this.blockId=keyPersonVo.getBlockId()==null?0:keyPersonVo.getBlockId();
        this.blockName=keyPersonVo.getBlockName();
        this.buildingId=keyPersonVo.getBuildingId()==null?0:keyPersonVo.getBuildingId();
        this.buildingName=keyPersonVo.getBuildingName();
        this.gender=(keyPersonVo.getGender()==null?1:keyPersonVo.getGender());
    }

    public MergeResidentVo(AssistResidentVo assistResidentVo)
    {
        this.id=assistResidentVo.getId();
        this.name=assistResidentVo.getName();
        this.address=assistResidentVo.getAddress();
        this.link=assistResidentVo.getLink();
        this.type=assistResidentVo.getType();
        this.typeName=assistResidentVo.getTypeName();
        this.lng=assistResidentVo.getLng();
        this.lat=assistResidentVo.getLat();
        this.blockId=assistResidentVo.getBlockId()==null?0:assistResidentVo.getBlockId();
        this.blockName=assistResidentVo.getBlockName();
        this.buildingId=assistResidentVo.getBuildingId()==null?0:assistResidentVo.getBuildingId();
        this.buildingName=assistResidentVo.getBuildingName();
        this.gender=(assistResidentVo.getGender()==null?1:assistResidentVo.getGender());
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public Long getBlockId() {
        return blockId;
    }

    public void setBlockId(Long blockId) {
        this.blockId = blockId;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}
