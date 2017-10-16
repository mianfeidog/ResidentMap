package com.ydl.residentmap.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

/**
 * 社区
 */
@Entity
@Table(name = "community")
public class Community implements Serializable {

    private static final long serialVersionUID = 8556304036066494899L;

    @Id
    @Column(name = "id")
    private Long id;

    //社区名称
    @Column(name = "name")
    private String name;

    //总占地面积
    @Column(name = "area")
    private BigDecimal area;

    //小区(院落)个数
    @Column(name = "block_count")
    private Integer blockCount;

    //总户数
    @Column(name = "family_count")
    private Integer familyCount;

    //总人口数
    @Column(name = "people_count")
    private Integer peopleCount;

    //常住户数
    @Column(name = "resident_family_count")
    private Integer residentFamilyCount;

    //常住人数
    @Column(name = "resident_people_count")
    private Integer residentPeopleCount;

    //暂住户数
    @Column(name = "temporary_family_count")
    private Integer temporaryFamilyCount;

    //暂住人数
    @Column(name = "temporary_people_count")
    private Integer temporaryPeopleCount;

    //流入人口数
    @Column(name = "income_people_count")
    private Integer incomePeopleCount;

    //流出人口数
    @Column(name = "out_people_count")
    private Integer outPeopleCount;

    //党员人口数
    @Column(name = "party_member_count")
    private Integer partyMemberCount;

    //流入党员人数
    @Column(name = "income_party_member_count")
    private Integer incomePartyMemberCount;

    //流出党员人数
    @Column(name = "out_party_member_count")
    private Integer outPartyMemberCount;

    //育龄妇女人数
    @Column(name = "childbearing_count")
    private Integer childbearingCount;

    //残疾人数
    @Column(name = "deformity_count")
    private Integer deformityCount;

    //低保户数
    @Column(name = "allowances_family_count")
    private Integer allowancesFamilyCount;

    //低收入家庭
    @Column(name = "low_income_family_count")
    private Integer lowIncomeFamilyCount;

    //老龄人口
    @Column(name = "old_people_count")
    private Integer oldPeopleCount;

    //居民医保参保人数
    @Column(name = "medical_insurance_count")
    private Integer medicalInsuranceCount;

    //居民养老参保人数
    @Column(name = "old_insurance_count")
    private Integer oldInsuranceCount;

    //重点人员
    @Column(name = "key_person_count")
    private Integer keyPersonCount;

    //路张长院长所长人数
    @Column(name = "grid_manager_count")
    private Integer gridManagerCount;

    //已参加水改户数
    @Column(name = "water_reform_family_count")
    private Integer waterReformFamilyCount;

    //已参加电改户数
    @Column(name = "electricity_reform_family_count")
    private Integer electricityReformFamilyCount;

    //集中供暖户数
    @Column(name = "heating_supply_family_count")
    private Integer heatingSupplyFamilyCount;

    //天然气安装户数
    @Column(name = "gas_family_count")
    private Integer gasFamilyCount;

    //安防设置
    @Column(name = "security_system_count")
    private Integer securitySystemCount;

    //摄像头数
    @Column(name = "camera_count")
    private Integer cameraCount;

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

    //经度
    @Column(name = "lng")
    private String lng;

    //纬度
    @Column(name = "lat")
    private String lat;

    //文字说明
    @Column(name = "description")
    private String description;

    //所属街道id
    @Column(name = "street_id")
    private Long streetId;

    //社区类型
    @Column(name = "type")
    private Integer type;

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

    //    @OneToMany
//    @JoinColumn(name = "community_id")
//    private Set<PartyMember> partyMemberSet;

    public String getMapRange() {
        return mapRange;
    }

    public void setMapRange(String mapRange) {
        this.mapRange = mapRange;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public Integer getBlockCount() {
        return blockCount;
    }

    public void setBlockCount(Integer blockCount) {
        this.blockCount = blockCount;
    }

    public Integer getFamilyCount() {
        return familyCount;
    }

    public void setFamilyCount(Integer familyCount) {
        this.familyCount = familyCount;
    }

    public Integer getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(Integer peopleCount) {
        this.peopleCount = peopleCount;
    }

    public Integer getResidentFamilyCount() {
        return residentFamilyCount;
    }

    public void setResidentFamilyCount(Integer residentFamilyCount) {
        this.residentFamilyCount = residentFamilyCount;
    }

    public Integer getResidentPeopleCount() {
        return residentPeopleCount;
    }

    public void setResidentPeopleCount(Integer residentPeopleCount) {
        this.residentPeopleCount = residentPeopleCount;
    }

    public Integer getTemporaryFamilyCount() {
        return temporaryFamilyCount;
    }

    public void setTemporaryFamilyCount(Integer temporaryFamilyCount) {
        this.temporaryFamilyCount = temporaryFamilyCount;
    }

    public Integer getTemporaryPeopleCount() {
        return temporaryPeopleCount;
    }

    public void setTemporaryPeopleCount(Integer temporaryPeopleCount) {
        this.temporaryPeopleCount = temporaryPeopleCount;
    }

    public Integer getIncomePeopleCount() {
        return incomePeopleCount;
    }

    public void setIncomePeopleCount(Integer incomePeopleCount) {
        this.incomePeopleCount = incomePeopleCount;
    }

    public Integer getOutPeopleCount() {
        return outPeopleCount;
    }

    public void setOutPeopleCount(Integer outPeopleCount) {
        this.outPeopleCount = outPeopleCount;
    }

    public Integer getPartyMemberCount() {
        return partyMemberCount;
    }

    public void setPartyMemberCount(Integer partyMemberCount) {
        this.partyMemberCount = partyMemberCount;
    }

    public Integer getIncomePartyMemberCount() {
        return incomePartyMemberCount;
    }

    public void setIncomePartyMemberCount(Integer incomePartyMemberCount) {
        this.incomePartyMemberCount = incomePartyMemberCount;
    }

    public Integer getOutPartyMemberCount() {
        return outPartyMemberCount;
    }

    public void setOutPartyMemberCount(Integer outPartyMemberCount) {
        this.outPartyMemberCount = outPartyMemberCount;
    }

    public Integer getChildbearingCount() {
        return childbearingCount;
    }

    public void setChildbearingCount(Integer childbearingCount) {
        this.childbearingCount = childbearingCount;
    }

    public Integer getDeformityCount() {
        return deformityCount;
    }

    public void setDeformityCount(Integer deformityCount) {
        this.deformityCount = deformityCount;
    }

    public Integer getAllowancesFamilyCount() {
        return allowancesFamilyCount;
    }

    public void setAllowancesFamilyCount(Integer allowancesFamilyCount) {
        this.allowancesFamilyCount = allowancesFamilyCount;
    }

    public Integer getLowIncomeFamilyCount() {
        return lowIncomeFamilyCount;
    }

    public void setLowIncomeFamilyCount(Integer lowIncomeFamilyCount) {
        this.lowIncomeFamilyCount = lowIncomeFamilyCount;
    }

    public Integer getOldPeopleCount() {
        return oldPeopleCount;
    }

    public void setOldPeopleCount(Integer oldPeopleCount) {
        this.oldPeopleCount = oldPeopleCount;
    }

    public Integer getMedicalInsuranceCount() {
        return medicalInsuranceCount;
    }

    public void setMedicalInsuranceCount(Integer medicalInsuranceCount) {
        this.medicalInsuranceCount = medicalInsuranceCount;
    }

    public Integer getOldInsuranceCount() {
        return oldInsuranceCount;
    }

    public void setOldInsuranceCount(Integer oldInsuranceCount) {
        this.oldInsuranceCount = oldInsuranceCount;
    }

    public Integer getKeyPersonCount() {
        return keyPersonCount;
    }

    public void setKeyPersonCount(Integer keyPersonCount) {
        this.keyPersonCount = keyPersonCount;
    }

    public Integer getGridManagerCount() {
        return gridManagerCount;
    }

    public void setGridManagerCount(Integer gridManagerCount) {
        this.gridManagerCount = gridManagerCount;
    }

    public Integer getWaterReformFamilyCount() {
        return waterReformFamilyCount;
    }

    public void setWaterReformFamilyCount(Integer waterReformFamilyCount) {
        this.waterReformFamilyCount = waterReformFamilyCount;
    }

    public Integer getElectricityReformFamilyCount() {
        return electricityReformFamilyCount;
    }

    public void setElectricityReformFamilyCount(Integer electricityReformFamilyCount) {
        this.electricityReformFamilyCount = electricityReformFamilyCount;
    }

    public Integer getHeatingSupplyFamilyCount() {
        return heatingSupplyFamilyCount;
    }

    public void setHeatingSupplyFamilyCount(Integer heatingSupplyFamilyCount) {
        this.heatingSupplyFamilyCount = heatingSupplyFamilyCount;
    }

    public Integer getGasFamilyCount() {
        return gasFamilyCount;
    }

    public void setGasFamilyCount(Integer gasFamilyCount) {
        this.gasFamilyCount = gasFamilyCount;
    }

    public Integer getSecuritySystemCount() {
        return securitySystemCount;
    }

    public void setSecuritySystemCount(Integer securitySystemCount) {
        this.securitySystemCount = securitySystemCount;
    }

    public Integer getCameraCount() {
        return cameraCount;
    }

    public void setCameraCount(Integer cameraCount) {
        this.cameraCount = cameraCount;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getStreetId() {
        return streetId;
    }

    public void setStreetId(Long streetId) {
        this.streetId = streetId;
    }
}
