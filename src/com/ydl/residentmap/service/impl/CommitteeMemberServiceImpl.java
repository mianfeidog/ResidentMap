package com.ydl.residentmap.service.impl;

import com.ydl.residentmap.constants.CommonConst;
import com.ydl.residentmap.constants.ResultMessage;
import com.ydl.residentmap.dao.CommitteeMemberDao;
import com.ydl.residentmap.model.CommitteeMember;
import com.ydl.residentmap.model.vo.CommitteeMemberVo;
import com.ydl.residentmap.service.CommitteeMemberService;
import com.ydl.residentmap.util.LatitudeUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class CommitteeMemberServiceImpl implements CommitteeMemberService {
    @Resource
    private CommitteeMemberDao committeeMemberDao ;

    @Override
    public Boolean save(CommitteeMember committeeMember) {
        String idCard = committeeMember.getIdCard();
        if(idCard!=null && !idCard.trim().equals(""))
        {
            List<CommitteeMember> committeeMemberCheck =  committeeMemberDao.getCommitteeMemebersByIdCard(idCard, CommonConst.ACTION_ADD,committeeMember.getId());
            if(committeeMemberCheck.size()>0)
            {
                throw new RuntimeException(ResultMessage.DUPLICATE_IDCARD);
            }
        }

        String address = committeeMember.getAddress().trim();
        //地址不为空，获取经纬度
        if(!"".equals(address)){
            Map<String,String> lngLat = LatitudeUtils.getGeocoderLatitude(address);
            if(lngLat!=null){
                String lng = lngLat.get("lng");
                String lat = lngLat.get("lat");
                committeeMember.setLng(lng);
                committeeMember.setLat(lat);
            }
            else{
                throw new RuntimeException(ResultMessage.NO_LNG_LAT);
            }
        }
        else
        {
            throw new RuntimeException(ResultMessage.EMPTY_ADDRESS);
        }

        return committeeMemberDao.save(committeeMember);
    }

    @Override
    public Integer deleteList(List<String> idList) {
        return committeeMemberDao.deleteList(idList);
    }

    @Override
    public Boolean update(CommitteeMember committeeMember) {
        String idCard = committeeMember.getIdCard();
        if(idCard!=null && !idCard.trim().equals(""))
        {
            List<CommitteeMember> committeeMemberCheck =  committeeMemberDao.getCommitteeMemebersByIdCard(idCard,CommonConst.ACTION_EDIT,committeeMember.getId());
            if(committeeMemberCheck.size()>0)
            {
                throw new RuntimeException(ResultMessage.DUPLICATE_IDCARD);
            }
        }

        String address = committeeMember.getAddress().trim();
        //地址不为空，获取经纬度
        if(!"".equals(address)){
            Map<String,String> lngLat = LatitudeUtils.getGeocoderLatitude(address);
            if(lngLat!=null){
                String lng = lngLat.get("lng");
                String lat = lngLat.get("lat");
                committeeMember.setLng(lng);
                committeeMember.setLat(lat);
            }
            else{
                throw new RuntimeException(ResultMessage.NO_LNG_LAT);
            }
        }
        else
        {
            throw new RuntimeException(ResultMessage.EMPTY_ADDRESS);
        }

        return committeeMemberDao.update(committeeMember);
    }

    @Override
    public List<CommitteeMember> getAllCommitteeMembers() {
        return committeeMemberDao.getAllCommitteeMembers();
    }

    @Override
    public List<CommitteeMember> getCommitteeMembersByName(String name) {
        return committeeMemberDao.getCommitteeMembersByName(name);
    }

    @Override
    public CommitteeMember getCommitteeMemberById(Long id) {
        return committeeMemberDao.getCommitteeMemberById(id);
    }

    @Override
    public List<CommitteeMemberVo> getAllCommitteeMemberVos() {
        return committeeMemberDao.getAllCommitteeMemberVos();
    }

    @Override
    public List<CommitteeMemberVo> getCommitteeMemberVosByName(String name) {
        return committeeMemberDao.getCommitteeMemberVosByName(name);
    }

    @Override
    public CommitteeMemberVo getCommitteeMemberVoById(Long id) {
        return committeeMemberDao.getCommitteeMemberVoById(id);
    }

    @Override
    public List<CommitteeMember> getCommitteeMemebersByIdCard(String idCard,String action,Long id)
    {
        return committeeMemberDao.getCommitteeMemebersByIdCard(idCard,action,id);
    }
}
