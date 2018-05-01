package org.hibernate.demo.dao;

import org.hibernate.demo.entity.Dept;

public class DeptDao extends BaseDao{

	/**
	 * 一对多双向关联
	 * @param deptno
	 * @return
	 */
	public Dept get(byte deptno){
		return (Dept) this.currentSession().get(Dept.class, deptno);
	}
	
	/**
	 * save方法，级联cascade属性
	 * @param dept
	 */
	public void save(Dept dept){
		this.currentSession().save(dept);
	}
	
	/**
	 * 这里的删除，内容是个持久态，是根据查询得到
	 * @param deptno
	 */
	public void delete(byte deptno){
		this.currentSession().delete(this.get(deptno));
	}
}
