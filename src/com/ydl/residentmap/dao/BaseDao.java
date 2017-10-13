package com.ydl.residentmap.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {

	Serializable save(T o);

	void delete(T o);

	void update(T o);

	void saveOrUpdate(T o);

	List<T> find(String hql);

	List<T> find(String hql, Object[] param);

	List<T> find(String hql, List<Object> param);

	List<T> find(String hql, Object[] param, Integer page, Integer rows);

	List<T> find(String hql, List<Object> param, Integer page, Integer rows);

	T get(Class<T> c, Serializable id);

	T get(String hql, Object[] param);

	T get(String hql, List<Object> param);

	Long count(String hql);

	Long count(String hql, Object[] param);

	Long count(String hql, List<Object> param);

	Integer executeHql(String hql);

	Integer executeHql(String hql, Object[] param);

	Integer executeHql(String hql, List<Object> param);

	List<T> getResultBySQL(String sql, Object[] parameters);
	
	List<T> getResultBySQL(String sql, Object[] parameters, Class<T> t);
}