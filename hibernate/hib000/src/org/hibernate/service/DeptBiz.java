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
