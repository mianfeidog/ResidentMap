package com.ydl.residentmap.service.impl;

import com.ydl.residentmap.constants.CommonConst;
import com.ydl.residentmap.constants.ResultMessage;
import com.ydl.residentmap.dao.CadreDao;
import com.ydl.residentmap.model.Cadre;
import com.ydl.residentmap.model.vo.CadreVo;
import com.ydl.residentmap.service.CadreService;
import com.ydl.residentmap.util.IdWorker;
import com.ydl.residentmap.util.LatitudeUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class CadreServiceImpl implements CadreService{
    @Resource
    private CadreDao cadreDao ;

    @Override
    public Boolean save(Cadre cadre) {
        Long communityId = cadre.getCommunityId();
        if(communityId==null)
        {
            throw new RuntimeException(ResultMessage.EMPTY_COMMUNITY_ID);
        }

        String idCard = cadre.getIdCard();
        if(idCard!=null && !idCard.trim().equals(""))
        {
            List<Cadre> cadres = cadreDao.getCadresByIdCard(idCard, CommonConst.ACTION_ADD,cadre.getId());
            if(cadres.size()>0)
            {
                throw new RuntimeException(ResultMessage.DUPLICATE_IDCARD);
            }
        }
        else
        {
            throw new RuntimeException(ResultMessage.EMPTY_ID_CARD);
        }

        String address = cadre.getAddress().trim();
        //地址不为空，获取经纬度
        if(!"".equals(address))
        {
            Map<String,String> lngLat = LatitudeUtils.getGeocoderLatitude(address);
            if(lngLat!=null){
                String lng = lngLat.get("lng");
                String lat = lngLat.get("lat");
                cadre.setLng(lng);
                cadre.setLat(lat);
            }
            else{
                throw new RuntimeException(ResultMessage.NO_LNG_LAT);
            }
        }
        else
        {
            throw new RuntimeException(ResultMessage.EMPTY_ADDRESS);
        }

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
        Long communityId = cadre.getCommunityId();
        if(communityId==null)
        {
            throw new RuntimeException(ResultMessage.EMPTY_COMMUNITY_ID);
        }

        String idCard = cadre.getIdCard();
        if(idCard!=null && !idCard.trim().equals(""))
        {
            List<Cadre> cadres = cadreDao.getCadresByIdCard(idCard, CommonConst.ACTION_EDIT,cadre.getId());
            if(cadres.size()>0)
            {
                throw new RuntimeException(ResultMessage.DUPLICATE_IDCARD);
            }
        }
        else
        {
            throw new RuntimeException(ResultMessage.EMPTY_ID_CARD);
        }


        String address = cadre.getAddress().trim();
        //地址不为空，获取经纬度
        if(!"".equals(address)){
            Map<String,String> lngLat = LatitudeUtils.getGeocoderLatitude(address);
            if(lngLat!=null){
                String lng = lngLat.get("lng");
                String lat = lngLat.get("lat");
                cadre.setLng(lng);
                cadre.setLat(lat);
            }
            else{
                throw new RuntimeException(ResultMessage.NO_LNG_LAT);
            }
        }
        else
        {
            throw new RuntimeException(ResultMessage.EMPTY_ADDRESS);
        }

        //创建时间
        Date now = new Date();
        String sdate=(new SimpleDateFormat("yyyyMMddHHmm")).format(now);
        Long dateLong = Long.parseLong(sdate);
        cadre.setCreateAt(dateLong);

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
    public List<Cadre> getCadresByIdCard(String idCard, String action, Long id) {
        return cadreDao.getCadresByIdCard(idCard,action,id);
    }
}
