package org.hibernate.service;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.dao.DeptDao;
import org.hibernate.dao.HibernateUtil;
import org.hibernate.demo.entity.Dept;

public class DeptBiz {

	/**
	 * ����һ������
	 * @param dept
	 */
	public void addNewDept(Dept dept){
		Transaction tx = null;
		
		try{
			//��������
			tx = HibernateUtil.currentSession().beginTransaction();
			
			new DeptDao().save(dept);
			
			//�ύ����
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx != null){
				tx.rollback();
			}
		}
	}
}
