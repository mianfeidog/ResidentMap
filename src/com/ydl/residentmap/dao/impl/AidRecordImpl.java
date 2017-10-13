package com.ydl.residentmap.dao.impl;

import com.ydl.residentmap.dao.AidRecordDao;
import com.ydl.residentmap.dao.BaseDao;
import com.ydl.residentmap.model.AidRecord;
import com.ydl.residentmap.model.vo.AidRecordVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * 帮扶记录
 * Created by 小强 on 2017/7/20.
 */
@Repository
public class AidRecordImpl implements AidRecordDao {
    @Resource
    BaseDao baseDAO;
    @Resource
    BaseDao<AidRecord> baseDao1;

    @Override
    public Boolean save(AidRecord aidRecord) {
        Boolean rs = true;
        try{
            baseDAO.save(aidRecord);
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
            String hql ="delete from AidRecord where id=?";
            baseDAO.executeHql(hql,new Object[]{id});
        }catch (Exception e){
            e.printStackTrace();
            rs = false;
        }
        return rs;
    }

    @Override
    public Boolean update(AidRecord aidRecord) {
        Boolean rs = true;
        try{
            baseDAO.update(aidRecord);
        }catch (Exception e){
            e.printStackTrace();
            rs = false;
        }
        return rs;
    }

    @Override
    public List<AidRecordVo> getAidRecordsByUserId(Long userId) {
        String hql ="SELECT ar.id id,ar.content content,ar.created_at createdAt,u.full_name aidCadre"                     +" from aid_record ar "
                    + " INNER JOIN USER u ON ar.created_by = u.id"
                    +"  where u.id=?";
        return baseDAO.getResultBySQL(hql,new Object[]{userId},AidRecordVo.class);
    }

    @Override
    public AidRecordVo getVo(Long id) throws Exception {

        String hql ="SELECT ar.id id,ar.content content,ar.created_at createdAt,u.full_name aidCadre"                     +" from aid_record ar "
                + " INNER JOIN USER u ON ar.created_by = u.id"
                +"  where ar.id=?";
        List<AidRecordVo> aidRecordVos =  baseDAO.getResultBySQL(hql,new Object[]{id},AidRecordVo.class);

        if(aidRecordVos != null && aidRecordVos.size()>0){
            return  aidRecordVos.get(0);
        }
        return null;
    }

    @Override
    public AidRecord get(Long id) {
        return baseDao1.get(AidRecord.class,id);
    }
}
