package com.ydl.residentmap.service.impl;

import com.ydl.residentmap.constants.CommonConst;
import com.ydl.residentmap.constants.ResultMessage;
import com.ydl.residentmap.dao.PartyMemberDao;
import com.ydl.residentmap.model.PartyMember;
import com.ydl.residentmap.model.vo.PartyMemberVo;
import com.ydl.residentmap.service.PartyMemberService;
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
    public List<PartyMemberVo> getPartyMemberVosByCondition(Map<String,String> map) {
        return partyMemberDao.getPartyMemberVosByCondition(map);
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
