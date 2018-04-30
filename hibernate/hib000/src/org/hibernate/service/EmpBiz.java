package org.hibernate.service;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.dao.EmpDao;
import org.hibernate.demo.entity.Emp;
import org.hibernate.demo.entity.EmpCondition;

public class EmpBiz {
	private EmpDao dao = new EmpDao();
	
	/**
	 * ��ҳ��ʾ
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Emp> findByPage(int pageNo,int pageSize){
		Transaction tx = null;
		List<Emp> result = null;
		try{
			//EmpDao dao = new EmpDao();
			tx = dao.currentSession().beginTransaction();
			
			result = dao.findByPage(pageNo, pageSize);
						
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
	 * @param condition
	 * @return
	 */
	public List<Emp> findByCondition(Map<String,Object> condition){
		Transaction tx = null;
		List<Emp> result = null;
		try{
			//EmpDao dao = new EmpDao();
			tx = dao.currentSession().beginTransaction();
			StringBuilder hql = new StringBuilder("from Emp where 1=1");
			if(condition.get("job") != null && ((String)condition.get("job")).length() >0){
				hql.append(" and job = :job");//����������������Ը�ֵ
			}
			if(condition.get("salary") != null){
				hql.append(" and salary = :salary");
			}
			if(condition.get("from") != null){
				hql.append(" and hireDate > :from");
			}
			if(condition.get("to") != null){
				hql.append(" and hireDate < :to");
			}
			result = dao.findByCondition(hql.toString(), condition);
			
			
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
	 * �鿴���й�Ա
	 * @return
	 */
	public List<Emp> findByCondition(EmpCondition condition){
		Transaction tx = null;
		List<Emp> result = null;
		try{
			//EmpDao dao = new EmpDao();
			tx = dao.currentSession().beginTransaction();
			StringBuilder hql = new StringBuilder("from Emp where 1=1");
			if(condition.getJob() != null && condition.getJob().length() >0){
				hql.append(" and job = :job");//����������������Ը�ֵ
			}
			if(condition.getSalary() != null && condition.getSalary() != 0){
				hql.append(" and salary = :salary");
			}
			if(condition.getFrom() != null){
				hql.append(" and hireDate > :from");
			}
			if(condition.getTo() != null){
				hql.append(" and hireDate < :to");
			}
			result = dao.findByCondition(hql.toString(), condition);
			
			
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx != null){
				tx.rollback();
			}
		}
		return result;
		
	}
	
	public Long countEmp(){
		Transaction tx = null;
		Long l = 0l;
		try{
			EmpDao dao = new EmpDao();
			tx = dao.currentSession().beginTransaction();
			l = dao.countEmp();
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx != null){
				tx.rollback();
			}
		}
		return l;
		
	}
	
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
