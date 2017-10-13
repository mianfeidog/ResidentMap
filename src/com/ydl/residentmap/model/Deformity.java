package com.ydl.residentmap.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 残疾人信息
 */
@Entity
@Table(name = "deformity")
public class Deformity implements Serializable {

    private static final long serialVersionUID = 8556304036066494817L;

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
    private Integer familyMemberCount;

    @Column(name = "family_income")
    private BigDecimal familyIncome;

    @Column(name = "deformity_card_rank")
    private Integer deformityCardRank;

    @Column(name = "certificate_num")
    private String certificateNum;

    @Column(name = "receive_policy_standard")
    private String receivePolicyStandard;

    @Column(name = "address")
    private String address;

    @Column(name = "link")
    private String link;

    @Column(name = "community_id")
    private Long communityId;

    @Column(name = "block_id")
    private Long blockId;

    @Column(name = "building_id")
    private Long buildingId;

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

    public Integer getFamilyMemberCount() {
        return familyMemberCount;
    }

    public void setFamilyMemberCount(Integer familyMemberCount) {
        this.familyMemberCount = familyMemberCount;
    }

    public BigDecimal getFamilyIncome() {
        return familyIncome;
    }

    public void setFamilyIncome(BigDecimal familyIncome) {
        this.familyIncome = familyIncome;
    }

    public Integer getDeformityCardRank() {
        return deformityCardRank;
    }

    public void setDeformityCardRank(Integer deformityCardRank) {
        this.deformityCardRank = deformityCardRank;
    }

    public String getCertificateNum() {
        return certificateNum;
    }

    public void setCertificateNum(String certificateNum) {
        this.certificateNum = certificateNum;
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

    public Long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
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
}
