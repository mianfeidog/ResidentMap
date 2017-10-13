package com.ydl.residentmap.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 党员基本信息
 */
@Entity
@Table(name = "party_member")
public class PartyMember implements Serializable {

    private static final long serialVersionUID = 8556304036066494897L;

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

    @Column(name = "join_date")
    private Long joinDate;

    @Column(name = "claim_post")
    private Integer claimPost;

    @Column(name = "id_card")
    private String idCard;

    @Column(name = "address")
    private String address;

    @Column(name = "link")
    private String link;

    @Column(name = "difficult")
    private Boolean difficult;

    //@Column(name = "street_id")
    //private Long streetId;

    @Column(name = "community_id")
    private Long communityId;

    //@Column(name = "block_id")
    //private Long blockId;

    //@Column(name = "building_id")
    //private Long buildingId;

//    public Long getStreetId() {
//        return streetId;
//    }
//
//    public void setStreetId(Long streetId) {
//        this.streetId = streetId;
//    }

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

    public Long getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Long joinDate) {
        this.joinDate = joinDate;
    }

    public Integer getClaimPost() {
        return claimPost;
    }

    public void setClaimPost(Integer claimPost) {
        this.claimPost = claimPost;
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

    public Boolean getDifficult() {
        return difficult;
    }

    public void setDifficult(Boolean difficult) {
        this.difficult = difficult;
    }

    public Long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
    }

//    public Long getBlockId() {
//        return blockId;
//    }
//
//    public void setBlockId(Long blockId) {
//        this.blockId = blockId;
//    }
//
//    public Long getBuildingId() {
//        return buildingId;
//    }
//
//    public void setBuildingId(Long buildingId) {
//        this.buildingId = buildingId;
//    }
}
