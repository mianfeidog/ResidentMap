package com.ydl.residentmap.dao.impl;

import com.ydl.residentmap.dao.BaseDao;
import com.ydl.residentmap.dao.DataDictionaryDao;
import com.ydl.residentmap.model.DataDictionary;
import com.ydl.residentmap.util.IdWorker;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@Repository
public class DataDictionaryDaoImpl implements DataDictionaryDao {	
	@Resource
	private BaseDao<DataDictionary> baseDAO;

	@Override
	public List<DataDictionary> getByType(int dataType) {
		String hql="from DataDictionary where dataType=? order by value";
		return baseDAO.find(hql,new Object[]{dataType});
	}


	@Override
	public Boolean save(DataDictionary dataDictionary) {
		Boolean rs = true;
		try{
			Random random = new Random();
			dataDictionary.setId(new IdWorker((long) random.nextInt(15)).nextId());
			baseDAO.save(dataDictionary);
			System.out.println("添加字典OK   ID：" + dataDictionary.getId());
		}catch (Exception e){
			rs = false;
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public Boolean update(DataDictionary dataDictionary) {
		Boolean rs = true;
		try{
			baseDAO.update(dataDictionary);
		}catch (Exception e){
			rs = false;
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public Boolean delete(Long id) {
		Boolean rs = true;
		try{
			String hql ="delete from DataDictionary where id=?";
			baseDAO.executeHql(hql,new Object[]{id});
		}catch (Exception e){
			rs = false;
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public Integer deleteList(List<String> idList) {
		Integer cnt = 0;
		try {
			String hql="delete DataDictionary as r where r.id in ("+String.join(",", idList)+")";
			cnt=baseDAO.executeHql(hql);
		} catch (Exception e) {
			e.printStackTrace();
			cnt = 0;
		}
		return cnt;
	}

	@Override
	public List<DataDictionary> getAll() {
		String hql ="from DataDictionary order by dataType asc, value asc ";
		return baseDAO.find(hql);
	}
}
