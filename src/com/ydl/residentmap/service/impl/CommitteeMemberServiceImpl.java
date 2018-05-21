package com.ydl.residentmap.service.impl;

import com.ydl.residentmap.constants.CommonConst;
import com.ydl.residentmap.constants.ResultMessage;
import com.ydl.residentmap.dao.CommitteeMemberDao;
import com.ydl.residentmap.model.CommitteeMember;
import com.ydl.residentmap.model.vo.CommitteeMemberVo;
import com.ydl.residentmap.service.CommitteeMemberService;
import com.ydl.residentmap.util.CommonUtil;
import com.ydl.residentmap.util.IdWorker;
import com.ydl.residentmap.util.LatitudeUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CommitteeMemberServiceImpl implements CommitteeMemberService {
    @Resource
    private CommitteeMemberDao committeeMemberDao ;

    @Override
    public Boolean save(CommitteeMember committeeMember) {
        Long communityId = committeeMember.getCommunityId();
        if(communityId==null)
        {
            throw new RuntimeException(ResultMessage.EMPTY_COMMUNITY_ID);
        }

        String idCard = committeeMember.getIdCard();
        if(idCard!=null && !idCard.trim().equals(""))
        {
            List<CommitteeMember> committeeMemberCheck =  committeeMemberDao.getCommitteeMemebersByIdCard(idCard, CommonConst.ACTION_ADD,committeeMember.getId());
            if(committeeMemberCheck.size()>0)
            {
                throw new RuntimeException(ResultMessage.DUPLICATE_IDCARD);
            }
        }
        else
        {
            throw new RuntimeException(ResultMessage.EMPTY_ID_CARD);
        }

//        String address = committeeMember.getAddress().trim();
//        //地址不为空，获取经纬度
//        if(!"".equals(address)){
//            Map<String,String> lngLat = LatitudeUtils.getGeocoderLatitude(address);
//            if(lngLat!=null){
//                String lng = lngLat.get("lng");
//                String lat = lngLat.get("lat");
//                committeeMember.setLng(lng);
//                committeeMember.setLat(lat);
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
        committeeMember.setCreateAt(dateLong);

        Random random = new Random();
        committeeMember.setId(new IdWorker((long) random.nextInt(15)).nextId());

        return committeeMemberDao.save(committeeMember);
    }

    @Override
    public Integer deleteList(List<String> idList) {
        return committeeMemberDao.deleteList(idList);
    }

    @Override
    public Boolean update(CommitteeMember committeeMember) {
        Long communityId = committeeMember.getCommunityId();
        if(communityId==null)
        {
            throw new RuntimeException(ResultMessage.EMPTY_COMMUNITY_ID);
        }

        String idCard = committeeMember.getIdCard();
        if(idCard!=null && !idCard.trim().equals(""))
        {
            List<CommitteeMember> committeeMemberCheck =  committeeMemberDao.getCommitteeMemebersByIdCard(idCard,CommonConst.ACTION_EDIT,committeeMember.getId());
            if(committeeMemberCheck.size()>0)
            {
                throw new RuntimeException(ResultMessage.DUPLICATE_IDCARD);
            }
        }
        else
        {
            throw new RuntimeException(ResultMessage.EMPTY_ID_CARD);
        }

//        String address = committeeMember.getAddress().trim();
//        //地址不为空，获取经纬度
//        if(!"".equals(address)){
//            Map<String,String> lngLat = LatitudeUtils.getGeocoderLatitude(address);
//            if(lngLat!=null){
//                String lng = lngLat.get("lng");
//                String lat = lngLat.get("lat");
//                committeeMember.setLng(lng);
//                committeeMember.setLat(lat);
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
        committeeMember.setCreateAt(dateLong);

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
    public List<CommitteeMemberVo> getCommitteeMemberVosByCondition(HashMap<String,String> map) {
        return committeeMemberDao.getCommitteeMemberVosByCondition(map);
    }

    @Override
    public HSSFWorkbook exportExcel(List<CommitteeMemberVo> committeeMemberVos) {
        //设置表格标题行
        String[] headers = new String[] {"职务","姓名", "性别","民族","出生年月", "文化程度", "入党时间","身份证号","家庭住址","联系方式","所属社区"};
        List<List<String>> dataList = new ArrayList<List<String>>();
        for(int i=0;i<committeeMemberVos.size();i++) {
            CommitteeMemberVo committeeMemberVo = committeeMemberVos.get(i);
            List<String> list = new ArrayList<String>();

            list.add(committeeMemberVo.getPositionName());
            list.add(committeeMemberVo.getName());
            list.add(committeeMemberVo.getGender()==1?"男":"女");
            list.add(committeeMemberVo.getMinorityName());
            list.add(committeeMemberVo.getBirthday().toString());

            list.add(committeeMemberVo.getEducationName());
            list.add(committeeMemberVo.getJoinDate().toString());
            list.add(committeeMemberVo.getIdCard());
            list.add(committeeMemberVo.getAddress());
            list.add(committeeMemberVo.getLink());

            list.add(committeeMemberVo.getCommunityName());

            dataList.add(list);
        }

        HSSFWorkbook workbook = CommonUtil.setExcel(headers,dataList);
        return workbook;
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
