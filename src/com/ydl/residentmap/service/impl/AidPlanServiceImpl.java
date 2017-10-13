package com.ydl.residentmap.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ydl.residentmap.dao.AidPlanDao;
import com.ydl.residentmap.model.AidPlan;
import com.ydl.residentmap.service.AidPlanService;
import com.ydl.residentmap.util.IdWorker;

@Service
public class AidPlanServiceImpl implements AidPlanService {
	
	@Resource
	private AidPlanDao aidPlanDao;
	@Override
	public Boolean save(AidPlan obj) {
		Boolean flag = true;
		try {
			if(obj.getId() == null || obj.getId() < 1L)
			{
				Random random=new Random();
				IdWorker idWorker = new IdWorker((long)random.nextInt(15));
				obj.setId(idWorker.nextId());
			}
			//创建时间
			obj.setCreatedAt(new Date());
			//设置为未提醒
			obj.setIsRemind(false);
			aidPlanDao.save(obj);
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	
	@Override
	public List<AidPlan> getAll() {
		return aidPlanDao.getAll();
	}

	@Override
	public Boolean delete(Long id) {
		return aidPlanDao.delete(id);
	}

	@Override
	public Boolean update(AidPlan obj) {
		//查询帮扶计划
		AidPlan aidPlan = aidPlanDao.get(obj.getId());
		obj.setCreatedAt(aidPlan.getCreatedAt());
		obj.setCreatedBy(aidPlan.getCreatedBy());
		obj.setIsRemind(aidPlan.getIsRemind());
		return aidPlanDao.update(obj);
	}

	@Override
	public AidPlan get(Long id) {
		return aidPlanDao.get(id);
	}

	@Override
	public List<AidPlan> getPlansByUserId(Long userId) {
		return aidPlanDao.getPlansByUserId(userId);
	}
}
