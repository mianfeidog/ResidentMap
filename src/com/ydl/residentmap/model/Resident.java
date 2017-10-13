package com.ydl.residentmap.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户
 * @author yy
 * 2017-7-16
 */
@Entity
@Table(name = "resident")
public class Resident implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8556304036066494896L;
	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "type")
	private int type;

	@Column(name = "name")
	private String name;

	@Column(name = "birthday")
	private Date birthday;

	//@Column(name = "full_name",nullable=true,columnDefinition="varchar(255) default ''  COMMENT '用户真实姓名' ")
	@Column(name = "family_cnt")
	private int familyCnt;

	@Column(name = "telephone")
	private String telephone;

	@Column(name = "is_low_income")
	private Boolean isLowIncome;

	@Column(name = "is_deformity")
	private Boolean isDeformity;

	@Column(name = "year_income")
	private BigDecimal yearIncome;

	@Column(name = "address")
	private String address;

	@Column(name = "receive_policy_standard")
	private String receivePolicyStandard;

	@Column(name = "photo")
	private String photo;

	@Column(name = "lng")
	private String lng;

	@Column(name = "lat")
	private String lat;

	//@Column(name = "photo_str")
	//private String photoStr;

	//public void setPhotoStr(String photoStr) {
//		this.photoStr = photoStr;
//	}

	//public String getPhotoStr() {
//		return photoStr;
//	}

	public Long getId() {
		return id;
	}

	public int getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public int getFamilyCnt() {
		return familyCnt;
	}

	public String getTelephone() {
		return telephone;
	}

	public Boolean getLowIncome() {
		return isLowIncome;
	}

	public Boolean getDeformity() {
		return isDeformity;
	}

	public BigDecimal getYearIncome() {
		return yearIncome;
	}

	public String getAddress() {
		return address;
	}

	public String getReceivePolicyStandard() {
		return receivePolicyStandard;
	}

	public String getPhoto() {
		return photo;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setFamilyCnt(int familyCnt) {
		this.familyCnt = familyCnt;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setLowIncome(Boolean lowIncome) {
		isLowIncome = lowIncome;
	}

	public void setDeformity(Boolean deformity) {
		isDeformity = deformity;
	}

	public void setYearIncome(BigDecimal yearIncome) {
		this.yearIncome = yearIncome;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setReceivePolicyStandard(String receivePolicyStandard) {
		this.receivePolicyStandard = receivePolicyStandard;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getLng() {
		return lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}
}
