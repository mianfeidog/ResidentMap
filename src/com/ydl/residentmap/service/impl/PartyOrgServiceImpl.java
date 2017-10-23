package com.ydl.residentmap.service.impl;

import com.ydl.residentmap.dao.PartyOrgDao;
import com.ydl.residentmap.model.PartyOrg;
import com.ydl.residentmap.model.vo.PartyOrgVo;
import com.ydl.residentmap.service.PartyOrgService;
import com.ydl.residentmap.util.IdWorker;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class PartyOrgServiceImpl implements PartyOrgService {

    @Resource
    private PartyOrgDao partyOrgDao ;

    @Override
    public Boolean save(PartyOrg partyOrg) {

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
    public PartyOrgVo getPartyOrgVoById(Long id) {
        return partyOrgDao.getPartyOrgVoById(id);
    }
}
