package com.ydl.residentmap.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import com.ydl.residentmap.util.DES;
import com.ydl.residentmap.util.MD5Util;
import org.springframework.stereotype.Repository;

import com.ydl.residentmap.dao.BaseDao;
import com.ydl.residentmap.dao.UserDao;
import com.ydl.residentmap.model.Pager;
import com.ydl.residentmap.model.User;
import com.ydl.residentmap.util.IdWorker;

@Repository
public class UserDaoImpl implements UserDao {

	@Resource
	private BaseDao<User> baseDAO;

	@Override
	public Boolean save(User user) {
		Boolean flag = true;
		try {
			//DES des = new DES();
			//byte[] encontent = des.Encrytor(pwd);
			//byte[] decontent = des.Decryptor(encontent);
			//String mw = new String(decontent);
			//String jmh = new String(encontent);
			//user.setPassword(jmh);

			baseDAO.save(user);
			System.out.println("添加用户 OK   用户ID："+user.getId());
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public List<User> getAll() {
		String hql="from User order by createAt desc ";
		return baseDAO.find(hql);
	}

	@Override
	public List<User> getUsersByType(Integer type) {
		String hql="from User  where userType="+type + " order by createAt desc ";
		return baseDAO.find(hql);
	}

	@Override
	public User getUserByNamePwd(String name,String pwd)
	{
		String pwd2 = MD5Util.string2MD5(pwd);
		String hql="from User  where name=? and password=?";
		Object[] params = new Object[2];
		params[0] = name;
		params[1] = pwd2;
		List<User> users = baseDAO.find(hql, params);
		if(users.size() ==0){
			return null;
		}
		return users.get(0);
	}

	@Override
	public User getUserByName(String name) {
		String hql="from User  where name=?";
		Object[] params = new Object[1];
		params[0] = name;
		List<User> users = baseDAO.find(hql, params);
		if(users.size() ==0){
			return null;
		}
		return users.get(0);
	}

	@Override
	public List<User> getUsersByName(String name) {
		String hql="from User where name like ? order by createAt desc ";
		Object[] params = new Object[1];
		params[0] = "%"+name+"%";
		List<User> users = baseDAO.find(hql, params);
		return users;
	}

	/**
	 * 删除用户
	 */
	@Override
	public Boolean delete(Long id) {
		Boolean flag = true;
		try {
			User data = baseDAO.get(User.class, id);
			baseDAO.delete(data);
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
			String hql="delete User as r where r.id in ("+String.join(",", idList)+")";
			cnt=baseDAO.executeHql(hql);
		} catch (Exception e) {
			e.printStackTrace();
			cnt = 0;
		}
		return cnt;
	}

	@Override
	public Boolean update(User user) {
		Boolean flag = true;
		try {
			baseDAO.update(user);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public User get(Long id) {	
		return baseDAO.get(User.class, id);
	}

	@Override
	public Pager<User> getUsers(int offset, int size) {
		//查询共多少数据
		String totalSql = "select count(*) from User";
		Long total = baseDAO.count(totalSql);
		String dateHql = "from User";
		List<User> userList = baseDAO.find(dateHql, new Object[]{}, offset, size);
		Pager<User> pager = new Pager<User>();
		pager.setOffset(offset);
		pager.setSize(size);
		pager.setTotal(total);
		pager.setData(userList);
		return pager;
	}
}
