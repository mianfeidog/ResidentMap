package com.ydl.residentmap.dao.impl;

import com.ydl.residentmap.dao.AidStepDao;
import com.ydl.residentmap.dao.BaseDao;
import com.ydl.residentmap.model.AidStep;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * 帮扶措施
 * Created by 小强 on 2017/7/20.
 */
@Repository
public class AidStepImpl implements AidStepDao {
    @Resource
    BaseDao<AidStep> baseDao;

    @Override
    public Boolean save(AidStep aidStep) {
        Boolean rs = true;
        try{
            baseDao.save(aidStep);
        }catch (Exception e){
            e.printStackTrace();
            rs = false;
        }
        return rs;
    }

    @Override
    public Boolean update(AidStep aidStep) {
        Boolean rs = true;
        try{
            baseDao.update(aidStep);
        }catch (Exception e){
            e.printStackTrace();
            rs = false;
        }
        return rs;
    }

    @Override
    public Boolean delete(Long id) {
        Boolean rs = true;
        try{
            String hql ="delete from AidStep where id =?";
            baseDao.executeHql(hql,new Object[]{id});
        }catch (Exception e){
            e.printStackTrace();
            rs = false;
        }
        return rs;
    }

    @Override
    public AidStep get(Long id) {
        return baseDao.get(AidStep.class,id);
    }

    @Override
    public List<AidStep> getAll() {
        String hql ="from AidStep order by createdAt desc";
        return baseDao.find(hql);
    }
}
