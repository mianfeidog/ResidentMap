package com.ydl.residentmap.dao.impl;

import com.ydl.residentmap.dao.BaseDao;
import com.ydl.residentmap.dao.BlockDao;
import com.ydl.residentmap.model.Block;
import com.ydl.residentmap.model.Pager;
import com.ydl.residentmap.model.vo.BlockVo;
import com.ydl.residentmap.util.IdWorker;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@Repository
public class BlockDaoImpl implements BlockDao {
    @Resource
    private BaseDao<Block> baseDAO;

    @Resource
    private BaseDao<BlockVo> baseVoDAO;

    private String commonSql="select t1.map_range mapRange, t1.id,t1.name,t1.name,t1.lng,t1.lat,t1.address,t1.community_id communityId," +
            " t1.secretary_name secretaryName,t1.director_name directorName,t1.in_charge_leader_name inChargeLeaderName, "+
            " t1.work_day_leader_name workDayLeaderName,t1.secretary_tel secretaryTel,t1.director_tel directorTel,t1.in_charge_leader_tel inChargeLeaderTel, " +
            " t1.work_day_leader_tel workDayLeaderTel,t1.person_in_charge personInCharge,t1.person_in_charge_tel personInChargeTel,ifnull(t2.name,'') communityName, " +
            " ifnull(t3.name,'') streetName " +
            " from block t1 " +
            " left join community t2 on t1.community_id=t2.id " +
            " left join street t3 on t2.street_id=t3.id " ;


    @Override
    public Boolean save(Block block) {
        Boolean flag = true;
        try {
            Random random = new Random();
            block.setId(new IdWorker((long)random.nextInt(15)).nextId());
            baseDAO.save(block);
            System.out.println("添加小区 OK   小区ID："+block.getId());
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
            String hql="delete Block as r where r.id in ("+String.join(",", idList)+")";
            cnt=baseDAO.executeHql(hql);
        } catch (Exception e) {
            e.printStackTrace();
            cnt = 0;
        }
        return cnt;
    }

    @Override
    public Boolean update(Block block) {
        Boolean flag = true;
        try {
            baseDAO.update(block);
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    @Override
    public Block getBlockById(Long id) {
        return baseDAO.get(Block.class, id);
    }

    @Override
    public List<Block> getAllBlocks() {
        String hql="from Block";
        return baseDAO.find(hql);
    }

    @Override
    public List<Block> getBlocksByName(String name) {
        String hql="from Block where name like ?";
        Object[] params = new Object[1];
        params[0] = "%"+name+"%";
        List<Block> blocks = baseDAO.find(hql, params);
        return blocks;
    }

    @Override
    public BlockVo getBlockVoById(Long id) {
        BlockVo blockVo = new BlockVo();
        String hql=this.commonSql + " where t1.id="+id;
        List<BlockVo> blockVos = baseVoDAO.getResultBySQL(hql,new Object[]{},BlockVo.class);
        if(blockVos.size()>0) {
            blockVo=blockVos.get(0);
        }
        return blockVo;
    }

    @Override
    public List<BlockVo> getAllBlockVos() {
        String hql=this.commonSql;
        List<BlockVo> blockVos = baseVoDAO.getResultBySQL(hql,new Object[]{},BlockVo.class);
        return blockVos;
    }

    @Override
    public List<BlockVo> getBlockVosByName(String name) {
        String hql=this.commonSql + " where t1.name like ?";
        Object[] params = new Object[1];
        params[0] = "%"+name+"%";
        List<BlockVo> blockVos = baseVoDAO.getResultBySQL(hql,params,BlockVo.class);
        return blockVos;
    }

    @Override
    public List<BlockVo> getBlockVosByCommunityId(Long communityId) {
        String hql=this.commonSql + " where t1.community_id ="+communityId;
        Object[] params = new Object[0];
        List<BlockVo> blockVos = baseVoDAO.getResultBySQL(hql,params,BlockVo.class);
        return blockVos;
    }

    @Override
    public Pager<Block> getBlocksByPage(int offset, int size) {
        //查询共多少数据
        String totalSql = "select count(*) from Block";
        Long total = baseDAO.count(totalSql);
        String dateHql = "from Block";
        List<Block> blockList = baseDAO.find(dateHql, new Object[]{}, offset, size);
        Pager<Block> pager = new Pager<Block>();
        pager.setOffset(offset);
        pager.setSize(size);
        pager.setTotal(total);
        pager.setData(blockList);
        return pager;
    }
}
