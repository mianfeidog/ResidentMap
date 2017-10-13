package com.ydl.residentmap.dao;
import java.util.List;

import com.ydl.residentmap.model.AidResident;

public interface AidResidentDao{
	//保存
	Boolean save(AidResident aidResident);
	//更新
	Boolean update(AidResident aidResident);
	//删除
	Boolean delete(Long id);
	//软删除
	Boolean deleteSoft(Long id);
	//获取贫困户分布坐标
	List<AidResident> getPositions(AidResident aidResident);
	//获取贫困户简要信息
	List<AidResident> getRemarkInfos(AidResident aidResident, Integer page, Integer size);
	//根据id 查询贫困户简要信息
	AidResident getRemarkInfo(Long id);
	//查询贫困户的详细信息
	AidResident getDetailInfo(Long id);
}
