package org.hibernate.service;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.dao.DeptDao;

import org.hibernate.dao.HibernateUtil;
import org.hibernate.demo.entity.Dept;


public class DeptBiz {
	
	private DeptDao dao = new DeptDao();
	
	public List<Dept> findProjections_3(){
		Transaction tx = null;
		List<Dept> result = null;
		
		try{
			tx = dao.currentSession().beginTransaction();
			result = dao.findByProjection_3();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx != null){
				tx.rollback();
			}
		}
		return result;
	}
	
	public List<Object[]> findProjections_2(){
		Transaction tx = null;
		List<Object[]> result = null;
		
		try{
			tx = dao.currentSession().beginTransaction();
			result = dao.findByProjection_2();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx != null){
				tx.rollback();
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Object> findProjections(){
		Transaction tx = null;
		List<Object> result = null;
		
		try{
			tx = dao.currentSession().beginTransaction();
			result = dao.findByProjection();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx != null){
				tx.rollback();
			}
		}
		return result;
	}
	
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
			

			//dept.setLoc("新的地址");此时dept是持久状态，它的内容一变化，hibernate一监控改变，会改变数据库中的数据。
			
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
