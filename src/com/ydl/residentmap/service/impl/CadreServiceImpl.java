package com.ydl.residentmap.service.impl;

import com.ydl.residentmap.constants.CommonConst;
import com.ydl.residentmap.constants.ResultMessage;
import com.ydl.residentmap.dao.CadreDao;
import com.ydl.residentmap.model.Cadre;
import com.ydl.residentmap.model.vo.CadreVo;
import com.ydl.residentmap.service.CadreService;
import com.ydl.residentmap.util.CommonUtil;
import com.ydl.residentmap.util.IdWorker;
import com.ydl.residentmap.util.LatitudeUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

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

//        String address = cadre.getAddress().trim();
//        //地址不为空，获取经纬度
//        if(!"".equals(address))
//        {
//            Map<String,String> lngLat = LatitudeUtils.getGeocoderLatitude(address);
//            if(lngLat!=null){
//                String lng = lngLat.get("lng");
//                String lat = lngLat.get("lat");
//                cadre.setLng(lng);
//                cadre.setLat(lat);
//            }
//            else{
//                throw new RuntimeException(ResultMessage.NO_LNG_LAT);
//            }
//        }
//        else
//        {
//            throw new RuntimeException(ResultMessage.EMPTY_ADDRESS);
//        }

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


//        String address = cadre.getAddress().trim();
//        //地址不为空，获取经纬度
//        if(!"".equals(address)){
//            Map<String,String> lngLat = LatitudeUtils.getGeocoderLatitude(address);
//            if(lngLat!=null){
//                String lng = lngLat.get("lng");
//                String lat = lngLat.get("lat");
//                cadre.setLng(lng);
//                cadre.setLat(lat);
//            }
//            else{
//                throw new RuntimeException(ResultMessage.NO_LNG_LAT);
//            }
//        }
//        else
//        {
//            throw new RuntimeException(ResultMessage.EMPTY_ADDRESS);
//        }

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
    public List<CadreVo> getCadreVosByCondition(HashMap<String,String> map) {
        return cadreDao.getCadreVosByCondition(map);
    }

    @Override
    public HSSFWorkbook exportExcel(List<CadreVo> cadreVos){
        //设置表格标题行
        String[] headers = new String[] {"姓名","职务", "性别","出生年月","文化程度", "入党时间", "身份证号","家庭住址","联系方式","所属社区"};
        List<List<String>> dataList = new ArrayList<List<String>>();
        for(int i=0;i<cadreVos.size();i++) {
            CadreVo cadreVo = cadreVos.get(i);
            List<String> list = new ArrayList<String>();
            list.add(cadreVo.getName());          //姓名
            list.add(cadreVo.getPositionName());  //职务
            list.add(cadreVo.getGender()==1?"男":"女");
            list.add(cadreVo.getBirthday().toString());      //出生年月
            list.add(cadreVo.getEducationName()); //文化程度
            list.add(cadreVo.getJoinDate().toString());      //入党时间
            list.add(cadreVo.getIdCard());          //身份证号
            list.add(cadreVo.getAddress());         //家庭住址
            list.add(cadreVo.getLink());            //联系方式
            list.add(cadreVo.getCommunityName());   //所属社区
            dataList.add(list);
        }
        HSSFWorkbook workbook = CommonUtil.setExcel(headers,dataList);
        return workbook;
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
