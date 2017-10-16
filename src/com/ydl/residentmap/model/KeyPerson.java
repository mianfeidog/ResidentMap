package com.ydl.residentmap.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 重点人员基本信息
 */
@Entity
@Table(name = "key_person")
public class KeyPerson implements Serializable {

    private static final long serialVersionUID = 8556304036066494897L;

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "age")
    private int age;

    @Column(name = "type")
    private int type;

    @Column(name = "base_condition")
    private String baseCondition;

    @Column(name = "id_card")
    private String idCard;

    @Column(name = "address")
    private String address;

    @Column(name = "link")
    private String link;

    //@Column(name = "street_id")
    //private Long streetId;

    //@Column(name = "community_id")
    //private Long communityId;

    @Column(name = "block_id")
    private Long blockId;

    @Column(name = "building_id")
    private Long buildingId;

    @Column(name = "lng")
    private String lng;

    @Column(name = "lat")
    private String lat;

    @Column(name = "create_at")
    private Long createAt;

    public Long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
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

//    public Long getCommunityId() {
//        return communityId;
//    }
//
//    public void setCommunityId(Long communityId) {
//        this.communityId = communityId;
//    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public Long getBlockId() {
        return blockId;
    }

    public void setBlockId(Long blockId) {
        this.blockId = blockId;
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

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getBaseCondition() {
        return baseCondition;
    }

    public void setBaseCondition(String baseCondition) {
        this.baseCondition = baseCondition;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
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

//    public Long getStreetId() {
//        return streetId;
//    }
//
//    public void setStreetId(Long streetId) {
//        this.streetId = streetId;
//    }

}
