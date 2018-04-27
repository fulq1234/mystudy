package org.hibernate.service;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.dao.DeptDao;
import org.hibernate.dao.EmpDao;
import org.hibernate.dao.HibernateUtil;
import org.hibernate.demo.entity.Dept;
import org.hibernate.demo.entity.Emp;

public class DeptBiz {

	
	
	/**
	 * ɾ��
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
	 * �޸�
	 * @param dept
	 */
	public void modifyDept(Dept dept){
		Transaction tx = null;
		
		try{
			tx = HibernateUtil.currentSession().beginTransaction();
			
			
			//�ȼ���new DeptDao().merge(dept); begin
			Dept deptToUpdate = new DeptDao().load(dept.getDeptno());
			
			//���¸�ֵ
			deptToUpdate.setDeptno(dept.getDeptno());
			deptToUpdate.setDname(dept.getDname());
			deptToUpdate.setLoc(dept.getLoc());
			//�ȼ���new DeptDao().merge(dept); end
			
			
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
	 * ����һ������
	 * @param dept
	 */
	public void addNewDept(Dept dept){
		Transaction tx = null;
		
		try{
			//��������
			tx = HibernateUtil.currentSession().beginTransaction();
			
			new DeptDao().save(dept);
			

			//dept.setLoc("�µĵ�ַ");��ʱdept�ǳ־�״̬����������һ�仯��hibernateһ��ظı䣬��ı����ݿ��е����ݡ�
			
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
