package com.ydl.residentmap.service.impl;

import com.ydl.residentmap.constants.ResultMessage;
import com.ydl.residentmap.dao.AssistResidentDao;
import com.ydl.residentmap.dao.CadreDao;
import com.ydl.residentmap.model.AssistResident;
import com.ydl.residentmap.model.vo.AssistResidentVo;
import com.ydl.residentmap.service.AssistResidentService;
import com.ydl.residentmap.util.CommonUtil;
import com.ydl.residentmap.util.IdWorker;
import com.ydl.residentmap.util.LatitudeUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AssistResidentServiceImpl implements AssistResidentService {
    @Resource
    private AssistResidentDao assistResidentDao ;

    @Override
    public Boolean save(AssistResident assistResident) {
        Long blockId = assistResident.getBlockId();
        if(blockId==null)
        {
            throw new RuntimeException(ResultMessage.EMPTY_BLOCK_ID);
        }

//        String address = assistResident.getAddress().trim();
//        //地址不为空，获取经纬度
//        if(!"".equals(address)){
//            Map<String,String> lngLat = LatitudeUtils.getGeocoderLatitude(address);
//            if(lngLat!=null){
//                String lng = lngLat.get("lng");
//                String lat = lngLat.get("lat");
//                assistResident.setLng(lng);
//                assistResident.setLat(lat);
//            }
//            else{
//                throw new RuntimeException(ResultMessage.NO_LNG_LAT);
//            }
//        }

        //创建时间
        Date now = new Date();
        String sdate=(new SimpleDateFormat("yyyyMMddHHmm")).format(now);
        Long dateLong = Long.parseLong(sdate);
        assistResident.setCreateAt(dateLong);

        Random random = new Random();
        assistResident.setId(new IdWorker((long)random.nextInt(15)).nextId());

        return assistResidentDao.save(assistResident);
    }

    @Override
    public Integer deleteList(List<String> idList) {
        return assistResidentDao.deleteList(idList);
    }

    @Override
    public Boolean update(AssistResident assistResident) {
        Long blockId = assistResident.getBlockId();
        if(blockId==null)
        {
            throw new RuntimeException(ResultMessage.EMPTY_BLOCK_ID);
        }

//        String address = assistResident.getAddress().trim();
//        //地址不为空，获取经纬度
//        if(!"".equals(address)){
//            Map<String,String> lngLat = LatitudeUtils.getGeocoderLatitude(address);
//            if(lngLat!=null){
//                String lng = lngLat.get("lng");
//                String lat = lngLat.get("lat");
//                assistResident.setLng(lng);
//                assistResident.setLat(lat);
//            }
//            else{
//                throw new RuntimeException(ResultMessage.NO_LNG_LAT);
//            }
//        }

        Date now = new Date();
        String sdate=(new SimpleDateFormat("yyyyMMddHHmm")).format(now);
        Long dateLong = Long.parseLong(sdate);
        assistResident.setCreateAt(dateLong);

        return assistResidentDao.update(assistResident);
    }

    @Override
    public List<AssistResident> getAllAssistResidents() {
        return assistResidentDao.getAllAssistResidents();
    }

    @Override
    public List<AssistResident> getAssistResidentsByName(String name) {
        return assistResidentDao.getAssistResidentsByName(name);
    }

    @Override
    public List<AssistResident> getAssistResidentsByTypes(List<String> types) {
        return assistResidentDao.getAssistResidentsByTypes(types);
    }

    @Override
    public AssistResident getAssistResidentById(Long id) {
        return assistResidentDao.getAssistResidentById(id);
    }

    @Override
    public List<AssistResidentVo> getAllAssistResidentVos() {
        return assistResidentDao.getAllAssistResidentVos();
    }

    @Override
    public List<AssistResidentVo> getAssistResidentVosByName(String name) {
        return assistResidentDao.getAssistResidentVosByName(name);
    }

    @Override
    public List<AssistResidentVo> getAssistResidentVosByCondition(HashMap<String,String> map) {
        return assistResidentDao.getAssistResidentVosByCondition(map);
    }

    @Override
    public HSSFWorkbook exportExcel(List<AssistResidentVo> assistResidentVos) {
        //设置表格标题行
        String[] headers = new String[] {"姓名","性别","出生年月","家庭人口", "家庭年收入(元)", "居民类型","已享受惠民政策及标准","家庭住址","联系方式","残疾证等级","残疾证号码","所属小区"};
        List<List<String>> dataList = new ArrayList<List<String>>();
        for(int i=0;i<assistResidentVos.size();i++) {
            AssistResidentVo assistResidentVo=assistResidentVos.get(i);
            List<String> list = new ArrayList<String>();
            list.add(assistResidentVo.getName());                       //姓名
            list.add(assistResidentVo.getGender()==1?"男":"女");       //性别
            list.add(assistResidentVo.getBirthday().toString());        //出生年月
            list.add(assistResidentVo.getFamilyMemberCount().toString());   //家庭人口
            list.add(assistResidentVo.getFamilyYearIncome().toString());    //家庭年收入

            list.add(assistResidentVo.getTypeName());                       //居民类型
            list.add(assistResidentVo.getReceivePolicyStandard());          //已享受惠民政策及标准
            list.add(assistResidentVo.getAddress());
            list.add(assistResidentVo.getLink());
            list.add(assistResidentVo.getDeformityCardRank());              //残疾证等级

            list.add(assistResidentVo.getDeformityCertificateNum());        //残疾证号码
            list.add(assistResidentVo.getCommunityName());

            dataList.add(list);
        }
        HSSFWorkbook workbook = CommonUtil.setExcel(headers,dataList);
        return workbook;
    }

    @Override
    public List<AssistResidentVo> getAssistResidentVosByTypes(List<String> types) {
        return assistResidentDao.getAssistResidentVosByTypes(types);
    }

    @Override
    public List<AssistResidentVo> getAssistResidentVosByTypesName(List<String> types, String name) {
        return assistResidentDao.getAssistResidentVosByTypesName(types,name);
    }

    @Override
    public AssistResidentVo getAssistResidentVoById(Long id) {
        return assistResidentDao.getAssistResidentVoById(id);
    }
}
