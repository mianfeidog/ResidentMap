package com.ydl.residentmap.dao.impl;

import com.ydl.residentmap.dao.BaseDao;
import com.ydl.residentmap.dao.StreetDao;
import com.ydl.residentmap.model.Pager;
import com.ydl.residentmap.model.Resident;
import com.ydl.residentmap.model.Street;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

import com.ydl.residentmap.util.IdWorker;

@Repository
public class StreetDaoImpl implements StreetDao {
    @Resource
    private BaseDao<Street> baseDAO;

    @Override
    public Boolean save(Street street) {
        Boolean flag = true;
        try {
            baseDAO.save(street);
            System.out.println("添加街道OK  街道ID："+street.getId());
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    @Override
    public List<Street> getAll() {
        String hql="from Street order by createAt desc";
        return baseDAO.find(hql);
    }

    @Override
    public List<Street> getStreetsByName(String name) {
        String hql = "from Street where name like ? order by createAt desc";
        Object[] params = new Object[1];
        params[0] = "%" + name + "%";
        List<Street> streets = baseDAO.find(hql, params);
        return streets;
    }

    @Override
    public List<Street> getStreetsByCondition(HashMap<String,String> map) {
        String addSql = "";
        List<String> paramList = new ArrayList<String>();

        //名称模糊
        if(map.containsKey("nameLike"))
        {
            addSql+=" and name like ? ";
            String val = map.get("nameLike");
            paramList.add("%"+val+"%");
        }
        //后勤模糊
        if(map.containsKey("serviceLike"))
        {
            addSql+=" and service like ? ";
            String val = map.get("serviceLike");
            paramList.add("%"+val+"%");
        }
        //治安模糊
        if(map.containsKey("securityLike"))
        {
            addSql+=" and security like ? ";
            String val = map.get("securityLike");
            paramList.add("%"+val+"%");
        }
        //社保模糊
        if(map.containsKey("socialSecurityLike"))
        {
            addSql+=" and socialSecurity like ? ";
            String val = map.get("socialSecurityLike");
            paramList.add("%"+val+"%");
        }
        //社区建设模糊
        if(map.containsKey("communityBuildLike"))
        {
            addSql+=" and communityBuild like ? ";
            String val = map.get("communityBuildLike");
            paramList.add("%"+val+"%");
        }
        //街道建设模糊
        if(map.containsKey("streetBuildLike"))
        {
            addSql+=" and streetBuild like ? ";
            String val = map.get("streetBuildLike");
            paramList.add("%"+val+"%");
        }

        String hql = "from Street where 1=1 "+addSql+" order by createAt desc";
        Object[] params = paramList.toArray();
        List<Street> streets = baseDAO.find(hql, params);
        return streets;
    }

    @Override
    public Street getStreetById(Long id) {
        return baseDAO.get(Street.class, id);
    }

    @Override
    public Integer deleteList(List<String> idList) {
        Integer cnt = 0;
        try {
            String hql="delete Street as r where r.id in ("+String.join(",", idList)+")";
            cnt=baseDAO.executeHql(hql);
        } catch (Exception e) {
            e.printStackTrace();
            cnt = 0;
        }
        return cnt;
    }

    @Override
    public Boolean update(Street street) {
        Boolean flag = true;
        try {
            baseDAO.update(street);
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    @Override
    public Pager<Street> getStreetsByPage(int offset, int size) {
        //查询共多少数据
        String totalSql = "select count(*) from Street";
        Long total = baseDAO.count(totalSql);
        String dateHql = "from Street";
        List<Street> streetList = baseDAO.find(dateHql, new Object[]{}, offset, size);
        Pager<Street> pager = new Pager<Street>();
        pager.setOffset(offset);
        pager.setSize(size);
        pager.setTotal(total);
        pager.setData(streetList);
        return pager;
    }
}
