package com.ydl.residentmap.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "data_dictionary")
public class DataDictionary implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8556304036066494896L;
	@Id
	@Column(name = "id")
	private Long id;
	
	//名称
	@Column(name = "name")
	private String name;
	
	//类型值
	@Column(name = "value")
	private Integer value;
	
	//数据类型
	@Column(name = "data_type")
	private Integer dataType;
	
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

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public Integer getDataType() {
		return dataType;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}

}
