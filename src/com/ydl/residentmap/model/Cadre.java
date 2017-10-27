package com.ydl.residentmap.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 社区干部基本信息
 */
@Entity
@Table(name = "cadre")
public class Cadre implements Serializable {

    private static final long serialVersionUID = 8556304036066494817L;

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "position")
    private Integer position;

    @Column(name = "position_begin_date")
    private Long positionBeginDate;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "birthday")
    private Long birthday;

    @Column(name = "join_date")
    private Long joinDate;

    @Column(name = "address")
    private String address;

    @Column(name = "link")
    private String link;

    @Column(name = "community_id")
    private Long communityId;

    @Column(name = "education")
    private Integer education;

    @Column(name = "id_card")
    private String idCard;

    @Column(name = "create_at")
    private Long createAt;

    @Column(name = "lng")
    private String lng;

    @Column(name = "lat")
    private String lat;

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

    public Long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }

    public Integer getEducation() {
        return education;
    }

    public void setEducation(Integer education) {
        this.education = education;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Long getPositionBeginDate() {
        return positionBeginDate;
    }

    public void setPositionBeginDate(Long positionBeginDate) {
        this.positionBeginDate = positionBeginDate;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public Long getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Long joinDate) {
        this.joinDate = joinDate;
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

    public Long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
    }
}
