package org.hibernate.service;

import java.util.Iterator;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.demo.dao.DeptDao;
import org.hibernate.demo.entity.Dept;
import org.hibernate.demo.entity.Emp2;

public class DeptBiz {
	private DeptDao dao = new DeptDao();
	
	/**
	 * 不管部门下面有没有员工，都删除
	 * @param deptno
	 */
	public void deleteDept(byte deptno){
		Transaction tx = null;
		
		try{
			tx = dao.currentSession().beginTransaction();
			dao.delete(deptno);
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx != null){
				tx.rollback();
			}
		}
		
		
	}
	
	public void addNewDept(Dept dept){
		Transaction tx = null;
		
		try{
			tx = dao.currentSession().beginTransaction();
			dao.save(dept);
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx != null){
				tx.rollback();
			}
		}
		
		
	}
	
	public Dept findById(byte deptno){
		Transaction tx = null;
		Dept result = null;
		try{
			tx = dao.currentSession().beginTransaction();
			result = dao.get((byte)1);
			System.out.println(result.getDname());
			Set<Emp2> emps = result.getEmps();
			Iterator<Emp2> its = emps.iterator();
			while(its.hasNext()){
				System.out.println(its.next().getEname());
			}
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
