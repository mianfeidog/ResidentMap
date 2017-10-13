package com.ydl.residentmap.model.vo;

import com.ydl.residentmap.model.Resident;

import javax.persistence.Column;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用户
 * @author yy
 * 2017-7-16
 */

public class ResidentVo extends Resident {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8556304036066494896L;

	private String birthdayStr;

	private String photoStr;

	public String getBirthdayStr() {
		return birthdayStr;
	}

	public void setBirthdayStr(String birthdayStr) {
		this.birthdayStr = birthdayStr;
	}

	public String getPhotoStr() {
		return photoStr;
	}

	public void setPhotoStr(String photoStr) {
		this.photoStr = photoStr;
	}

	public ResidentVo(Resident resident){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date birthday = resident.getBirthday();
		this.setId(resident.getId());
		this.setBirthday(birthday);
		this.setLat(resident.getLat());
		this.setLng(resident.getLng());
		this.setAddress(resident.getAddress());
		this.setDeformity(resident.getDeformity());
		this.setFamilyCnt(resident.getFamilyCnt());
		this.setLowIncome(resident.getLowIncome());
		this.setName(resident.getName());
		this.setPhoto(resident.getPhoto());
		//this.setPhotoStr(resident.getPhotoStr());
		this.setType(resident.getType());
		this.setTelephone(resident.getTelephone());
		this.setYearIncome(resident.getYearIncome());
		this.setReceivePolicyStandard(resident.getReceivePolicyStandard());
		if(birthday!=null){
			String birthdayStr=sdf.format(birthday);
			this.setBirthdayStr(birthdayStr);
		}
	}

}
