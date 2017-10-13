package com.ydl.residentmap.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.ydl.residentmap.util.MD5Util;
import org.springframework.stereotype.Service;

import com.ydl.residentmap.dao.UserDao;
import com.ydl.residentmap.model.Pager;
import com.ydl.residentmap.model.User;
import com.ydl.residentmap.model.UserException;
import com.ydl.residentmap.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserDao userDao;
	@Override
	public Boolean save(User obj) {
		if(obj.getName() == null){
			throw new UserException("账号不能为空！");
		}
		if(obj.getPassword()== null){
			throw new UserException("密码不能为空！");
		}

		//保存前，校验该账号是否存在，如果存在，返回，账号已经存在
		User dbUser = userDao.getUserByName(obj.getName());
		if(dbUser != null){
			String msg = "";
			throw new UserException("该账号已经存在，" + msg);
		}
		return userDao.save(obj);
	}

	@Override
	public User login(User obj) {
		//根据用户名和密码查询用户
		User u = userDao.getUserByName(obj.getName());
		String objPwd = MD5Util.string2MD5(obj.getPassword());
		if(u == null){
			throw new UserException("无法登陆");
		}else if(!u.getPassword().equals(objPwd)){
			throw new UserException("密码错误");
		}
		return u;
	}

	@Override
	public Boolean delete(Long id) {
		return userDao.delete(id);
	}

	@Override
	public Boolean update(User user) {
		return userDao.update(user);
	}

	@Override
	public User get(Long id) {
		return userDao.get(id);
	}
	
	/**
	 * 判断该账号是否存在
	 */
	@Override
	public Boolean isExist(String name) {
	    User user = userDao.getUserByName(name);
	    if(user!= null){
	    	return true;
	    }
		return false;
	}

	@Override
	public Pager<User> getUsers(int offset, int size) {
		return userDao.getUsers(offset, size);
	}

	@Override
	public User getUserByName(String name) {
		return userDao.getUserByName(name);
	}

	@Override
	public Boolean modifyPassword(User obj) {
		//查询用户
		User tempUser = userDao.getUserByName(obj.getName());
		//设置新密码
		tempUser.setPassword(obj.getPassword());
		return userDao.update(tempUser);
		
	}

	@Override
	public List<User> getAll() {
		return userDao.getAll();
	}

	@Override
	public List<User> getUsersByName(String name) {
		return userDao.getUsersByName(name);
	}

	@Override
	public Integer deleteList(List<String> idList) {
		return userDao.deleteList(idList);
	}
}
