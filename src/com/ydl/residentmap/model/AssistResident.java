package com.ydl.residentmap.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 帮扶人员基本信息
 */
@Entity
@Table(name = "assist_resident")
public class AssistResident implements Serializable {

    private static final long serialVersionUID = 8556304036066494812L;

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private Integer type;

    //private DataDictionary type;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "birthday")
    private Long birthday;

    @Column(name = "family_member_count")
    private Short familyMemberCount;

    @Column(name = "family_year_income")
    private BigDecimal familyYearIncome;


    @Column(name = "deformity_card_rank")
    private Integer deformityCardRank;

    @Column(name = "deformity_certificate_num")
    private String deformityCertificateNum;

    @Column(name = "receive_policy_standard")
    private String receivePolicyStandard;

    @Column(name = "address")
    private String address;

    @Column(name = "link")
    private String link;


    //@Column(name = "street_id")
    //private Long streetId;

//    @ManyToOne
//    @JoinColumn(name="street_id")
//    private Street street;

    //@Column(name = "community_id")
    //private Long communityId;

    @Column(name = "block_id")
    private  Long blockId;

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

    public BigDecimal getFamilyYearIncome() {
        return familyYearIncome;
    }

    public void setFamilyYearIncome(BigDecimal familyYearIncome) {
        this.familyYearIncome = familyYearIncome;
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

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public Short getFamilyMemberCount() {
        return familyMemberCount;
    }

    public void setFamilyMemberCount(Short familyMemberCount) {
        this.familyMemberCount = familyMemberCount;
    }

    public String getReceivePolicyStandard() {
        return receivePolicyStandard;
    }

    public void setReceivePolicyStandard(String receivePolicyStandard) {
        this.receivePolicyStandard = receivePolicyStandard;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public Long getBlockId() {
        return blockId;
    }

    public void setBlockId(Long blockId) {
        this.blockId = blockId;
    }

    public Integer getDeformityCardRank() {
        return deformityCardRank;
    }

    public void setDeformityCardRank(Integer deformityCardRank) {
        this.deformityCardRank = deformityCardRank;
    }

    public String getDeformityCertificateNum() {
        return deformityCertificateNum;
    }

    public void setDeformityCertificateNum(String deformityCertificateNum) {
        this.deformityCertificateNum = deformityCertificateNum;
    }

//    public Long getStreetId() {
//        return streetId;
//    }
//
//    public void setStreetId(Long streetId) {
//        this.streetId = streetId;
//    }


//    public Street getStreet() {
//        return street;
//    }
//
//    public void setStreet(Street street) {
//        this.street = street;
//    }
}
