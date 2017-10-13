package com.ydl.residentmap.dao;

import com.ydl.residentmap.model.Pager;
import com.ydl.residentmap.model.Resident;

import java.util.List;

public interface ResidentDao {
	Boolean save(Resident resident);

	List<Resident> getAll();

	List<Resident> getResidentsByName(String name);

	List<Resident> getResidentsByType(int type);

	Boolean delete(Long id);

	Integer deleteList(List<String> idList);

	Boolean update(Resident resident);

	Resident get(Long id);

	Pager<Resident> getResidents(int offset, int size);
}
