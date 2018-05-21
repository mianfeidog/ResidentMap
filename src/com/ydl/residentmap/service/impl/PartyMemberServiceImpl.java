package com.ydl.residentmap.service.impl;

import com.ydl.residentmap.constants.CommonConst;
import com.ydl.residentmap.constants.ResultMessage;
import com.ydl.residentmap.dao.PartyMemberDao;
import com.ydl.residentmap.model.PartyMember;
import com.ydl.residentmap.model.vo.PartyMemberVo;
import com.ydl.residentmap.service.PartyMemberService;
import com.ydl.residentmap.util.CommonUtil;
import com.ydl.residentmap.util.IdWorker;
import com.ydl.residentmap.util.LatitudeUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PartyMemberServiceImpl implements PartyMemberService {

    @Resource
    private PartyMemberDao partyMemberDao ;

    @Override
    public Boolean save(PartyMember partyMember) {
        Long communityId=partyMember.getCommunityId();
        if(communityId==null)
        {
            throw new RuntimeException(ResultMessage.EMPTY_COMMUNITY_ID);
        }

        String idCard = partyMember.getIdCard();
        if(idCard!=null && !idCard.trim().equals(""))
        {
            List<PartyMember> partyMemberList = partyMemberDao.getPartyMembersByIdCard(idCard, CommonConst.ACTION_ADD,partyMember.getId());
            if(partyMemberList.size()>0)
            {
                throw new RuntimeException(ResultMessage.DUPLICATE_IDCARD);
            }
        }
        else
        {
            throw new RuntimeException(ResultMessage.EMPTY_IDCARD);
        }

//        String address = partyMember.getAddress().trim();
//        //地址不为空，获取经纬度
//        if(!"".equals(address))
//        {
//            Map<String,String> lngLat = LatitudeUtils.getGeocoderLatitude(address);
//            if(lngLat!=null){
//                String lng = lngLat.get("lng");
//                String lat = lngLat.get("lat");
//                partyMember.setLng(lng);
//                partyMember.setLat(lat);
//            }
//            else
//            {
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
        partyMember.setCreateAt(dateLong);

        Random random = new Random();
        partyMember.setId(new IdWorker((long) random.nextInt(15)).nextId());
        return partyMemberDao.save(partyMember);
    }

    @Override
    public Integer deleteList(List<String> idList) {
        return partyMemberDao.deleteList(idList);
    }

    @Override
    public Boolean update(PartyMember partyMember) {
        Long communityId=partyMember.getCommunityId();
        if(communityId==null)
        {
            throw new RuntimeException(ResultMessage.EMPTY_COMMUNITY_ID);
        }

        String idCard = partyMember.getIdCard();
        if(idCard!=null && !idCard.trim().equals(""))
        {
            List<PartyMember> partyMemberList = partyMemberDao.getPartyMembersByIdCard(idCard, CommonConst.ACTION_EDIT,partyMember.getId());
            if(partyMemberList.size()>0)
            {
                throw new RuntimeException(ResultMessage.DUPLICATE_IDCARD);
            }
        }
        else
        {
            throw new RuntimeException(ResultMessage.EMPTY_IDCARD);
        }

//        String address = partyMember.getAddress().trim();
//        //地址不为空，获取经纬度
//        if(!"".equals(address))
//        {
//            Map<String,String> lngLat = LatitudeUtils.getGeocoderLatitude(address);
//            if(lngLat!=null){
//                String lng = lngLat.get("lng");
//                String lat = lngLat.get("lat");
//                partyMember.setLng(lng);
//                partyMember.setLat(lat);
//            }
//            else
//            {
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
        partyMember.setCreateAt(dateLong);

        return partyMemberDao.update(partyMember);
    }

    @Override
    public List<PartyMember> getAllPartyMembers() {
        return partyMemberDao.getAllPartyMembers();
    }

    @Override
    public List<PartyMember> getPartyMembersByName(String name) {
        return partyMemberDao.getPartyMembersByName(name);
    }

    @Override
    public PartyMember getPartyMemberById(Long id) {
        return partyMemberDao.getPartyMemberById(id);
    }

    @Override
    public List<PartyMemberVo> getAllPartyMemberVos() {
        return partyMemberDao.getAllPartyMemberVos();
    }

    @Override
    public List<PartyMemberVo> getPartyMemberVosByName(String name) {
        return partyMemberDao.getPartyMemberVosByName(name);
    }

    @Override
    public List<PartyMemberVo> getPartyMemberVosByCondition(HashMap<String,String> map) {
        return partyMemberDao.getPartyMemberVosByCondition(map);
    }

    @Override
    public List<PartyMemberVo> getPartyMemberVosByCondition(Map<String,String> map) {
        return partyMemberDao.getPartyMemberVosByCondition(map);
    }

    @Override
    public HSSFWorkbook exportExcel(List<PartyMemberVo> partyMemberVos) {
        //设置表格标题行
        String[] headers = new String[] {"姓名", "性别","民族","出生年月","文化程度", "入党时间", "认领岗位","身份证号","家庭住址","联系方式","是否困难党员","所属社区"};
        List<List<String>> dataList = new ArrayList<List<String>>();
        for(int i=0;i<partyMemberVos.size();i++) {
            PartyMemberVo partyMemberVo = partyMemberVos.get(i);
            List<String> list = new ArrayList<String>();

            list.add(partyMemberVo.getName());
            list.add(partyMemberVo.getGender()==1?"男":"女");
            list.add(partyMemberVo.getMinorityName());
            list.add(partyMemberVo.getBirthday().toString());
            list.add(partyMemberVo.getEducationName());

            list.add(partyMemberVo.getJoinDate().toString());
            list.add(partyMemberVo.getClaimPostName());
            list.add(partyMemberVo.getIdCard());
            list.add(partyMemberVo.getAddress());
            list.add(partyMemberVo.getLink());

            list.add(partyMemberVo.getDifficult()==true?"是":"否");
            list.add(partyMemberVo.getCommunityName());

            dataList.add(list);
        }

        HSSFWorkbook workbook = CommonUtil.setExcel(headers,dataList);
        return workbook;
    }

    @Override
    public PartyMemberVo getPartyMemberVoById(Long id) {
        return partyMemberDao.getPartyMemberVoById(id);
    }

    @Override
    public List<PartyMember> getPartyMembersByIdCard(String idCard, String action, Long id) {
        return partyMemberDao.getPartyMembersByIdCard(idCard,action,id);
    }
}
