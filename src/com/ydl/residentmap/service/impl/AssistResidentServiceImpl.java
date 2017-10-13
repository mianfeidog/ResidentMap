package com.ydl.residentmap.service.impl;

import com.ydl.residentmap.dao.AssistResidentDao;
import com.ydl.residentmap.dao.CadreDao;
import com.ydl.residentmap.model.AssistResident;
import com.ydl.residentmap.model.vo.AssistResidentVo;
import com.ydl.residentmap.service.AssistResidentService;
import com.ydl.residentmap.util.LatitudeUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class AssistResidentServiceImpl implements AssistResidentService {
    @Resource
    private AssistResidentDao assistResidentDao ;

    @Override
    public Boolean save(AssistResident assistResident) {
        String address = assistResident.getAddress().trim();
        //地址不为空，获取经纬度
        if(!"".equals(address)){
            Map<String,String> lngLat = LatitudeUtils.getGeocoderLatitude(address);
            if(lngLat!=null){
                String lng = lngLat.get("lng");
                String lat = lngLat.get("lat");
                assistResident.setLng(lng);
                assistResident.setLat(lat);
            }
            else{
                assistResident.setLng("");
                assistResident.setLat("");
            }
        }

        return assistResidentDao.save(assistResident);
    }

    @Override
    public Integer deleteList(List<String> idList) {
        return assistResidentDao.deleteList(idList);
    }

    @Override
    public Boolean update(AssistResident assistResident) {
        String address = assistResident.getAddress().trim();
        //地址不为空，获取经纬度
        if(!"".equals(address)){
            Map<String,String> lngLat = LatitudeUtils.getGeocoderLatitude(address);
            if(lngLat!=null){
                String lng = lngLat.get("lng");
                String lat = lngLat.get("lat");
                assistResident.setLng(lng);
                assistResident.setLat(lat);
            }
            else{
                assistResident.setLng("");
                assistResident.setLat("");
            }
        }

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