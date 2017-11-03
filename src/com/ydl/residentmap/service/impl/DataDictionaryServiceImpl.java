package com.ydl.residentmap.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.ydl.residentmap.constants.CommonConst;
import com.ydl.residentmap.constants.DataDictionaryCode;
import com.ydl.residentmap.dao.*;
import com.ydl.residentmap.model.*;
import org.springframework.stereotype.Service;

import com.ydl.residentmap.service.DataDictionaryService;

@Service
public class DataDictionaryServiceImpl implements DataDictionaryService {
	
	@Resource
	private DataDictionaryDao dataDictionaryDao;

	@Resource
	private KeyPersonDao keyPersonDao ;

	@Resource
	private CommunityDao communityDao ;

	@Resource(name="gridManagerDao")
	private GridManagerDao gridManagerDao ;

	@Resource
	private PartyMemberDao partyMemberDao ;

	@Resource
	private DelegateCommitteeDao delegateCommitteeDao ;

	@Override
	public List<DataDictionary> getByType(int dataType) {
		return dataDictionaryDao.getByType(dataType);
	}


	@Override
	public Boolean save(DataDictionary dataDictionary) {
		return dataDictionaryDao.save(dataDictionary);
	}

	@Override
	public Boolean update(DataDictionary dataDictionary) {
		return dataDictionaryDao.update(dataDictionary);
	}

	@Override
	public Boolean delete(Long id) {
		return dataDictionaryDao.delete(id);
	}

	@Override
	public List<DataDictionary> getAll() {
		return dataDictionaryDao.getAll();
	}

	@Override
	public Integer deleteList(List<String> idList) {
		for(int i=0;i<idList.size();i++)
		{
			Long id = Long.parseLong(idList.get(i));
			DataDictionary dic = dataDictionaryDao.getById(id);
			int dataType=dic.getDataType();
			String name = dic.getName();
			//重点人员
			if(dataType==DataDictionaryCode.DATA_TYPE_KEY_PERSON )
			{
				List<KeyPerson> keyPersonList= keyPersonDao.getKeyPersonsByType(dataType);
				if(keyPersonList.size()>0)
				{
					throw new RuntimeException(name+"已被重点人员引用，无法删除");
				}
			}
			//社区类型
			else if(dataType==DataDictionaryCode.DATA_TYPE_COMMUNITY)
			{
				List<Community> communityList = communityDao.getCommunitiesByType(dataType);
				if(communityList.size()>0)
				{
					throw new RuntimeException(name+"已被社区引用，无法删除");
				}
			}
			//网格角色
			else if(dataType==DataDictionaryCode.DATA_TYPE_GRID_ROLE)
			{
				List<GridManager> gridManagerList =gridManagerDao.getKeyGridManagersByGridRole(dataType);
				if(gridManagerList.size()>0)
				{
					throw new RuntimeException(name+"已被三长三员网格化管理人员引用，无法删除");
				}
			}
			//民族
			else if(dataType==DataDictionaryCode.DATA_TYPE_MINORITY)
			{
				List<DelegateCommittee> delegateCommitteeList =delegateCommitteeDao.getDelegateCommitteesByMinority(dataType);
				if(delegateCommitteeList.size()>0)
				{
					throw new RuntimeException(name+"已被两代表一委员引用，无法删除");
				}

				List<GridManager> gridManagerList =gridManagerDao.getKeyGridManagersByMinority(dataType);
				if(gridManagerList.size()>0)
				{
					throw new RuntimeException(name+"已被三长三员网格化管理人员引用，无法删除");
				}

				List<PartyMember> partyMemberList = partyMemberDao.getPartyMembersByMinority(dataType);
				if(partyMemberList.size()>0)
				{
					throw new RuntimeException(name+"已被党员引用，无法删除");
				}
			}
			//文化程度
			else if(dataType==DataDictionaryCode.DATA_TYPE_EDUCATION)
			{

			}
		}

		return dataDictionaryDao.deleteList(idList);
	}

	@Override
	public Integer getNextValue(Integer dataType) {
		List<DataDictionary> dataDictionaryList= dataDictionaryDao.getByType(dataType);
		return dataDictionaryList.size()+1;
	}
}
