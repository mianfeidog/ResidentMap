package com.ydl.residentmap.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 三长三员网格化管理人员
 */
@Entity
@Table(name = "grid_manager")
public class GridManager implements Serializable {

    private static final long serialVersionUID = 8556304036066494813L;

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "grid_num")
    private Integer gridNum;

    @Column(name = "grid_name")
    private String gridName;

    @Column(name = "grid_role")
    private Integer gridRole;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "minority")
    private Integer minority;

    @Column(name = "birthday")
    private Long birthday;

    @Column(name = "education")
    private Integer education;

    @Column(name = "party_member")
    private Boolean partyMember;

    @Column(name = "address")
    private String address;

    @Column(name = "link")
    private String link;

    @Column(name = "community_id")
    private Long communityId;

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

    public Long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGridNum() {
        return gridNum;
    }

    public void setGridNum(Integer gridNum) {
        this.gridNum = gridNum;
    }

    public String getGridName() {
        return gridName;
    }

    public void setGridName(String gridName) {
        this.gridName = gridName;
    }

    public Integer getGridRole() {
        return gridRole;
    }

    public void setGridRole(Integer gridRole) {
        this.gridRole = gridRole;
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

    public Integer getMinority() {
        return minority;
    }

    public void setMinority(Integer minority) {
        this.minority = minority;
    }

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public Integer getEducation() {
        return education;
    }

    public void setEducation(Integer education) {
        this.education = education;
    }

    public Boolean getPartyMember() {
        return partyMember;
    }

    public void setPartyMember(Boolean partyMember) {
        this.partyMember = partyMember;
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
}
