package com.ydl.residentmap.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 小区
 */
@Entity
@Table(name = "block")
public class Block implements Serializable {
    private static final long serialVersionUID = 8556304036066494810L;

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lng")
    private String lng;

    @Column(name = "lat")
    private String lat;

    @Column(name = "address")
    private String address;

    //@Column(name = "street_id")
    //private Long streetId;

    @Column(name = "community_id")
    private Long communityId;

    //书记名称
    @Column(name = "secretary_name")
    private String secretaryName;

    //主任名称
    @Column(name = "director_name")
    private String directorName;

    //街道包抓领导名称
    @Column(name = "in_charge_leader_name")
    private String inChargeLeaderName;

    //社区工作日领导
    @Column(name = "work_day_leader_name")
    private String workDayLeaderName;

    //书记联系电话
    @Column(name = "secretary_tel")
    private String secretaryTel;

    //主任联系电话
    @Column(name = "director_tel")
    private String directorTel;

    //街道包抓领导联系电话
    @Column(name = "in_charge_leader_tel")
    private String inChargeLeaderTel;

    //社区工作日领导电话
    @Column(name = "work_day_leader_tel")
    private String workDayLeaderTel;

    //负责人
    @Column(name = "person_in_charge")
    private String personInCharge;

    //负责人电话
    @Column(name = "person_in_charge_tel")
    private String personInChargeTel;

    //地图范围
    @Column(name = "map_range")
    private String mapRange;

    @Column(name = "create_at")
    private Long createAt;

    public Long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }

    public String getMapRange() {
        return mapRange;
    }

    public void setMapRange(String mapRange) {
        this.mapRange = mapRange;
    }

//    public Long getStreetId() {
//        return streetId;
//    }
//
//    public void setStreetId(Long streetId) {
//        this.streetId = streetId;
//    }

    public String getPersonInCharge() {
        return personInCharge;
    }

    public void setPersonInCharge(String personInCharge) {
        this.personInCharge = personInCharge;
    }

    public String getPersonInChargeTel() {
        return personInChargeTel;
    }

    public void setPersonInChargeTel(String personInChargeTel) {
        this.personInChargeTel = personInChargeTel;
    }

    public String getSecretaryName() {
        return secretaryName;
    }

    public void setSecretaryName(String secretaryName) {
        this.secretaryName = secretaryName;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getInChargeLeaderName() {
        return inChargeLeaderName;
    }

    public void setInChargeLeaderName(String inChargeLeaderName) {
        this.inChargeLeaderName = inChargeLeaderName;
    }

    public String getWorkDayLeaderName() {
        return workDayLeaderName;
    }

    public void setWorkDayLeaderName(String workDayLeaderName) {
        this.workDayLeaderName = workDayLeaderName;
    }

    public String getSecretaryTel() {
        return secretaryTel;
    }

    public void setSecretaryTel(String secretaryTel) {
        this.secretaryTel = secretaryTel;
    }

    public String getDirectorTel() {
        return directorTel;
    }

    public void setDirectorTel(String directorTel) {
        this.directorTel = directorTel;
    }

    public String getInChargeLeaderTel() {
        return inChargeLeaderTel;
    }

    public void setInChargeLeaderTel(String inChargeLeaderTel) {
        this.inChargeLeaderTel = inChargeLeaderTel;
    }

    public String getWorkDayLeaderTel() {
        return workDayLeaderTel;
    }

    public void setWorkDayLeaderTel(String workDayLeaderTel) {
        this.workDayLeaderTel = workDayLeaderTel;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
    }
}
