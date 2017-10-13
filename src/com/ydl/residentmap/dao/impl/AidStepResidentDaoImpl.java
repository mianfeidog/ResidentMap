package com.ydl.residentmap.dao.impl;

import com.ydl.residentmap.dao.AidStepResidentDao;
import com.ydl.residentmap.dao.BaseDao;
import com.ydl.residentmap.model.AidResident;
import com.ydl.residentmap.model.AidStepResident;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * 帮扶措施与帮扶对象关联
 * Created by 小强 on 2017/7/20.
 */
@Repository
public class AidStepResidentDaoImpl implements AidStepResidentDao {
    @Resource
    BaseDao<AidStepResident> baseDao;
    @Resource
    BaseDao<AidResident> baseDao1;
    @Override
    public Boolean save(AidStepResident aidStepResident) {
        Boolean rs = true;
        try{
            baseDao.save(aidStepResident);
        }catch (Exception e){
            e.printStackTrace();
            rs = false;
        }
        return rs;
    }

    @Override
    public Boolean deleteByStepId(Long stepId) {
        Boolean rs = true;
        try{
            String hql ="delete from AidStepResident where aidStepId =?";
            baseDao.executeHql(hql,new Object[]{stepId});
        }catch (Exception e){
            e.printStackTrace();
            rs = false;
        }
        return rs;
    }

    @Override
    public List<AidResident> getAidResidentByStepId(Long stepId) {
        String hql ="SELECT ar.id id,ar.aid_type aidType,ar.name name FROM aid_step_resident  asr "
                + " INNER JOIN aid_resident ar ON asr.resident_id = ar.id "
                + " WHERE asr.step_id =?";
        return baseDao1.getResultBySQL(hql,new Object[]{stepId});
    }
}
