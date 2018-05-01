package org.hibernate.demo.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.demo.dao.HibernateUtil;
import org.hibernate.demo.entity.Employee;
import org.hibernate.demo.entity.Project;
import org.junit.Test;

public class Ex03 {

	@Test
	public void ex03_1(){
		Session session = null;
		Transaction tx = null;
		
		try{
			//打开session
			session = HibernateUtil.currentSession();
			//开启事务
			tx = session.beginTransaction();
			Employee employee1 = new Employee((short)3,"王五");
			Employee employee2 = new Employee((short)4,"赵六");
			
			Project project1 = new Project((short)3,"3号项目");
			Project project2 = new Project((short)4,"4号项目");
			
			//双方都要保存数据
			project1.getEmployees().add(employee1);
			project1.getEmployees().add(employee2);
			
			employee1.getProjects().add(project1);
			employee2.getProjects().add(project2);
			
			project2.getEmployees().add(employee1);
			
			employee1.getProjects().add(project2);
			
			//保存有控制权的一方
			session.save(project1);
			session.save(project2);
			
			//提交事务
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			//回滚事务
			if(tx != null){
				tx.rollback();
			}
		}
	}
}
