package com.ydl.residentmap.service.impl;

import com.ydl.residentmap.dao.ResidentDao;
import com.ydl.residentmap.model.Pager;
import com.ydl.residentmap.model.Resident;
import com.ydl.residentmap.model.UserException;
import com.ydl.residentmap.model.vo.ResidentVo;
import com.ydl.residentmap.service.ResidentService;
import com.ydl.residentmap.util.ImageUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ResidentServiceImpl implements ResidentService {
	
	@Resource
	private ResidentDao ResidentDao;

	@Override
	public Boolean save(Resident obj) {
		if(obj.getName() == null){
			throw new RuntimeException("名字不能为空！");
		}
		return ResidentDao.save(obj);
	}

	@Override
	public Pager<Resident> getResidents(int offset, int size){
		return ResidentDao.getResidents(offset,size);
	}

	@Override
	public Boolean delete(Long id) {
		return ResidentDao.delete(id);
	}

	@Override
	public Integer deleteList(List<String> idList){
		return ResidentDao.deleteList(idList);
	}

	@Override
	public Boolean update(Resident resident) {
		return ResidentDao.update(resident);
	}

	@Override
	public ResidentVo get(Long id) {
		Resident resident = ResidentDao.get(id);
		ResidentVo residentVo = this.getResidentVo(resident);
		String photo = residentVo.getPhoto();
		String rootPath = System.getProperty("kechuangMap.webapp");
		String filePath = rootPath+photo;
		String photoStr=ImageUtils.encodeImgToBase64(filePath);
		//String photoStr = ImageUtils.convertByteToImage()
		residentVo.setPhotoStr(photoStr);
		return residentVo;
	}

	public @Override List<ResidentVo> getAll(){
		List<Resident> residents= ResidentDao.getAll();
		List<ResidentVo> residentVos= this.getResidentVos(residents);
		return residentVos;
	}

	@Override
	public List<ResidentVo> getResidentsByName(String name) {
		List<Resident> residents= ResidentDao.getResidentsByName(name);
		List<ResidentVo> residentVos= this.getResidentVos(residents);
		return residentVos;
	}

	@Override
	public List<ResidentVo> getResidentsByType(int type) {
		List<Resident> residents= ResidentDao.getResidentsByType(type);
		List<ResidentVo> residentVos= this.getResidentVos(residents);
		return residentVos;
	}

	private List<ResidentVo> getResidentVos(List<Resident> residents){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		List<ResidentVo> residentVos = new ArrayList<ResidentVo>();
		for(int i=0;i<residents.size();i++){
			ResidentVo residentVo = new ResidentVo(residents.get(i));
			residentVos.add(residentVo);
		}
		return residentVos;
	}

	private ResidentVo getResidentVo(Resident resident){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		ResidentVo residentVo = new ResidentVo(resident);
		return residentVo;
	}


}
