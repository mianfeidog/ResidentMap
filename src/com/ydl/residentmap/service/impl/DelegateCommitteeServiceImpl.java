package com.ydl.residentmap.service.impl;

import com.ydl.residentmap.constants.ResultMessage;
import com.ydl.residentmap.dao.DelegateCommitteeDao;
import com.ydl.residentmap.model.DelegateCommittee;
import com.ydl.residentmap.model.vo.DelegateCommitteeVo;
import com.ydl.residentmap.service.DelegateCommitteeService;
import com.ydl.residentmap.util.CommonUtil;
import com.ydl.residentmap.util.IdWorker;
import com.ydl.residentmap.util.LatitudeUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class DelegateCommitteeServiceImpl implements DelegateCommitteeService {

    @Resource
    private DelegateCommitteeDao delegateCommitteeDao ;

    @Override
    public Boolean save(DelegateCommittee delegateCommittee) {
        Long communityId = delegateCommittee.getCommunityId();
        if(communityId==null)
        {
            throw new RuntimeException(ResultMessage.EMPTY_COMMUNITY_ID);
        }

//        String address = delegateCommittee.getAddress().trim();
//        //地址不为空，获取经纬度
//        if(!"".equals(address))
//        {
//            Map<String,String> lngLat = LatitudeUtils.getGeocoderLatitude(address);
//            if(lngLat!=null){
//                String lng = lngLat.get("lng");
//                String lat = lngLat.get("lat");
//                delegateCommittee.setLng(lng);
//                delegateCommittee.setLat(lat);
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
        delegateCommittee.setId(new IdWorker((long) random.nextInt(15)).nextId());

        //创建时间
        Date now = new Date();
        String sdate=(new SimpleDateFormat("yyyyMMddHHmm")).format(now);
        Long dateLong = Long.parseLong(sdate);
        delegateCommittee.setCreateAt(dateLong);
        return delegateCommitteeDao.save(delegateCommittee);
    }

    @Override
    public Integer deleteList(List<String> idList) {
        return delegateCommitteeDao.deleteList(idList);
    }

    @Override
    public Boolean update(DelegateCommittee delegateCommittee) {
        Long communityId = delegateCommittee.getCommunityId();
        if(communityId==null)
        {
            throw new RuntimeException(ResultMessage.EMPTY_COMMUNITY_ID);
        }

//        String address = delegateCommittee.getAddress().trim();
//        //地址不为空，获取经纬度
//        if(!"".equals(address))
//        {
//            Map<String,String> lngLat = LatitudeUtils.getGeocoderLatitude(address);
//            if(lngLat!=null){
//                String lng = lngLat.get("lng");
//                String lat = lngLat.get("lat");
//                delegateCommittee.setLng(lng);
//                delegateCommittee.setLat(lat);
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
        delegateCommittee.setCreateAt(dateLong);

        return delegateCommitteeDao.update(delegateCommittee);
    }

    @Override
    public List<DelegateCommittee> getAllDelegateCommittees() {
        return delegateCommitteeDao.getAllDelegateCommittees();
    }

    @Override
    public List<DelegateCommittee> getDelegateCommitteesByName(String name) {
        return delegateCommitteeDao.getDelegateCommitteesByName(name);
    }

    @Override
    public DelegateCommittee getDelegateCommitteeById(Long id) {
        return delegateCommitteeDao.getDelegateCommitteeById(id);
    }

    @Override
    public List<DelegateCommitteeVo> getAllDelegateCommitteeVos() {
        return delegateCommitteeDao.getAllDelegateCommitteeVos();
    }

    @Override
    public List<DelegateCommitteeVo> getDelegateCommitteeVosByName(String name) {
        return delegateCommitteeDao.getDelegateCommitteeVosByName(name);
    }

    @Override
    public List<DelegateCommitteeVo> getDelegateCommitteeVosByCondition(HashMap<String,String> map) {
        return delegateCommitteeDao.getDelegateCommitteeVosByCondition(map);
    }

    @Override
    public HSSFWorkbook exportExcel(List<DelegateCommitteeVo> delegateCommitteeVos) {
        //设置表格标题行
        String[] headers = new String[] {"姓名","性别", "民族","出生年月","文化程度", "党派", "何届省、市、区党代表或人大代表或政协委员","家庭住址","联系方式","所属社区"};
        List<List<String>> dataList = new ArrayList<List<String>>();
        for(int i=0;i<delegateCommitteeVos.size();i++) {
            DelegateCommitteeVo delegateCommitteeVo = delegateCommitteeVos.get(i);
            List<String> list = new ArrayList<String>();

            list.add(delegateCommitteeVo.getName());
            list.add(delegateCommitteeVo.getGender()==1?"男":"女");
            list.add(delegateCommitteeVo.getMinorityName());
            list.add(delegateCommitteeVo.getBirthday().toString());
            list.add(delegateCommitteeVo.getEducationName());

            list.add(delegateCommitteeVo.getPartyName());
            list.add(delegateCommitteeVo.getAppointPost());
            list.add(delegateCommitteeVo.getAddress());
            list.add(delegateCommitteeVo.getLink());
            list.add(delegateCommitteeVo.getCommunityName());

            dataList.add(list);
        }

        HSSFWorkbook workbook = CommonUtil.setExcel(headers,dataList);
        return workbook;
    }

    @Override
    public DelegateCommitteeVo getDelegateCommitteeVoById(Long id) {
        return delegateCommitteeDao.getDelegateCommitteeVoById(id);
    }
}
