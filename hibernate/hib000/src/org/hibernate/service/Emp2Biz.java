package org.hibernate.service;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.dao.Emp2Dao;
import org.hibernate.demo.entity.Dept;
import org.hibernate.demo.entity.Emp;
import org.hibernate.demo.entity.Emp2;

public class Emp2Biz {
	
	private Emp2Dao dao = new Emp2Dao();
	
	
	
	public void delete(Emp2 emp2){
		Transaction tx = null;
		
		try{
			tx = dao.currentSession().beginTransaction();
			dao.delete(emp2);
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx != null){
				tx.rollback();
			}
		}
	}
	
	public void saveOrUpdateEmp2(Emp2 emp2){
		Transaction tx = null;
		
		try{
			tx = dao.currentSession().beginTransaction();
			dao.saveOrUpdate(emp2);
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx != null){
				tx.rollback();
			}
		}
	}
	
	public Emp2 findById(Serializable id){
		Transaction tx = null;
		Emp2 result = null;
		try{
			tx = dao.currentSession().beginTransaction();
			result = dao.get(id);
			System.out.println(result.getEname());
			System.out.println(result.getDept().getDname());
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx != null){
				tx.rollback();
			}
		}
		return result;
	}
	
	public List<Emp2> findEmpByDept(Dept dept){
		Transaction tx = null;
		List<Emp2> result = null;
		try{
			tx = dao.currentSession().beginTransaction();
			result = dao.findByDept(dept);
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx != null){
				tx.rollback();
			}
		}
		return result;
	}
	
	public List<Emp2> findEmpByDept(byte dept){
		Transaction tx = null;
		List<Emp2> result = null;
		try{
			tx = dao.currentSession().beginTransaction();
			result = dao.findByDept(dept);
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx != null){
				tx.rollback();
			}
		}
		return result;
	}
}
