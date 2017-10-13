package com.ydl.residentmap.dao;
import java.util.Dictionary;
import java.util.List;

import com.ydl.residentmap.model.DataDictionary;

public interface DataDictionaryDao{
	List<DataDictionary> getByType(int dataType);
	//保存
	Boolean save(DataDictionary dataDictionary);
	Boolean update(DataDictionary dataDictionary);
	//删除
	Boolean delete(Long id);

	Integer deleteList(List<String> idList);

	//获取所有数据字典
	List<DataDictionary> getAll();
}
