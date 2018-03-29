package com.ydl.residentmap.service.impl;

import com.ydl.residentmap.dao.CommunityDao;
import com.ydl.residentmap.dao.StreetDao;
import com.ydl.residentmap.model.Community;
import com.ydl.residentmap.model.Pager;
import com.ydl.residentmap.model.Street;
import com.ydl.residentmap.service.StreetService;
import com.ydl.residentmap.util.IdWorker;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class StreetServiceImpl implements StreetService{
    @Resource
    private StreetDao streetDao ;

    @Resource
    private CommunityDao communityDao ;

    @Override
    public Boolean save(Street street) {
        //创建时间
        Date now = new Date();
        String sdate=(new SimpleDateFormat("yyyyMMddHHmm")).format(now);
        Long dateLong = Long.parseLong(sdate);
        street.setCreateAt(dateLong);

        Random random = new Random();
        street.setId(new IdWorker((long)random.nextInt(15)).nextId());

        return streetDao.save(street);
    }

    @Override
    public List<Street> getAll() {
        return streetDao.getAll();
    }

    @Override
    public List<Street> getStreetsByName(String name) {
        return streetDao.getStreetsByName(name);
    }

    @Override
    public List<Street> getStreetsByCondition(HashMap<String,String> map) {
        return streetDao.getStreetsByCondition(map);
    }

    @Override
    public Street getStreetById(Long id) {
        return streetDao.getStreetById(id);
    }


    @Override
    public Integer deleteList(List<String> idList) {
        Set<String> communityNames = new HashSet<String>();
        for(int i=0;i<idList.size();i++)
        {
            //根据所属街道查找社区
            String streetId = idList.get(i);
            List<Community> communityList= communityDao.getCommunitiesByStreetId(Long.parseLong(streetId));
            if(communityList.size()>0)
            {
                for(int j=0;j<communityList.size();j++)
                {
                    communityNames.add(communityList.get(j).getName());
                }
            }
        }

        if(communityNames.size()>0)
        {
            throw new RuntimeException("所删街道已被社区（"+String.join(",",communityNames)+"）引用，无法删除。");
        }

        return streetDao.deleteList(idList);
    }

    @Override
    public Boolean update(Street street) {
        //创建时间
        Date now = new Date();
        String sdate=(new SimpleDateFormat("yyyyMMddHHmm")).format(now);
        Long dateLong = Long.parseLong(sdate);
        street.setCreateAt(dateLong);

        return streetDao.update(street);
    }

    @Override
    public Pager<Street> getStreetsByPage(int offset, int size) {
        return streetDao.getStreetsByPage(offset,size);
    }
}
