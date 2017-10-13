package com.ydl.residentmap.service;

import java.util.List;

import com.ydl.residentmap.model.AidResident;
import com.ydl.residentmap.model.vo.AidResidentVo;

public interface AidResidentService{
	//保存
	Boolean save(AidResident aidResident) throws Exception;
	//更新
	Boolean update(AidResident aidResident);
	//删除
	Boolean delete(Long id);
	//获取贫困户分布坐标
	List<AidResident> getPositions(AidResident aidResident);
	//获取贫困户简要信息
	List<AidResident> getRemarkInfos(AidResidentVo aidResidentVo);
	//根据id 查询贫困户简要信息
	AidResident getRemarkInfo(Long id);
	//查询贫困户的详细信息
	AidResident getDetailInfo(Long id);


}
