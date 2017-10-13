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
@Table(name = "aid_plan")
public class AidPlan implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4492256523970631050L;

	@Id
	@Column(name = "id")
	private Long id;
	
	//标题
	@Column(name = "title")
	private String title;
	
	//内容
	@Column(name = "content")
	private String content;
	
	//创建时间
	@Column(name = "created_at")
	private Date createdAt;
	
	//提醒时间
	@Column(name = "reminded_at")
	private Date remindedAt;
	
	//是否提醒
	@Column(name = "is_remind")
	private Boolean isRemind;

	//创建人
	@Column(name = "created_by")
	private Long createdBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getRemindedAt() {
		return remindedAt;
	}

	public void setRemindedAt(Date remindedAt) {
		this.remindedAt = remindedAt;
	}

	public Boolean getIsRemind() {
		return isRemind;
	}

	public void setIsRemind(Boolean isRemind) {
		this.isRemind = isRemind;
	}


	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
}
