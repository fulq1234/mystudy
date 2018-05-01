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
			//��session
			session = HibernateUtil.currentSession();
			//��������
			tx = session.beginTransaction();
			Employee employee1 = new Employee((short)3,"����");
			Employee employee2 = new Employee((short)4,"����");
			
			Project project1 = new Project((short)3,"3����Ŀ");
			Project project2 = new Project((short)4,"4����Ŀ");
			
			//˫����Ҫ��������
			project1.getEmployees().add(employee1);
			project1.getEmployees().add(employee2);
			
			employee1.getProjects().add(project1);
			employee2.getProjects().add(project2);
			
			project2.getEmployees().add(employee1);
			
			employee1.getProjects().add(project2);
			
			//�����п���Ȩ��һ��
			session.save(project1);
			session.save(project2);
			
			//�ύ����
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			//�ع�����
			if(tx != null){
				tx.rollback();
			}
		}
	}
}
