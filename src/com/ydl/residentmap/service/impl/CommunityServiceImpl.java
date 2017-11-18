package com.ydl.residentmap.service.impl;

import com.ydl.residentmap.constants.ResultMessage;
import com.ydl.residentmap.dao.*;
import com.ydl.residentmap.model.*;
import com.ydl.residentmap.model.vo.BlockVo;
import com.ydl.residentmap.model.vo.CommunityVo;
import com.ydl.residentmap.service.CommunityService;
import com.ydl.residentmap.util.IdWorker;
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
    public List<BlockVo> getBlockVosByCommunityId(Long communityId) {
        return blockDao.getBlockVosByCommunityId(communityId);
    }

    @Override
    public Pager<Community> getCommunitiesByPage(int offset, int size) {
        return communityDao.getCommunitiesByPage(offset,size);
    }
}
