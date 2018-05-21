package com.ydl.residentmap.service.impl;

import com.ydl.residentmap.constants.ResultMessage;
import com.ydl.residentmap.dao.PartyOrgDao;
import com.ydl.residentmap.model.PartyOrg;
import com.ydl.residentmap.model.vo.PartyOrgVo;
import com.ydl.residentmap.service.PartyOrgService;
import com.ydl.residentmap.util.CommonUtil;
import com.ydl.residentmap.util.IdWorker;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PartyOrgServiceImpl implements PartyOrgService {

    @Resource
    private PartyOrgDao partyOrgDao ;

    @Override
    public Boolean save(PartyOrg partyOrg) {
        Long communityId = partyOrg.getCommunityId();
        if(communityId==null)
        {
            throw new RuntimeException(ResultMessage.EMPTY_COMMUNITY_ID);
        }

        Random random = new Random();
        partyOrg.setId(new IdWorker((long)random.nextInt(15)).nextId());

        //创建时间
        Date now = new Date();
        String sdate=(new SimpleDateFormat("yyyyMMddHHmm")).format(now);
        Long dateLong = Long.parseLong(sdate);
        partyOrg.setCreateAt(dateLong);
        return partyOrgDao.save(partyOrg);
    }

    @Override
    public Integer deleteList(List<String> idList) {
        return partyOrgDao.deleteList(idList);
    }

    @Override
    public Boolean update(PartyOrg partyOrg) {
        Long communityId = partyOrg.getCommunityId();
        if(communityId==null)
        {
            throw new RuntimeException(ResultMessage.EMPTY_COMMUNITY_ID);
        }

        //创建时间
        Date now = new Date();
        String sdate=(new SimpleDateFormat("yyyyMMddHHmm")).format(now);
        Long dateLong = Long.parseLong(sdate);
        partyOrg.setCreateAt(dateLong);

        return partyOrgDao.update(partyOrg);
    }

    @Override
    public List<PartyOrg> getAllPartyOrgs() {
        return partyOrgDao.getAllPartyOrgs();
    }

    @Override
    public List<PartyOrg> getPartyOrgsByName(String name) {
        return partyOrgDao.getPartyOrgsByName(name);
    }

    @Override
    public PartyOrg getPartyOrgById(Long id) {
        return partyOrgDao.getPartyOrgById(id);
    }

    @Override
    public List<PartyOrgVo> getAllPartyOrgVos() {
        return partyOrgDao.getAllPartyOrgVos();
    }

    @Override
    public List<PartyOrgVo> getPartyOrgVosByName(String name) {
        return partyOrgDao.getPartyOrgVosByName(name);
    }

    @Override
    public List<PartyOrgVo> getPartyOrgVosByCondition(HashMap<String,String> map) {
        return partyOrgDao.getPartyOrgVosByCondition(map);
    }

    @Override
    public HSSFWorkbook exportExcel(List<PartyOrgVo> partyOrgVos) {
        //设置表格标题行
        String[] headers = new String[] {"党组织名称","组织建制", "党组织属性","上级党组织","书记姓名", "党员人数", "详细地址","联系电话","所属社区"};
        List<List<String>> dataList = new ArrayList<List<String>>();
        for(int i=0;i<partyOrgVos.size();i++) {
            PartyOrgVo  partyOrgVo = partyOrgVos.get(i);
            List<String> list = new ArrayList<String>();

            list.add(partyOrgVo.getName());
            list.add(partyOrgVo.getOrgSystemName());
            list.add(partyOrgVo.getOrgAttributeName());
            list.add(partyOrgVo.getParPartyName());
            list.add(partyOrgVo.getSecretaryName());

            list.add(partyOrgVo.getMemberCnt().toString());
            list.add(partyOrgVo.getAddress());
            list.add(partyOrgVo.getTelephone());
            list.add(partyOrgVo.getCommunityName());

            dataList.add(list);
        }

        HSSFWorkbook workbook = CommonUtil.setExcel(headers,dataList);
        return workbook;
    }

    @Override
    public PartyOrgVo getPartyOrgVoById(Long id) {
        return partyOrgDao.getPartyOrgVoById(id);
    }
}
