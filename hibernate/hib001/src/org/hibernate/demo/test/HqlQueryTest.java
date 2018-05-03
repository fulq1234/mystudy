package org.hibernate.demo.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.demo.dao.HibernateUtil;
import org.hibernate.demo.entity.Dept;
import org.hibernate.demo.entity.Emp2;
import org.junit.Test;

/**
 * HQL连接查询与Hibernate注解
 * @author Administrator
 *
 */
public class HqlQueryTest {

	/**
	 * 迫切左连接
	 */
	@Test
	public void testLeftJoinFetch(){
		Transaction tx = null;
		try{
			Session session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			//sql:select * from DEPT d left join EMP e on d.deptno = e.deptno
			String hql = "from Dept d left join fetch d.emps e";//from Emp e left join e.dept d
			List<Object[]> result = session.createQuery(hql).list();
			for(Object[] o : result){
				System.out.println(o);
				System.out.println(((Dept)o[0]).getDname());
				System.out.println("\t" + ((Emp2)o[1]).getEname());
			}
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx != null){
				tx.rollback();
			}
		}
	}
	
	/**
	 * 测试内连接
	 */
	@Test
	public void testInnerJoin(){
		Transaction tx = null;
		try{
			Session session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			//sql:select * from DEPT d left join EMP e on d.deptno = e.deptno
			String hql = "from Dept d join d.emps e";//from Emp e left join e.dept d
			List<Object[]> result = session.createQuery(hql).list();
			for(Object[] o : result){
				System.out.println(((Dept)o[0]).getDname());
				System.out.println("\t" + ((Emp2)o[1]).getEname());
			}
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx != null){
				tx.rollback();
			}
		}
	}
	
	/**
	 * Hql支持左连接
	 */
	@Test
	public void testLeftJoin(){
		Transaction tx = null;
		try{
			Session session = HibernateUtil.currentSession();
			tx = session.beginTransaction();
			//sql:select * from DEPT d left join EMP e on d.deptno = e.deptno
			String hql = "from Dept d left join d.emps e";//from Emp e left join e.dept d
			List<Object[]> result = session.createQuery(hql).list();
			for(Object[] o : result){
				System.out.println(o);
				System.out.println(((Dept)o[0]).getDname());
				System.out.println("\t" + ((Emp2)o[1]).getEname());
			}
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			if(tx != null){
				tx.rollback();
			}
		}
	}
}
