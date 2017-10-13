package com.ydl.residentmap.service;

import com.ydl.residentmap.model.Pager;
import com.ydl.residentmap.model.Resident;
import com.ydl.residentmap.model.vo.ResidentVo;

import java.util.List;

public interface ResidentService {
	Boolean save(Resident obj);

	Boolean delete(Long id);

	Integer deleteList(List<String> idList);
	
	Boolean update(Resident obj);

	ResidentVo get(Long id);

	List<ResidentVo> getAll();

	List<ResidentVo> getResidentsByName(String name);

	List<ResidentVo> getResidentsByType(int type);

	Pager<Resident> getResidents(int offset, int size);

}
