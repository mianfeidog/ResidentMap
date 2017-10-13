package com.ydl.residentmap.service;

import java.util.List;

import com.ydl.residentmap.model.Pager;
import com.ydl.residentmap.model.User;

public interface UserService{
	Boolean save(User obj);
	
	User login(User obj);
	
	Boolean delete(Long id);
	
	Boolean update(User obj);
	
	User get(Long id);
	
	Boolean isExist(String name);
	
	Pager<User> getUsers(int offset, int size);
	
	//根据用户名查找用户
	User getUserByName(String name);
	
	//修改密码
	Boolean modifyPassword(User obj);

	List<User> getAll();

	List<User> getUsersByName(String name);

	Integer deleteList(List<String> idList);
}
