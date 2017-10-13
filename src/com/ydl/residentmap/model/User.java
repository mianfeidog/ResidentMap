package com.ydl.residentmap.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 用户
 * @author yy
 * 2017-7-16
 */
@Entity
@Table(name = "user")
public class User implements Serializable {
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
	
	//密码
	@Column(name = "password")
	private String password;

	//真实姓名
	@Column(name = "real_name",nullable=true,columnDefinition="varchar(255) default ''  COMMENT '用户真实姓名' ")
	private String realName;
	
	//创建时间
	@Column(name = "created_at")
	private Long createdAt;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
}
