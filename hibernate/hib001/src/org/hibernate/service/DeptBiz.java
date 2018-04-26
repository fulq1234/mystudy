package org.hibernate.service;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.dao.DeptDao;
import org.hibernate.dao.HibernateUtil;
import org.hibernate.demo.entity.Dept;

public class DeptBiz {

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
