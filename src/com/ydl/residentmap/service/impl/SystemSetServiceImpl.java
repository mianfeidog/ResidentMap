package com.ydl.residentmap.service.impl;

import com.ydl.residentmap.dao.*;
import com.ydl.residentmap.model.*;
import com.ydl.residentmap.service.SystemSetService;
import com.ydl.residentmap.util.LatitudeUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SystemSetServiceImpl implements SystemSetService {

    @Resource
    private AssistResidentDao assistResidentDao ;
    @Resource
    private BlockDao blockDao ;
    @Resource
    private CadreDao cadreDao ;
    @Resource
    private CommitteeMemberDao committeeMemberDao ;
    @Resource
    private DelegateCommitteeDao delegateCommitteeDao ;
    @Resource(name="gridManagerDao")
    private GridManagerDao gridManagerDao ;
    @Resource
    private KeyPersonDao keyPersonDao ;
    @Resource
    private PartyMemberDao partyMemberDao ;


    @Override
    public List<String> setLngLat() {
        List<String> emptyLngLat = new ArrayList<String>();
        String lng="";
        String lat="";
        String address="";
        String name = "";
        //同步帮扶人员经纬度
        List<AssistResident> assistResidentList = assistResidentDao.getAllAssistResidents();
        for (int i = 0; i < assistResidentList.size(); i++)
        {
            AssistResident assistResident=assistResidentList.get(i);
            lng=assistResident.getLng();
            lat=assistResident.getLat();
            address=assistResident.getAddress();
            if(lng==null || "".equals(lng.trim()) || lat==null || "".equals(lat.trim()))
            {
                Map<String,String> lngLat = LatitudeUtils.getGeocoderLatitude(address);
                if(lngLat!=null)
                {
                    lng = lngLat.get("lng");
                    lat = lngLat.get("lat");
                    assistResident.setLng(lng);
                    assistResident.setLat(lat);
                    assistResidentDao.update(assistResident);
                }
                else
                {
                    name="帮扶人员/"+assistResident.getName();
                    emptyLngLat.add(name);
                }
            }
        }

        //同步小区经纬度
        List<Block> blockList = blockDao.getAllBlocks();
        for (int i = 0; i < blockList.size(); i++)
        {
            Block block=blockList.get(i);
            lng=block.getLng();
            lat=block.getLat();
            address=block.getAddress();
            if(lng==null || "".equals(lng.trim()) || lat==null || "".equals(lat.trim()))
            {
                Map<String,String> lngLat = LatitudeUtils.getGeocoderLatitude(address);
                if(lngLat!=null)
                {
                    lng = lngLat.get("lng");
                    lat = lngLat.get("lat");
                    block.setLng(lng);
                    block.setLat(lat);
                    blockDao.update(block);
                }
                else
                {
                    name="小区/"+block.getName();
                    emptyLngLat.add(name);
                }
            }
        }

        //同步社区干部经纬度
        List<Cadre> cadreList = cadreDao.getAllCadres();
        for (int i = 0; i < cadreList.size(); i++)
        {
            Cadre cadre=cadreList.get(i);
            lng=cadre.getLng();
            lat=cadre.getLat();
            address=cadre.getAddress();
            if(lng==null || "".equals(lng.trim()) || lat==null || "".equals(lat.trim()))
            {
                Map<String,String> lngLat = LatitudeUtils.getGeocoderLatitude(address);
                if(lngLat!=null)
                {
                    lng = lngLat.get("lng");
                    lat = lngLat.get("lat");
                    cadre.setLng(lng);
                    cadre.setLat(lat);
                    cadreDao.update(cadre);
                }
                else
                {
                    name="社区干部/"+cadre.getName();
                    emptyLngLat.add(name);
                }
            }
        }

        //同步大党委成员经纬度
        List<CommitteeMember> committeeMemberList = committeeMemberDao.getAllCommitteeMembers();
        for (int i = 0; i < committeeMemberList.size(); i++)
        {
            CommitteeMember committeeMember=committeeMemberList.get(i);
            lng=committeeMember.getLng();
            lat=committeeMember.getLat();
            address=committeeMember.getAddress();
            if(lng==null || "".equals(lng.trim()) || lat==null || "".equals(lat.trim()))
            {
                Map<String,String> lngLat = LatitudeUtils.getGeocoderLatitude(address);
                if(lngLat!=null)
                {
                    lng = lngLat.get("lng");
                    lat = lngLat.get("lat");
                    committeeMember.setLng(lng);
                    committeeMember.setLat(lat);
                    committeeMemberDao.update(committeeMember);
                }
                else
                {
                    name="大党委成员/"+committeeMember.getName();
                    emptyLngLat.add(name);
                }
            }
        }

        //同步两代表一委员经纬度
        List<DelegateCommittee> delegateCommitteeList = delegateCommitteeDao.getAllDelegateCommittees();
        for (int i = 0; i < delegateCommitteeList.size(); i++)
        {
            DelegateCommittee delegateCommittee=delegateCommitteeList.get(i);
            lng=delegateCommittee.getLng();
            lat=delegateCommittee.getLat();
            address=delegateCommittee.getAddress();
            if(lng==null || "".equals(lng.trim()) || lat==null || "".equals(lat.trim()))
            {
                Map<String,String> lngLat = LatitudeUtils.getGeocoderLatitude(address);
                if(lngLat!=null)
                {
                    lng = lngLat.get("lng");
                    lat = lngLat.get("lat");
                    delegateCommittee.setLng(lng);
                    delegateCommittee.setLat(lat);
                    delegateCommitteeDao.update(delegateCommittee);
                }
                else
                {
                    name="两代表一委员/"+delegateCommittee.getName();
                    emptyLngLat.add(name);
                }
            }
        }

        //同步网格化管理人员
        List<GridManager> gridManagerList = gridManagerDao.getAllGridManagers();
        for (int i = 0; i < gridManagerList.size(); i++)
        {
            GridManager gridManager=gridManagerList.get(i);
            lng=gridManager.getLng();
            lat=gridManager.getLat();
            address=gridManager.getAddress();
            if(lng==null || "".equals(lng.trim()) || lat==null || "".equals(lat.trim()))
            {
                Map<String,String> lngLat = LatitudeUtils.getGeocoderLatitude(address);
                if(lngLat!=null)
                {
                    lng = lngLat.get("lng");
                    lat = lngLat.get("lat");
                    gridManager.setLng(lng);
                    gridManager.setLat(lat);
                    gridManagerDao.update(gridManager);
                }
                else
                {
                    name="网格化管理人员/"+gridManager.getName();
                    emptyLngLat.add(name);
                }
            }
        }

        //同步重点人员经纬度
        List<KeyPerson> keyPersonList = keyPersonDao.getAllKeyPersons();
        for (int i = 0; i < keyPersonList.size(); i++)
        {
            KeyPerson keyPerson=keyPersonList.get(i);
            lng=keyPerson.getLng();
            lat=keyPerson.getLat();
            address=keyPerson.getAddress();
            if(lng==null || "".equals(lng.trim()) || lat==null || "".equals(lat.trim()))
            {
                Map<String,String> lngLat = LatitudeUtils.getGeocoderLatitude(address);
                if(lngLat!=null)
                {
                    lng = lngLat.get("lng");
                    lat = lngLat.get("lat");
                    keyPerson.setLng(lng);
                    keyPerson.setLat(lat);
                    keyPersonDao.update(keyPerson);
                }
                else
                {
                    name="重点人员/"+keyPerson.getName();
                    emptyLngLat.add(name);
                }
            }
        }

        //同步党员经纬度
        List<PartyMember> partyMemberList = partyMemberDao.getAllPartyMembers();
        for (int i = 0; i < partyMemberList.size(); i++)
        {
            PartyMember partyMember=partyMemberList.get(i);
            lng=partyMember.getLng();
            lat=partyMember.getLat();
            address=partyMember.getAddress();
            if(lng==null || "".equals(lng.trim()) || lat==null || "".equals(lat.trim()))
            {
                Map<String,String> lngLat = LatitudeUtils.getGeocoderLatitude(address);
                if(lngLat!=null)
                {
                    lng = lngLat.get("lng");
                    lat = lngLat.get("lat");
                    partyMember.setLng(lng);
                    partyMember.setLat(lat);
                    partyMemberDao.update(partyMember);
                }
                else
                {
                    name="党员/"+partyMember.getName();
                    emptyLngLat.add(name);
                }
            }
        }

        return emptyLngLat;
    }
}
