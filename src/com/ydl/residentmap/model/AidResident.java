package com.ydl.residentmap.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 帮扶对象
 * @author yy
 * 2017-7-16
 */
@Entity
@Table(name = "aid_resident")
public class AidResident implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8556304036066494896L;
	@Id
	@Column(name = "id")
	private Long id;
	
	//账号
	@Column(name = "name")
	private String name;

	//经度
	@Column(name = "longitude")
	private Double longitude;

	//维度
	@Column(name = "latitude")
	private Double latitude;

	//照片
	@Column(name = "img")
	private String img;

	//所属镇
	@Column(name = "belonged_country")
	private String belongedCounty;
	
	//行政村
	@Column(name = "admin_village")
	private String adminVillage;

	//性别
	@Column(name = "gender")
	private Integer gender;

	//与户主关系
	@Column(name = "rate_householder")
	private Integer rate_householder;

	//身份证号码
	@Column(name = "id_card")
	private String idCard;

	//政治面貌
	@Column(name = "political_status")
	private String politicalStatus;

	//文化程度
	@Column(name = "education_degree")
	private Integer educationDegree;

	//健康状况
	@Column(name = "health_condition")
	private Integer healthCondition;
	//务工状况
	@Column(name = "work_condition")
	private Integer workCondition;

	//贫困识别年度
	@Column(name = "poor_date")
	private Date poorDate;
	//计划脱贫年度
	@Column(name = "plan_outpoor_date")
	private Date planOutpoorDate;
	//实际脱贫年度
	@Column(name = "fact_outpoor_date")
	private Date factOutpoorDate;
	//返贫年度
	@Column(name = "return_poor_date")
	private Date returnPoorDate;
	//贫困户类别
	@Column(name = "poor_cate")
	private Integer poorCate;
	//主要致贫原因
	@Column(name = "main_poor_reson")
	private Integer mainPoorReson;
	//劳动力人数
	@Column(name = "work_num")
	private Integer workNum;
	//生产经营状况
	@Column(name = "product_management_state")
	private Integer productManagementState;
	//是否危房
	@Column(name = "is_risk_house")
	private Boolean isRiskHouse;
	//是否纳入异地搬迁
	@Column(name = "is_in_offset")
	private Boolean isInOffset;
	//耕地面积
	@Column(name = "farmland_area")
	private Double farmlandArea;
	//林地面积
	@Column(name = "woodland_area")
	private Double woodlandArea;
	//义务教育阶段子女因贫辍学
	@Column(name = "is_stopschool_poor")
	private Boolean isStopschoolPoor;
	//是否参加新农合
	@Column(name = "is_join_nrcms")
	private Boolean isJoinNrcms;
	//是否参加大病保险
	@Column(name = "is_join_serious_illnesses")
	private Boolean isJoinSeriousIllnesses;
	//饮水安全
	@Column(name = "is_safe_water")
	private Boolean isSafeWater;
	//情况描述
	@Column(name = "description")
	private String description;
	//联系电话
	@Column(name = "phone")
	private String phone;
	//详细地址
	@Column(name = "address")
	private String address;
	//创建人
	@Column(name = "created_by")
	private Long createdBy;
	//创建时间
	@Column(name = "created_at")
	private Date createdAt;

	//是否删除
	@Column(name = "id_removed")
	private Boolean isRemoved;

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

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getBelongedCounty() {
		return belongedCounty;
	}

	public void setBelongedCounty(String belongedCounty) {
		this.belongedCounty = belongedCounty;
	}

	public String getAdminVillage() {
		return adminVillage;
	}

	public void setAdminVillage(String adminVillage) {
		this.adminVillage = adminVillage;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Integer getRate_householder() {
		return rate_householder;
	}

	public void setRate_householder(Integer rate_householder) {
		this.rate_householder = rate_householder;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getPoliticalStatus() {
		return politicalStatus;
	}

	public void setPoliticalStatus(String politicalStatus) {
		this.politicalStatus = politicalStatus;
	}

	public Integer getEducationDegree() {
		return educationDegree;
	}

	public void setEducationDegree(Integer educationDegree) {
		this.educationDegree = educationDegree;
	}

	public Integer getHealthCondition() {
		return healthCondition;
	}

	public void setHealthCondition(Integer healthCondition) {
		this.healthCondition = healthCondition;
	}

	public Integer getWorkCondition() {
		return workCondition;
	}

	public void setWorkCondition(Integer workCondition) {
		this.workCondition = workCondition;
	}

	public Date getPoorDate() {
		return poorDate;
	}

	public void setPoorDate(Date poorDate) {
		this.poorDate = poorDate;
	}

	public Date getPlanOutpoorDate() {
		return planOutpoorDate;
	}

	public void setPlanOutpoorDate(Date planOutpoorDate) {
		this.planOutpoorDate = planOutpoorDate;
	}

	public Date getFactOutpoorDate() {
		return factOutpoorDate;
	}

	public void setFactOutpoorDate(Date factOutpoorDate) {
		this.factOutpoorDate = factOutpoorDate;
	}

	public Date getReturnPoorDate() {
		return returnPoorDate;
	}

	public void setReturnPoorDate(Date returnPoorDate) {
		this.returnPoorDate = returnPoorDate;
	}

	public Integer getPoorCate() {
		return poorCate;
	}

	public void setPoorCate(Integer poorCate) {
		this.poorCate = poorCate;
	}

	public Integer getMainPoorReson() {
		return mainPoorReson;
	}

	public void setMainPoorReson(Integer mainPoorReson) {
		this.mainPoorReson = mainPoorReson;
	}

	public Integer getWorkNum() {
		return workNum;
	}

	public void setWorkNum(Integer workNum) {
		this.workNum = workNum;
	}

	public Integer getProductManagementState() {
		return productManagementState;
	}

	public void setProductManagementState(Integer productManagementState) {
		this.productManagementState = productManagementState;
	}

	public Boolean getRiskHouse() {
		return isRiskHouse;
	}

	public void setRiskHouse(Boolean riskHouse) {
		isRiskHouse = riskHouse;
	}

	public Boolean getInOffset() {
		return isInOffset;
	}

	public void setInOffset(Boolean inOffset) {
		isInOffset = inOffset;
	}

	public Double getFarmlandArea() {
		return farmlandArea;
	}

	public void setFarmlandArea(Double farmlandArea) {
		this.farmlandArea = farmlandArea;
	}

	public Double getWoodlandArea() {
		return woodlandArea;
	}

	public void setWoodlandArea(Double woodlandArea) {
		this.woodlandArea = woodlandArea;
	}

	public Boolean getStopschoolPoor() {
		return isStopschoolPoor;
	}

	public void setStopschoolPoor(Boolean stopschoolPoor) {
		isStopschoolPoor = stopschoolPoor;
	}

	public Boolean getJoinNrcms() {
		return isJoinNrcms;
	}

	public void setJoinNrcms(Boolean joinNrcms) {
		isJoinNrcms = joinNrcms;
	}

	public Boolean getJoinSeriousIllnesses() {
		return isJoinSeriousIllnesses;
	}

	public void setJoinSeriousIllnesses(Boolean joinSeriousIllnesses) {
		isJoinSeriousIllnesses = joinSeriousIllnesses;
	}

	public Boolean getSafeWater() {
		return isSafeWater;
	}

	public void setSafeWater(Boolean safeWater) {
		isSafeWater = safeWater;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Boolean getRemoved() {
		return isRemoved;
	}

	public void setRemoved(Boolean removed) {
		isRemoved = removed;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "AidResident{" +
				"id=" + id +
				", name='" + name + '\'' +
				", longitude=" + longitude +
				", latitude=" + latitude +
				", img='" + img + '\'' +
				", belongedCounty='" + belongedCounty + '\'' +
				", adminVillage='" + adminVillage + '\'' +
				", gender=" + gender +
				", rate_householder=" + rate_householder +
				", idCard='" + idCard + '\'' +
				", politicalStatus='" + politicalStatus + '\'' +
				", educationDegree=" + educationDegree +
				", healthCondition=" + healthCondition +
				", workCondition=" + workCondition +
				", poorDate=" + poorDate +
				", planOutpoorDate=" + planOutpoorDate +
				", factOutpoorDate=" + factOutpoorDate +
				", returnPoorDate=" + returnPoorDate +
				", poorCate=" + poorCate +
				", mainPoorReson=" + mainPoorReson +
				", workNum=" + workNum +
				", productManagementState=" + productManagementState +
				", isRiskHouse=" + isRiskHouse +
				", isInOffset=" + isInOffset +
				", farmlandArea=" + farmlandArea +
				", woodlandArea=" + woodlandArea +
				", isStopschoolPoor=" + isStopschoolPoor +
				", isJoinNrcms=" + isJoinNrcms +
				", isJoinSeriousIllnesses=" + isJoinSeriousIllnesses +
				", isSafeWater=" + isSafeWater +
				", description='" + description + '\'' +
				", phone='" + phone + '\'' +
				", address='" + address + '\'' +
				", createdBy=" + createdBy +
				", createdAt=" + createdAt +
				", isRemoved=" + isRemoved +
				'}';
	}
}
