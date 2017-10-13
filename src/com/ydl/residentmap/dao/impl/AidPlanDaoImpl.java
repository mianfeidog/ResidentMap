package com.ydl.residentmap.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ydl.residentmap.dao.AidPlanDao;
import com.ydl.residentmap.dao.BaseDao;
import com.ydl.residentmap.model.AidPlan;

@Repository
public class AidPlanDaoImpl implements AidPlanDao {	
	@Resource
	private BaseDao<AidPlan> baseDAO;
	
	@Override
	public Boolean save(AidPlan obj) {
		Boolean flag = true;
		try {
			baseDAO.save(obj);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public List<AidPlan> getAll() {
		String hql="from AidPlan";
		return baseDAO.find(hql);
	}
	
	/**
	 * 删除用户
	 */
	@Override
	public Boolean delete(Long id) {
		Boolean flag = true;
		try {
			AidPlan data = baseDAO.get(AidPlan.class, id);
			baseDAO.delete(data);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public Boolean update(AidPlan AidPlan) {
		Boolean flag = true;
		try {
			baseDAO.update(AidPlan);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public AidPlan get(Long id) {	
		return baseDAO.get(AidPlan.class, id);
	}

	@Override
	public List<AidPlan> getPlansByUserId(Long userId) {
		String hql = "from AidPlan where createdBy=?";
		return baseDAO.find(hql,new Object[]{userId});
	}
}
