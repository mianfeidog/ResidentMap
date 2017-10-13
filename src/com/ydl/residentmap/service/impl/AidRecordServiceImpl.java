package com.ydl.residentmap.service.impl;

import com.ydl.residentmap.dao.AidRecordDao;
import com.ydl.residentmap.model.AidRecord;
import com.ydl.residentmap.model.vo.AidRecordVo;
import com.ydl.residentmap.service.AidRecordService;
import com.ydl.residentmap.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 帮扶记录
 * Created by 小强 on 2017/7/20.
 */
@Service
public class AidRecordServiceImpl implements AidRecordService {
    @Autowired
    AidRecordDao aidRecordDao;
    @Override
    public Boolean save(AidRecord aidRecord) {
        Random random = new Random();
        IdWorker idWorker = new IdWorker((long)random.nextInt(15));
        aidRecord.setId(idWorker.nextId() );
        //创建时间
        aidRecord.setCreatedAt(new Date());
        return aidRecordDao.save(aidRecord);
    }

    @Override
    public Boolean update(AidRecord aidRecord) {
        //查询帮扶计划
        AidRecord aidRecord1 = aidRecordDao.get(aidRecord.getId());
        aidRecord.setCreatedAt(aidRecord1.getCreatedAt());
        aidRecord.setCreatedBy(aidRecord1.getCreatedBy());
        return aidRecordDao.update(aidRecord);
    }

    @Override
    public Boolean delete(Long id) {
        return aidRecordDao.delete(id);
    }

    @Override
    public List<AidRecordVo> getAidRecordsByUserId(Long userId) {
        return aidRecordDao.getAidRecordsByUserId(userId);
    }

    @Override
    public AidRecordVo get(Long id) throws Exception {
        return aidRecordDao.getVo(id);
    }
}
