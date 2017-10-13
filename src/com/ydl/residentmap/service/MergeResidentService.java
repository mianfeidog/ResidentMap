package com.ydl.residentmap.service;

import com.ydl.residentmap.model.Pager;
import com.ydl.residentmap.model.Resident;
import com.ydl.residentmap.model.vo.MergeResidentVo;
import com.ydl.residentmap.model.vo.ResidentVo;

import java.util.List;

public interface MergeResidentService {
	List<MergeResidentVo> queryMergeResidentVo(List<String> dataType1List,List<String> dataType12List,String name);
}
