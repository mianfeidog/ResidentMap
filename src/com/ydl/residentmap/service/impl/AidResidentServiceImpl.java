package com.ydl.residentmap.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import com.ydl.residentmap.model.vo.AidResidentVo;
import com.ydl.residentmap.util.IdWorker;
import com.ydl.residentmap.util.ImageUtils;
import org.springframework.stereotype.Service;

import com.ydl.residentmap.dao.AidResidentDao;
import com.ydl.residentmap.model.AidResident;
import com.ydl.residentmap.service.AidResidentService;

/**
 * 帮扶对象
 */
@Service
public class AidResidentServiceImpl implements AidResidentService {
	
	@Resource
	private AidResidentDao aidResidentDao;

	@Override
	public Boolean save(AidResident aidResident) throws Exception{
		if(aidResident.getImg() != null){
			//解码BASE64 图片，并保存
			String fileName = ImageUtils.decodeBase64ToImg("images\\aidResident\\header\\",aidResident.getImg());
			aidResident.setImg(fileName);
		}
		Random random=new Random();
		IdWorker idWorker = new IdWorker((long)random.nextInt(15));
		aidResident.setId(idWorker.nextId());
		aidResident.setCreatedAt(new Date());
		aidResident.setRemoved(false);

		return aidResidentDao.save(aidResident);
	}

	@Override
	public Boolean update(AidResident aidResident) {
		AidResident aidResident1 = aidResidentDao.getDetailInfo(aidResident.getId());
		aidResident.setCreatedAt(aidResident1.getCreatedAt());
		aidResident.setCreatedBy(aidResident1.getCreatedBy());
		aidResident.setRemoved(aidResident1.getRemoved());
		return aidResidentDao.update(aidResident);
	}

	@Override
	public Boolean delete(Long id) {
		//判断该扶贫对象是否被引用 -- 如果被引用 软删除，否则，直接删除
		return aidResidentDao.deleteSoft(id);
	}


	@Override
	public List<AidResident> getPositions(AidResident aidResident) {
		return aidResidentDao.getPositions(aidResident);
	}

	@Override
	public List<AidResident> getRemarkInfos(AidResidentVo aidResidentVo) {
		return aidResidentDao.getRemarkInfos(aidResidentVo,aidResidentVo.getPage(),aidResidentVo.getSize());
	}

	@Override
	public AidResident getRemarkInfo(Long id) {
		return aidResidentDao.getRemarkInfo(id);
	}

	@Override
	public AidResident getDetailInfo(Long id) {
		return aidResidentDao.getDetailInfo(id);
	}
}
