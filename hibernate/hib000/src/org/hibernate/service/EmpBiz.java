package org.hibernate.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.dao.EmpDao;
import org.hibernate.demo.entity.Emp;

public class EmpBiz {
	
	/**
	 * �鿴���й�Ա
	 * @return
	 */
	public List<Emp> findAllEmp(){
		Transaction tx = null;
		List<Emp> result = null;
		try{
			EmpDao dao = new EmpDao();
			tx = dao.currentSession().beginTransaction();
			result = dao.findAllEmps();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx != null){
				tx.rollback();
			}
		}
		return result;
		
	}
	
	public void saveOrUpdate(Emp emp){
		Transaction tx =null;
		try{
			EmpDao dao = new EmpDao();
			tx = dao.currentSession().beginTransaction();
			
			dao.saveOrUpdate(emp);
			
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx != null){
				tx.rollback();
			}
		}
	}
}
