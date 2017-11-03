package com.ydl.residentmap.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 党组织
 */
@Entity
@Table(name = "party_org")
public class PartyOrg {
    private static final long serialVersionUID = 8556304036066494815L;

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "org_system")
    private Integer orgSystem;

    @Column(name = "org_attribute")
    private Integer orgAttribute;

    @Column(name = "par_id")
    private Long parId;

    @Column(name = "secretary_name")
    private String secretaryName;

    @Column(name = "member_cnt")
    private Integer memberCnt;

    @Column(name = "address")
    private String address;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "community_id")
    private Long communityId;

    @Column(name = "create_at")
    private Long createAt;

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

    public Integer getOrgSystem() {
        return orgSystem;
    }

    public void setOrgSystem(Integer orgSystem) {
        this.orgSystem = orgSystem;
    }

    public Integer getOrgAttribute() {
        return orgAttribute;
    }

    public void setOrgAttribute(Integer orgAttribute) {
        this.orgAttribute = orgAttribute;
    }

    public Long getParId() {
        return parId;
    }

    public void setParId(Long parId) {
        this.parId = parId;
    }

    public String getSecretaryName() {
        return secretaryName;
    }

    public void setSecretaryName(String secretaryName) {
        this.secretaryName = secretaryName;
    }

    public Integer getMemberCnt() {
        return memberCnt;
    }

    public void setMemberCnt(Integer memberCnt) {
        this.memberCnt = memberCnt;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
    }
}
