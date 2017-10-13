package com.ydl.residentmap.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 贫困户低保户基本信息
 */
@Entity
@Table(name = "low_income_allowances")
public class LowIncomeAllowances implements Serializable {

    private static final long serialVersionUID = 8556304036066494812L;

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "birthday")
    private Long birthday;

    @Column(name = "family_member_count")
    private Short familyMemberCount;

    @Column(name = "family_year_income")
    private BigDecimal familyYearIncome;

    @Column(name = "is_low_income")
    private Boolean isLowIncome;

    @Column(name = "is_deformity")
    private Boolean isDeformity;

    @Column(name = "receive_policy_standard")
    private String receivePolicyStandard;

    @Column(name = "address")
    private String address;

    @Column(name = "link")
    private String link;

    @Column(name = "community_id")
    private Long communityId;

    @Column(name = "block_id")
    private  Long blockId;

    @Column(name = "building_id")
    private Long buildingId;

    public BigDecimal getFamilyYearIncome() {
        return familyYearIncome;
    }

    public void setFamilyYearIncome(BigDecimal familyYearIncome) {
        this.familyYearIncome = familyYearIncome;
    }

    public Long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
    }

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

    public Boolean getLowIncome() {
        return isLowIncome;
    }

    public void setLowIncome(Boolean lowIncome) {
        isLowIncome = lowIncome;
    }

    public Boolean getDeformity() {
        return isDeformity;
    }

    public void setDeformity(Boolean deformity) {
        isDeformity = deformity;
    }

    public String getReceivePolicyStandard() {
        return receivePolicyStandard;
    }

    public void setReceivePolicyStandard(String receivePolicyStandard) {
        this.receivePolicyStandard = receivePolicyStandard;
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
}
