package com.ydl.residentmap.service;

import java.util.List;

import com.ydl.residentmap.model.DataDictionary;

public interface DataDictionaryService{
	List<DataDictionary> getByType(int dataType);
	//保存
	Boolean save(DataDictionary dataDictionary);
	Boolean update(DataDictionary dataDictionary);
	//删除
	Boolean delete(Long id);
	//获取所有数据字典
	List<DataDictionary> getAll();

	Integer deleteList(List<String> idList);

	//获取某一类型的下一数值
	Integer getNextValue(Integer dataType);
}
