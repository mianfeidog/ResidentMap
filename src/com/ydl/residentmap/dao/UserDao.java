package com.ydl.residentmap.dao;
import java.util.List;

import com.ydl.residentmap.model.Pager;
import com.ydl.residentmap.model.User;

public interface UserDao{
	Boolean save(User user);
	
	List<User> getAll();
	
	List<User> getUsersByType(Integer type);
	
	User getUserByName(String name);

	User getUserByNamePwd(String name,String pwd);

	List<User> getUsersByName(String name);
	
	Boolean delete(Long id);

	Integer deleteList(List<String> idList);
	
	Boolean update(User user);
	
	User get(Long id);
	
	Pager<User> getUsers(int offset,int size);
}
