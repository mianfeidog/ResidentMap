package com.ydl.residentmap.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ydl.residentmap.dao.DataDictionaryDao;
import com.ydl.residentmap.model.DataDictionary;
import com.ydl.residentmap.service.DataDictionaryService;

@Service
public class DataDictionaryServiceImpl implements DataDictionaryService {
	
	@Resource
	private DataDictionaryDao dataDictionaryDao;

	@Override
	public List<DataDictionary> getByType(int dataType) {
		return dataDictionaryDao.getByType(dataType);
	}


	@Override
	public Boolean save(DataDictionary dataDictionary) {
		return dataDictionaryDao.save(dataDictionary);
	}

	@Override
	public Boolean update(DataDictionary dataDictionary) {
		return dataDictionaryDao.update(dataDictionary);
	}

	@Override
	public Boolean delete(Long id) {
		return dataDictionaryDao.delete(id);
	}

	@Override
	public List<DataDictionary> getAll() {
		return dataDictionaryDao.getAll();
	}

	@Override
	public Integer deleteList(List<String> idList) {
		return dataDictionaryDao.deleteList(idList);
	}

	@Override
	public Integer getNextValue(Integer dataType) {
		List<DataDictionary> dataDictionaryList= dataDictionaryDao.getByType(dataType);
		return dataDictionaryList.size()+1;
	}
}
