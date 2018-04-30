package org.hibernate.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.demo.entity.Dept;

public class DeptDao extends BaseDao{
	
	/**
	 * 情况3.投影查询，通过构造方法封装成对象
	 * @return
	 */
	public List<Dept> findByProjection_3(){
		return this.currentSession().createQuery("select dname,loc from Dept").list();
	}
	
	/**
	 * 情况2.投影查询,封装成Object数组
	 * @return
	 */
	public List<Object[]> findByProjection_2(){
		return this.currentSession().createQuery("select dname,loc from Dept").list();
	}
	
	/**
	 * 情况1. 封装成Object对象,select 一个字段的时候返回Object类型
	 * 投影查询：查询一个或多个属性，但不全部查询，这就是投影查询
	 * 只要用了select语句，hibernate就认为是散装的，用Object修饰。不会封装成对象，即使select出全部字段，也不会封装成对象
	 * @return
	 */
	public List<Object> findByProjection(){
		return this.currentSession().createQuery("select dname from Dept").list();
	}
	
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
