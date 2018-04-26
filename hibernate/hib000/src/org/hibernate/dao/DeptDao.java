package org.hibernate.dao;

import java.io.Serializable;

import org.hibernate.demo.entity.Dept;

public class DeptDao extends BaseDao{
	
	public void delete(Serializable id){
		Dept dept = this.load(id);
		this.currentSession().delete(dept);
	}
	
	public void merge(Dept dept){
		currentSession().merge(dept);
	}
	
	/**
	 * 新增
	 * @param dept
	 */
	public void save(Dept dept){
		currentSession().save(dept);
	}
	
	/**
	 * 根据id得到部门
	 * @param id
	 * @return
	 */
	public Dept get(Serializable id){
		return (Dept)currentSession().get(Dept.class, id);
	}
	

	public Dept load(Serializable id){
		return (Dept)currentSession().load(Dept.class, id);
	}
}
