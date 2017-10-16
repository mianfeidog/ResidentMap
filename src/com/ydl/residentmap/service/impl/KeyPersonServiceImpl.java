package com.ydl.residentmap.service.impl;

import com.ydl.residentmap.dao.KeyPersonDao;
import com.ydl.residentmap.model.KeyPerson;
import com.ydl.residentmap.model.Pager;
import com.ydl.residentmap.model.vo.KeyPersonVo;
import com.ydl.residentmap.service.KeyPersonService;
import com.ydl.residentmap.util.LatitudeUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

@Service
public class KeyPersonServiceImpl implements KeyPersonService{

    @Resource
    private KeyPersonDao keyPersonDao ;

    @Override
    public Boolean save(KeyPerson keyPerson) {
        String address = keyPerson.getAddress().trim();
        //地址不为空，获取经纬度
        if(!"".equals(address)){
            Map<String,String> lngLat = LatitudeUtils.getGeocoderLatitude(address);
            if(lngLat!=null){
                String lng = lngLat.get("lng");
                String lat = lngLat.get("lat");
                keyPerson.setLng(lng);
                keyPerson.setLat(lat);
            }
            else{
                keyPerson.setLng("");
                keyPerson.setLat("");
            }
        }

        if(keyPerson.getName() == null){
            throw new RuntimeException("名字不能为空！");
        }
        return keyPersonDao.save(keyPerson);
    }

    @Override
    public Integer deleteList(List<String> idList) {
        return keyPersonDao.deleteList(idList);
    }

    @Override
    public Boolean update(KeyPerson keyPerson) {
        String address = keyPerson.getAddress().trim();
        //地址不为空，获取经纬度
        if(!"".equals(address)){
            Map<String,String> lngLat = LatitudeUtils.getGeocoderLatitude(address);
            if(lngLat!=null){
                String lng = lngLat.get("lng");
                String lat = lngLat.get("lat");
                keyPerson.setLng(lng);
                keyPerson.setLat(lat);
            }
            else{
                keyPerson.setLng("");
                keyPerson.setLat("");
            }
        }

        return keyPersonDao.update(keyPerson);
    }

    @Override
    public KeyPerson getKeyPersonById(Long id) {
        return keyPersonDao.getKeyPersonById(id);
    }

    @Override
    public List<KeyPerson> getAllKeyPersons() {
        return keyPersonDao.getAllKeyPersons();
    }

    @Override
    public List<KeyPerson> getKeyPersonsByName(String name) {
        return keyPersonDao.getKeyPersonsByName(name);
    }

    @Override
    public List<KeyPerson> getKeyPersonsByType(int type) {
        return keyPersonDao.getKeyPersonsByType(type);
    }

    @Override
    public List<KeyPersonVo> getKeyPersonVosByName(String name) {
        return keyPersonDao.getKeyPersonVosByName(name);
    }

    @Override
    public KeyPersonVo getKeyPersonVoById(Long id) {
        return keyPersonDao.getKeyPersonVoById(id);
    }

    @Override
    public List<KeyPersonVo> getKeyPersonVosByType(int type) {
        return keyPersonDao.getKeyPersonVosByType(type);
    }



    @Override
    public List<KeyPersonVo> getKeyPersonVosByTypes(List<String> typeList) {
        return keyPersonDao.getKeyPersonVosByTypes(typeList);
    }

    @Override
    public List<KeyPersonVo> getKeyPersonVosByTypesName(List<String> typeList, String name) {
        return keyPersonDao.getKeyPersonVosByTypesName(typeList,name);
    }

    @Override
    public Pager<KeyPerson> getKeyPersonsByPage(int offset, int size) {
        return keyPersonDao.getKeyPersonsByPage(offset,size);
    }

    @Override
    public List<KeyPersonVo> getKeyPersonVosByPage(int offset, int size) {
        return keyPersonDao.getKeyPersonVosByPage(offset,size);
    }

    @Override
    public List<KeyPersonVo> getAllKeyPersonVos() {
        return keyPersonDao.getAllKeyPersonVos();
    }

    @Override
    public List<KeyPerson> getKeyPersonsByIdCard(String idCard, String action, Long id) {
        return keyPersonDao.getKeyPersonsByIdCard(idCard,action,id);
    }
}
