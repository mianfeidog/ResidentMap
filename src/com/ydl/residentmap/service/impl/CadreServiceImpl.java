package com.ydl.residentmap.service.impl;

import com.ydl.residentmap.constants.CommonConst;
import com.ydl.residentmap.dao.CadreDao;
import com.ydl.residentmap.model.Cadre;
import com.ydl.residentmap.model.vo.CadreVo;
import com.ydl.residentmap.service.CadreService;
import com.ydl.residentmap.util.IdWorker;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class CadreServiceImpl implements CadreService{
    @Resource
    private CadreDao cadreDao ;

    @Override
    public Boolean save(Cadre cadre) {
        Random random = new Random();
        cadre.setId(new IdWorker((long) random.nextInt(15)).nextId());
        //创建时间
        Date now = new Date();
        String sdate=(new SimpleDateFormat("yyyyMMddHHmm")).format(now);
        Long dateLong = Long.parseLong(sdate);
        cadre.setCreateAt(dateLong);

        return cadreDao.save(cadre);
    }

    @Override
    public Integer deleteList(List<String> idList) {
        return cadreDao.deleteList(idList);
    }

    @Override
    public Boolean update(Cadre cadre) {
        return cadreDao.update(cadre);
    }

    @Override
    public List<Cadre> getAllCadres() {
        return cadreDao.getAllCadres();
    }

    @Override
    public List<Cadre> getCadresByName(String name) {
        return cadreDao.getCadresByName(name);
    }

    @Override
    public Cadre getCadreById(Long id) {
        return cadreDao.getCadreById(id);
    }

    @Override
    public List<CadreVo> getAllCadreVos() {
        return cadreDao.getAllCadreVos();
    }

    @Override
    public List<CadreVo> getCadreVosByName(String name) {
        return cadreDao.getCadreVosByName(name);
    }

    @Override
    public CadreVo getCadreVoById(Long id) {
        return cadreDao.getCadreVoById(id);
    }

    @Override
    public List<Cadre> getKeyPersonsByIdCard(String idCard, String action, Long id) {
        return cadreDao.getKeyPersonsByIdCard(idCard,action,id);
    }
}
