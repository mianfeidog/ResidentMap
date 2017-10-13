package com.ydl.residentmap.service;

import java.util.List;

import com.ydl.residentmap.model.AidPlan;

public interface AidPlanService{
	Boolean save(AidPlan obj);
	
	List<AidPlan> getAll();
	
	Boolean delete(Long id);
	
	Boolean update(AidPlan obj);
	
	AidPlan get(Long id);

	//根据创建人查询帮扶计划
	List<AidPlan> getPlansByUserId(Long userId);

	
}
