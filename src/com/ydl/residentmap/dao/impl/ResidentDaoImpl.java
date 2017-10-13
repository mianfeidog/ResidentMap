package com.ydl.residentmap.dao.impl;

import com.ydl.residentmap.dao.BaseDao;
import com.ydl.residentmap.dao.ResidentDao;
import com.ydl.residentmap.model.Pager;
import com.ydl.residentmap.model.Resident;
import com.ydl.residentmap.util.IdWorker;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Repository
public class ResidentDaoImpl implements ResidentDao {
	@Resource
	private BaseDao<Resident> baseDAO;
	
	@Override
	public Boolean save(Resident resident) {
		Boolean flag = true;
		try {
			Random random = new Random();
			resident.setId(new IdWorker((long)random.nextInt(15)).nextId());
			baseDAO.save(resident);
			System.out.println("添加居民 OK   用户ID："+resident.getId());
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public List<Resident> getAll() {
		String hql="from Resident";
		return baseDAO.find(hql);
	}

	@Override
	public List<Resident> getResidentsByName(String name){
		String hql="from Resident where name like '%"+name+"%'";
		return baseDAO.find(hql);
	}

	@Override
	public List<Resident> getResidentsByType(int type){
		String hql="from Resident where type = "+type+"";
		return baseDAO.find(hql);
	}

	
	/**
	 * 删除居民
	 */
	@Override
	public Boolean delete(Long id) {
		Boolean flag = true;
		try {
			Resident data = baseDAO.get(Resident.class, id);
			baseDAO.delete(data);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public Integer deleteList(List<String> idList) {
		Integer cnt = 0;
		try {
			String hql="delete Resident as r where r.id in ("+String.join(",", idList)+")";
			cnt=baseDAO.executeHql(hql);
		} catch (Exception e) {
			e.printStackTrace();
			cnt = 0;
		}
		return cnt;
	}

	@Override
	public Boolean update(Resident resident) {
		Boolean flag = true;
		try {
			baseDAO.update(resident);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public Resident get(Long id) {
		return baseDAO.get(Resident.class, id);
	}

	@Override
	public Pager<Resident> getResidents(int offset, int size) {
		//查询共多少数据
		String totalSql = "select count(*) from Resident";
		Long total = baseDAO.count(totalSql);
		String dateHql = "from Resident";
		List<Resident> residentList = baseDAO.find(dateHql, new Object[]{}, offset, size);
		Pager<Resident> pager = new Pager<Resident>();
		pager.setOffset(offset);
		pager.setSize(size);
		pager.setTotal(total);
		pager.setData(residentList);
		return pager;
	}
}
