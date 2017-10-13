package com.ydl.residentmap.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ydl.residentmap.dao.AidResidentDao;
import com.ydl.residentmap.dao.BaseDao;
import com.ydl.residentmap.model.AidResident;

/**
 * 帮扶对象
 */
@Repository
public class AidResidentDaoImpl implements AidResidentDao {	
	@Resource
	private BaseDao<AidResident> baseDAO;

	@Override
	public Boolean save(AidResident aidResident) {
		Boolean rs = true;
		try{
			baseDAO.save(aidResident);
		}catch (Exception e){
			e.printStackTrace();
			rs = false;
		}

		return rs;
	}

	@Override
	public Boolean update(AidResident aidResident) {
		Boolean rs = true;
		try{
			baseDAO.update(aidResident);
		}catch (Exception e){
			e.printStackTrace();
			rs = false;
		}

		return rs;
	}

	@Override
	public Boolean delete(Long id) {
		Boolean rs = true;
		try{
			String hql ="delete from AidResident where id=?";
			baseDAO.executeHql(hql,new Object[]{id});
		}catch (Exception e){
			e.printStackTrace();
			rs = false;
		}
		return rs;
	}

	@Override
	public Boolean deleteSoft(Long id) {
		Boolean rs = true;
		try{
			String hql ="update  AidResident set isRemoved=1 where id=?";
			baseDAO.executeHql(hql,new Object[]{id});
		}catch (Exception e){
			e.printStackTrace();
			rs = false;
		}
		return rs;
	}


	/**
	 * 地图首页 获取帮扶对象的坐标分布<br/>
	 * 	 所属镇名称
		 贫困户类型
		 名字
		 经度
		 纬度
	 * @param aidResident
	 * @return
	 */
	@Override
	public List<AidResident> getPositions(AidResident aidResident) {
		StringBuffer hql = new StringBuffer("SELECT ar.id id,ar.name name,area.name belongedCounty,ar.longitude longitude,ar.latitude latitude,ar.poor_cate poorCate ");
		hql.append(" FROM aid_resident ar ");
		hql.append(" INNER JOIN area  ON ar.belonged_country = area.id ");
		hql.append(" where created_by=?");

		List<Object> params = new ArrayList<Object>();
		params.add(aidResident.getCreatedBy());

		//贫困类型
		if(aidResident.getPoorCate()!= null){
			hql.append(" and ar.poor_cate=? ");
			params.add(aidResident.getPoorCate());
		}

		//姓名
		if(aidResident.getName() != null){
			hql.append(" and ar.name =? ");
			params.add(aidResident.getName());
		}


		//镇id
		if(aidResident.getBelongedCounty() != null){
			hql.append(" and ar.belonged_country=?  ");
			params.add(aidResident.getBelongedCounty());
		}


		Object[] paramObj = new Object[params.size()];

		for(int i=0;i<params.size();i++){
			paramObj[i] = params.get(i);
		}

		return baseDAO.getResultBySQL(hql.toString(),paramObj);

	}

	/**
	 * 获取帮扶对象的简要信息
	 *
	 *   头像
		 姓名
		 性别
		 身份证号（前端计算年龄）
		 所属村
		 贫困类型
		 地址
		 电话
		 帮扶负责人姓名：
	 * @param aidResident
	 * @param page
	 * @param size
	 * @return
	 */
	@Override
	public List<AidResident> getRemarkInfos(AidResident aidResident, Integer page, Integer size) {
		StringBuffer hql = new StringBuffer("SELECT ar.id id,ar.name NAME,ar.img img,ar.gender gender, ar.id_card idCard,");
		hql.append(" area.name belongedCounty,ar.poor_cate poorCate,ar.address address,ar.phone phone, ");
		hql.append(" user.name createdBy ");
		hql.append(" FROM aid_resident ar ");
		hql.append(" INNER JOIN AREA  ON ar.belonged_country = area.id ");
		hql.append(" INNER JOIN USER ON ar.created_by = user.id ");
		hql.append(" where ar.created_by=? ");

		List<Object> params = new ArrayList<Object>();
		params.add(aidResident.getCreatedBy());



		//贫困类型
		if(aidResident.getPoorCate()!= null){
			hql.append(" and ar.poor_cate=? ");
			params.add(aidResident.getPoorCate());
		}

		//镇id
		if(aidResident.getBelongedCounty() != null){
			hql.append(" and ar.belonged_country=?  ");
			params.add(aidResident.getBelongedCounty());
		}

		hql.append("order by ar.created_at desc limit ?,?");
		params.add(page-1);
		params.add((page*size));

		Object[] paramObj = new Object[params.size()];

		for(int i=0;i<params.size();i++){
			paramObj[i] = params.get(i);
		}

		return baseDAO.getResultBySQL(hql.toString(),paramObj);

	}

	/**
	 * 根据帮扶对象id 查询帮扶对象
	 * 	 头像
		 姓名
		 性别
		 身份证号（前端计算年龄）
		 所属村
		 贫困类型
		 地址
		 电话
		 帮扶负责人姓名：
	 * @param id
	 * @return
	 */
	@Override
	public AidResident getRemarkInfo(Long id) {
		StringBuffer hql = new StringBuffer("SELECT ar.id id,ar.name name,ar.img img,ar.gender gender, ar.id_card idCard,");
		hql.append(" area.name belongedCounty,ar.poor_cate poorCate,ar.address address,ar.phone phone, ");
		hql.append(" user.name createdBy ");
		hql.append(" FROM aid_resident ar ");
		hql.append(" INNER JOIN area  ON ar.belonged_country = area.id ");
		hql.append(" INNER JOIN user ON ar.created_by = user.id ");
		hql.append(" where ar.id=? ");

		List<AidResident> aidResidents = baseDAO.getResultBySQL(hql.toString(),new Object[]{id},AidResident.class);

		if(aidResidents != null && aidResidents.size()>0){
			return aidResidents.get(0);
		}
		return null;
	}

	@Override
	public AidResident getDetailInfo(Long id) {
		return baseDAO.get(AidResident.class,id);
	}
}
