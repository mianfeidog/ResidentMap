package com.ydl.residentmap.service.impl;

import com.ydl.residentmap.dao.AidStepDao;
import com.ydl.residentmap.dao.AidStepResidentDao;
import com.ydl.residentmap.model.AidResident;
import com.ydl.residentmap.model.AidStep;
import com.ydl.residentmap.model.AidStepResident;
import com.ydl.residentmap.model.vo.AidStepVo;
import com.ydl.residentmap.service.AidStepService;
import com.ydl.residentmap.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.TransactionScoped;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 *帮扶措施
 * Created by 小强 on 2017/7/20.
 */
@Service
public class AidStepServiceImpl implements AidStepService {
    @Autowired
    AidStepDao aidStepDao;
    @Autowired
    AidStepResidentDao aidStepResidentDao;

    @Override
    public Boolean save(AidStep aidStep) {
        Random random = new Random();
        IdWorker idWorker = new IdWorker((long)random.nextInt(15));
        aidStep.setId(idWorker.nextId() );
        //创建时间
        aidStep.setCreatedAt(new Date());
        return aidStepDao.save(aidStep);
    }

    @Transactional
    @Override
    public Boolean update(AidStep aidStep) {
        //先删除原有关联
        aidStepResidentDao.deleteByStepId(aidStep.getId());
        //获取关联的帮扶对象
        List<Long> residentList = aidStep.getAidResidentList();
        if(residentList != null && residentList.size()>0){
            //保存关系
            for(int i=0;i<residentList.size();i++){
                Long residentId = residentList.get(i);

                AidStepResident aidStepResident = new AidStepResident();
                Random random = new Random();
                IdWorker idWorker = new IdWorker((long)random.nextInt(15));
                aidStepResident.setId(idWorker.nextId() );
                aidStepResident.setAidStepId(aidStep.getId());
                aidStepResident.setResidentId(residentId);

                aidStepResidentDao.save(aidStepResident);
            }
        }

        //查询帮扶措施
        AidStep aidStep1 = aidStepDao.get(aidStep.getId());

        aidStep.setCreatedAt(aidStep1.getCreatedAt());
        aidStep.setCreatedBy(aidStep1.getCreatedBy());
        return aidStepDao.update(aidStep);
    }

    @Override
    @Transactional
    public Boolean delete(Long id) {
        //删除关联关系
        aidStepResidentDao.deleteByStepId(id);
        //删除帮扶措施
        return aidStepDao.delete(id);
    }

    @Override
    public List<AidStep> getAll() {
        return aidStepDao.getAll();
    }

    @Override
    public AidStepVo get(Long id) {
        //查询帮扶措施
        AidStep aidStep = aidStepDao.get(id);
        //根据帮扶措施id 查询 关联的帮扶对象
        List<AidResident> aidResidents = aidStepResidentDao.getAidResidentByStepId(id);

        AidStepVo aidStepVo = new AidStepVo();
        aidStepVo.setId(aidStep.getId());
        aidStepVo.setTitle(aidStep.getTitle());
        aidStepVo.setContent(aidStep.getContent());
        aidStepVo.setCreatedAt(aidStep.getCreatedAt());
        aidStepVo.setResidentList(aidResidents);
        return aidStepVo;
    }
}
