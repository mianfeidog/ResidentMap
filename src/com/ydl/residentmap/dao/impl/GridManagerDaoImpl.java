package com.ydl.residentmap.dao.impl;

import com.ydl.residentmap.dao.BaseDao;
import com.ydl.residentmap.dao.GridManagerDao;
import com.ydl.residentmap.model.GridManager;
import com.ydl.residentmap.model.vo.GridManagerVo;
import com.ydl.residentmap.util.IdWorker;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Repository("gridManagerDao")
public class GridManagerDaoImpl implements GridManagerDao{

    @Resource
    private BaseDao<GridManager> baseDAO;

    @Resource
    private BaseDao<GridManagerVo> baseVoDAO;

    private String commonSql="SELECT ifnull(t2.name,'') gridRoleName,IFNULL(t3.name,'') minorityName, " +
        " IFNULL(t4.name,'') educationName,IFNULL(t5.name,'') communityName, " +
        " t1.id,t1.grid_num gridNum,t1.grid_name gridName,t1.grid_role gridRole,t1.name, " +
        " t1.gender,t1.minority,t1.birthday,t1.education,t1.party_member partyMember, " +
        " t1.address,t1.link,t1.community_id communityId,t1.create_at createAt " +
        " from grid_manager t1 " +
        " left join data_dictionary t2 on t1.grid_role=t2.value and t2.data_type=4 " +
        " left join data_dictionary t3 on t1.minority=t3.value and t3.data_type=5 " +
        " left join data_dictionary t4 on t1.education=t4.value and t4.data_type=6 " +
        " left join community t5 on t1.community_id=t5.id ";

    @Override
    public Boolean save(GridManager gridManager) {
        Boolean flag = true;
        try {
            //创建时间
            Date now = new Date();
            String sdate=(new SimpleDateFormat("yyyyMMddHHmm")).format(now);
            Long dateLong = Long.parseLong(sdate);
            gridManager.setCreateAt(dateLong);

            Random random = new Random();
            gridManager.setId(new IdWorker((long)random.nextInt(15)).nextId());
            baseDAO.save(gridManager);
            System.out.println("添加网格化管理人员 OK   重点人员ID："+gridManager.getId());
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    @Override
    public Integer deleteList(List<String> idList) {
        Integer cnt = 0;
        try {
            String hql="delete GridManager as r where r.id in ("+String.join(",", idList)+")";
            cnt=baseDAO.executeHql(hql);
        } catch (Exception e) {
            e.printStackTrace();
            cnt = 0;
        }
        return cnt;
    }

    @Override
    public Boolean update(GridManager gridManager) {
        Boolean flag = true;
        try {
            baseDAO.update(gridManager);
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    @Override
    public List<GridManager> getAllGridManagers() {
        String hql="from GridManager";
        return baseDAO.find(hql);
    }

    @Override
    public List<GridManagerVo> getAllGridManagerVos() {
        String hql=this.commonSql + " order by t1.create_at desc ";
        List<GridManagerVo> gridManagerVos = baseVoDAO.getResultBySQL(hql,new Object[]{},GridManagerVo.class);
        return gridManagerVos;
    }

    @Override
    public List<GridManager> getKeyGridManagersByName(String name) {
        String hql="from GridManager where name like ?";
        Object[] params = new Object[1];
        params[0] = "%"+name+"%";
        List<GridManager> gridManagers = baseDAO.find(hql, params);
        return gridManagers;
    }

    @Override
    public List<GridManagerVo> getKeyGridManagerVosByName(String name) {
        String hql=this.commonSql +" where t1.name like ? order by t1.create_at desc ";
        Object[] params = new Object[1];
        params[0] = "%"+name+"%";
        List<GridManagerVo> gridManagerVos = baseVoDAO.getResultBySQL(hql,params,GridManagerVo.class);
        return gridManagerVos;
    }

    @Override
    public GridManager getGridManagerById(Long id) {
        return baseDAO.get(GridManager.class, id);
    }

    @Override
    public GridManagerVo getGridManagerVoById(Long id) {
        String hql=this.commonSql + " where t1.id="+id;
        List<GridManagerVo> gridManagerVos = baseVoDAO.getResultBySQL(hql,new Object[]{},GridManagerVo.class);
        GridManagerVo gridManagerVo = new GridManagerVo();
        if(gridManagerVos.size()>0){
            gridManagerVo=gridManagerVos.get(0);
        }
        return gridManagerVo;
    }


}
