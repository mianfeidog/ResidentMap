package com.ydl.residentmap.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 两代表一委员基本信息
 */
@Entity
@Table(name = "delegate_committee")
public class DelegateCommittee implements Serializable {

    private static final long serialVersionUID = 8556304036066494818L;

    @Id
    @Column(name = "id")
    private Long id;

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

    @Column(name = "party")
    private Integer party;

    @Column(name = "appoint_post")
    private String appointPost;

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

    public Integer getParty() {
        return party;
    }

    public void setParty(Integer party) {
        this.party = party;
    }

    public String getAppointPost() {
        return appointPost;
    }

    public void setAppointPost(String appointPost) {
        this.appointPost = appointPost;
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
