package org.hibernate.service;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.dao.DeptDao;
import org.hibernate.dao.HibernateUtil;
import org.hibernate.demo.entity.Dept;

public class DeptBiz {

	/**
	 * 删除
	 * @param id
	 */
	public void delete(Serializable id){		
		Transaction tx = null;		
		try{
			tx = HibernateUtil.currentSession().beginTransaction();
			new DeptDao().delete(id);
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx != null){
				tx.rollback();
			}
		}
	}
	
	public void update(Dept dept){
		Transaction tx = null;		
		try{
			tx = HibernateUtil.currentSession().beginTransaction();
			new DeptDao().currentSession().update(dept);
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx != null){
				tx.rollback();
			}
		}
	}
	/**
	 * 修改
	 * @param dept
	 */
	public void modifyDept(Dept dept){
		Transaction tx = null;
		
		try{
			tx = HibernateUtil.currentSession().beginTransaction();
			
			
			//等价于new DeptDao().merge(dept); begin
			Dept deptToUpdate = new DeptDao().load(dept.getDeptno());
			
			//重新赋值
			deptToUpdate.setDeptno(dept.getDeptno());
			deptToUpdate.setDname(dept.getDname());
			deptToUpdate.setLoc(dept.getLoc());
			//等价于new DeptDao().merge(dept); end
			
			
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx != null){
				tx.rollback();
			}
		}
		
	}
	
	
	public Dept findById_get(Serializable id){
		Transaction tx = null;
		Dept dept = null;
		try{
			tx = HibernateUtil.currentSession().beginTransaction();
			dept = new DeptDao().get(id);
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx != null){
				tx.rollback();
			}
		}
		return dept;
	}
	

	public Dept findById_load(Serializable id){
		Transaction tx = null;
		Dept dept = null;
		try{
			tx = HibernateUtil.currentSession().beginTransaction();
			dept = new DeptDao().load(id);
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx != null){
				tx.rollback();
			}
		}
		return dept;
	}
	/**
	 * 新增一个部门
	 * @param dept
	 */
	public void addNewDept(Dept dept){
		Transaction tx = null;
		
		try{
			//开启事务
			tx = HibernateUtil.currentSession().beginTransaction();
			
			new DeptDao().save(dept);
			
			//提交事务
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx != null){
				tx.rollback();
			}
		}
	}
}
