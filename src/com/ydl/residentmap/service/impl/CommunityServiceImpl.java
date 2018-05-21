package com.ydl.residentmap.service.impl;

import com.ydl.residentmap.constants.ResultMessage;
import com.ydl.residentmap.dao.*;
import com.ydl.residentmap.model.*;
import com.ydl.residentmap.model.vo.BlockVo;
import com.ydl.residentmap.model.vo.CommunityVo;
import com.ydl.residentmap.service.CommunityService;
import com.ydl.residentmap.util.CommonUtil;
import com.ydl.residentmap.util.IdWorker;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CommunityServiceImpl implements CommunityService {
    @Resource
    private CommunityDao communityDao ;

    @Resource
    private BlockDao blockDao;

    @Resource
    private CadreDao cadreDao;

    @Resource
    private CommitteeMemberDao committeeMemberDao;

    @Resource
    private DelegateCommitteeDao delegateCommitteeDao;

    @Resource
    private GridManagerDao gridManagerDao;

    @Resource
    private PartyMemberDao partyMemberDao;

    @Resource
    private PartyOrgDao partyOrgDao;

    @Override
    public Boolean save(Community community) {
        Long streetId = community.getStreetId();
        if(streetId==null)
        {
            throw new RuntimeException(ResultMessage.EMPTY_STREET_ID);
        }

        //创建时间
        Date now = new Date();
        String sdate=(new SimpleDateFormat("yyyyMMddHHmm")).format(now);
        Long dateLong = Long.parseLong(sdate);
        community.setCreateAt(dateLong);

        Random random = new Random();
        community.setId(new IdWorker((long)random.nextInt(15)).nextId());

        return communityDao.save(community);
    }

    @Override
    public Integer deleteList(List<String> idList) {
        Set<String> blockNames = new HashSet<String>();
        Set<String> cadreNames = new HashSet<String>();
        Set<String> committeeMemberNames = new HashSet<String>();
        Set<String> delegateCommitteeNames = new HashSet<String>();
        Set<String> gridManagerNames = new HashSet<String>();
        Set<String> partyMembersNames = new HashSet<String>();
        Set<String> partyOrgNames = new HashSet<String>();

        for(int i=0;i<idList.size();i++)
        {

            String communityIdStr = idList.get(i);
            Long communityId=Long.parseLong(communityIdStr);
            //根据所属社区查找小区
            List<BlockVo> blockVoList = blockDao.getBlockVosByCommunityId(communityId);
            if (blockVoList.size() > 0) {
                for (int j = 0; j < blockVoList.size(); j++) {
                    blockNames.add(blockVoList.get(j).getName());
                }
            }
            if(blockNames.size()>0)
            {
                throw new RuntimeException("所删社区已被小区（"+String.join(",",blockNames)+"）引用，无法删除。");
            }

            //根据所属社区查找社区干部
            List<Cadre> cadreList=cadreDao.getCadresByCommunityId(communityId);
            if(cadreList.size()>0)
            {
                for (int j = 0; j < cadreList.size(); j++) {
                    cadreNames.add(cadreList.get(j).getName());
                }
            }
            if(cadreNames.size()>0)
            {
                throw new RuntimeException("所删社区已被社区干部（"+String.join(",",cadreNames)+"）引用，无法删除。");
            }

            //根据所属社区查找社区大党委成员
            List<CommitteeMember> committeeMemberList=committeeMemberDao.getCommitteeMembersByCommunityId(communityId);
            if(committeeMemberList.size()>0)
            {
                for (int j = 0; j < committeeMemberList.size(); j++) {
                    committeeMemberNames.add(committeeMemberList.get(j).getName());
                }
            }
            if(committeeMemberNames.size()>0)
            {
                throw new RuntimeException("所删社区已被大党委成员（"+String.join(",",committeeMemberNames)+"）引用，无法删除。");
            }

            //根据所属社区查找两代表一委员
            List<DelegateCommittee> delegateCommitteeList=delegateCommitteeDao.getDelegateCommitteesByCommunityId(communityId);
            if(delegateCommitteeList.size()>0)
            {
                for (int j = 0; j < delegateCommitteeList.size(); j++) {
                    delegateCommitteeNames.add(delegateCommitteeList.get(j).getName());
                }
            }
            if(delegateCommitteeNames.size()>0)
            {
                throw new RuntimeException("所删社区已被两代表一委员（"+String.join(",",delegateCommitteeNames)+"）引用，无法删除。");
            }

            //根据所属社区查找网格化管理人员
            List<GridManager> gridManagerList=gridManagerDao.getKeyGridManagersByCommunityId(communityId);
            if(gridManagerList.size()>0)
            {
                for (int j = 0; j < gridManagerList.size(); j++) {
                    gridManagerNames.add(gridManagerList.get(j).getName());
                }
            }
            if(gridManagerNames.size()>0)
            {
                throw new RuntimeException("所删社区已被网格化管理人员（"+String.join(",",gridManagerNames)+"）引用，无法删除。");
            }

            //根据所属社区查找党员
            List<PartyMember> partyMembers=partyMemberDao.getPartyMembersByCommunityId(communityId);
            if(partyMembers.size()>0)
            {
                for (int j = 0; j < partyMembers.size(); j++) {
                    partyMembersNames.add(partyMembers.get(j).getName());
                }
            }
            if(partyMembersNames.size()>0)
            {
                throw new RuntimeException("所删社区已被党员（"+String.join(",",partyMembersNames)+"）引用，无法删除。");
            }

            //根据所属社区查找党组织
            List<PartyOrg> partyOrgList=partyOrgDao.getPartyOrgsByCommunityId(communityId);
            if(partyOrgList.size()>0)
            {
                for (int j = 0; j < partyOrgList.size(); j++) {
                    partyOrgNames.add(partyOrgList.get(j).getName());
                }
            }
            if(partyOrgNames.size()>0)
            {
                throw new RuntimeException("所删社区已被党组织（"+String.join(",",partyOrgNames)+"）引用，无法删除。");
            }

        }

        return communityDao.deleteList(idList);
    }

    @Override
    public Boolean update(Community community) {
        Long streetId = community.getStreetId();
        if(streetId==null)
        {
            throw new RuntimeException(ResultMessage.EMPTY_STREET_ID);
        }

        //创建时间
        Date now = new Date();
        String sdate=(new SimpleDateFormat("yyyyMMddHHmm")).format(now);
        Long dateLong = Long.parseLong(sdate);
        community.setCreateAt(dateLong);

        return communityDao.update(community);
    }

    @Override
    public Community getKeyCommunityById(Long id) {
        return communityDao.getCommunityById(id);
    }

    @Override
    public List<Community> getAllCommunities() {
        return communityDao.getAllCommunities();
    }

    @Override
    public List<Community> getCommunitiesByName(String name) {
        return communityDao.getCommunitiesByName(name);
    }

    @Override
    public CommunityVo getCommunityVoById(Long id) {
        return communityDao.getCommunityVoById(id);
    }

    @Override
    public List<CommunityVo> getAllCommunityVos() {
        return communityDao.getAllCommunityVos();
    }

    @Override
    public List<CommunityVo> getCommunityVosByName(String name) {
        return communityDao.getCommunityVosByName(name);
    }

    @Override
    public List<CommunityVo> getCommunitiyVosByCondition(HashMap<String,String> map) {
        return communityDao.getCommunitiyVosByCondition(map);
    }

    @Override
    public HSSFWorkbook exportExcel(List<CommunityVo> communityVos) {
        //设置表格标题行
        String[] headers = new String[] {"社区名称","总占地面积", "小区(院落)数量","总户数",
                "总人口数", "社区类型", "书记姓名","主任姓名",
                "所属街道","社区党支部书记电话","社区居委会主任电话","街道包抓领导电话",
                "常住户数","暂住户数","流入人口数","党员总人数",
                "流出党员人数","残疾人数","居民医保参保人数","重点人员人数",
                "已参加水改户数","集中供暖户数","常住人口","暂住人口",
                "流出人口数","流入党员人数","育龄妇女","低保户数",
                "老龄人口","居民养老参保人数","路长/院长/所长人数","已参加电改户数",
                "天然气安装户数","摄像头数","安防设备数量","低收入家庭数量",
                "街道包抓领导名称","工作日领导名称","社区工作日领导电话"};
        List<List<String>> dataList = new ArrayList<List<String>>();
        for(int i=0;i<communityVos.size();i++) {
            CommunityVo communityVo = communityVos.get(i);
            List<String> list = new ArrayList<String>();

            list.add(communityVo.getName());
            list.add(communityVo.getArea().toString());
            list.add(communityVo.getBlockCount().toString());
            list.add(communityVo.getFamilyCount().toString());  //总户数

            list.add(communityVo.getPeopleCount().toString());  //总人口数
            list.add(communityVo.getTypeName());
            list.add(communityVo.getSecretaryName());   //书记姓名
            list.add(communityVo.getDirectorName());    //主任姓名

            list.add(communityVo.getStreetName());      //所属街道
            list.add(communityVo.getSecretaryName());   //书记电话
            list.add(communityVo.getDirectorTel());     //主任电话
            list.add(communityVo.getInChargeLeaderTel()); //街道包抓领导电话

            list.add(communityVo.getResidentFamilyCount().toString());      //常住户数
            list.add(communityVo.getTemporaryFamilyCount().toString());     //暂住户数
            list.add(communityVo.getIncomePeopleCount().toString());        //流入人口数
            list.add(communityVo.getPartyMemberCount().toString());         //党员总人数

            list.add(communityVo.getOutPartyMemberCount().toString());      //流出党员人数
            list.add(communityVo.getDeformityCount().toString());           //残疾人数
            list.add(communityVo.getMedicalInsuranceCount().toString());    //居民医保参保人数
            list.add(communityVo.getKeyPersonCount().toString());           //重点人员人数

            list.add(communityVo.getWaterReformFamilyCount().toString());   //已参加水改户数
            list.add(communityVo.getHeatingSupplyFamilyCount().toString()); //集中供暖户数
            list.add(communityVo.getResidentPeopleCount().toString());      //常住人口
            list.add(communityVo.getTemporaryPeopleCount().toString());     //暂住人口

            list.add(communityVo.getOutPeopleCount().toString());           //流出人口数
            list.add(communityVo.getIncomePartyMemberCount().toString());   //流入党员人数
            list.add(communityVo.getChildbearingCount().toString());        //育龄妇女
            list.add(communityVo.getAllowancesFamilyCount().toString());    //低保户数

            list.add(communityVo.getOutPeopleCount().toString());           //老龄人口
            list.add(communityVo.getOutPeopleCount().toString());           //居民养老参保人数
            list.add(communityVo.getGridManagerCount().toString());         //路长/院长/所长人数
            list.add(communityVo.getElectricityReformFamilyCount().toString());        //已参加电改户数

            list.add(communityVo.getGasFamilyCount().toString());           //天然气安装户数
            list.add(communityVo.getCameraCount().toString());              //摄像头数
            list.add(communityVo.getSecuritySystemCount().toString());      //安防设备数量
            list.add(communityVo.getLowIncomeFamilyCount().toString());     //低收入家庭数量

            list.add(communityVo.getInChargeLeaderName());                  //街道包抓领导名称
            list.add(communityVo.getWorkDayLeaderName());                   //工作日领导名称
            list.add(communityVo.getWorkDayLeaderTel());                    //社区工作日领导电话

            dataList.add(list);
        }
        HSSFWorkbook workbook = CommonUtil.setExcel(headers,dataList);
        return workbook;
    }

    @Override
    public List<BlockVo> getBlockVosByCommunityId(Long communityId) {
        return blockDao.getBlockVosByCommunityId(communityId);
    }

    @Override
    public Pager<Community> getCommunitiesByPage(int offset, int size) {
        return communityDao.getCommunitiesByPage(offset,size);
    }
}
