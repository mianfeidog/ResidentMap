package com.ydl.residentmap.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 帮扶措施
 * @author yy
 * 2017-7-16
 */
@Entity
@Table(name = "aid_step")
public class AidStep implements Serializable {

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

	//创建人
	@Column(name = "created_by")
	private Long createdBy;

	@Transient
	//记录关联的帮扶对象id
	private List<Long> aidResidentList;


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

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public List<Long> getAidResidentList() {
		return aidResidentList;
	}

	public void setAidResidentList(List<Long> aidResidentList) {
		this.aidResidentList = aidResidentList;
	}
}
