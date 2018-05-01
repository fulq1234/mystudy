package org.hibernate.demo.test;

import java.util.Date;
import java.util.List;

import org.hibernate.demo.entity.Dept;
import org.hibernate.demo.entity.Emp;
import org.hibernate.demo.entity.Emp2;
import org.hibernate.service.Emp2Biz;
import org.junit.Test;

public class Hib2Test {
	
	/**
	 * ������̬ɾ������
	 */
	@Test
	public void testDelete(){
		Emp2 emp = new Emp2();
		emp.setEmpno((short)1);
		new Emp2Biz().delete(emp);
	}
	
	/**
	 * ���2.empno���ڣ��־�̬��ִ�и��²���
	 */
	@Test
	public void testSaveOrUpdate2(){
		Emp2 emp = new Emp2();
		emp.setEmpno((short)1);
		emp.setEname("emp456");
		emp.setComm(122d);
		emp.setJob("����");
		emp.setHiredate(new Date());
		emp.setSal(100d);
	
		//ָ�������ʱ��Ҫ����һ�����
		Dept dept = new Dept();
		dept.setDeptno((byte)1);
		//dept.setDname("�г���");
		emp.setDept(dept);
		new Emp2Biz().saveOrUpdateEmp2(emp);
		
	}
	
	/**
	 * ���1�����Emp2û��empnoֵ����ִ������������Dept���ж�Ӧ���ݱ�����ڡ�
	 */
	@Test
	public void testSaveOrUpdate(){
		Emp2 emp = new Emp2();
		emp.setEname("emp123");
		emp.setComm(122d);
		emp.setJob("����");
		emp.setHiredate(new Date());
		emp.setSal(100d);
	
		//ָ�������ʱ��Ҫ����һ�����
		Dept dept = new Dept();
		dept.setDeptno((byte)1);
		//dept.setDname("�г���");
		emp.setDept(dept);
		new Emp2Biz().saveOrUpdateEmp2(emp);
		
	}
	
	@Test
	public void testEmpToDept(){
		Emp2 emp = new Emp2Biz().findById((short)1);
	
		/*System.out.println(emp.getEname());
		System.out.println(emp.getDept().getDname());
		������ò���Dept��ֵ����Ҫ�����������ֵ,����������񵽺�������Ҳ���ܻ�ȡ�ˡ�*/
	}
	
	@Test
	public void testFindByDept(){
		Dept dept = new Dept();
		dept.setDeptno((byte)1);
		List<Emp2> result = new Emp2Biz().findEmpByDept(dept);
		for(Emp2 emp : result){
			System.out.println(emp.getEname());
		}
	}
	
	@Test
	public void testFindByDeptNo(){
		List<Emp2> result = new Emp2Biz().findEmpByDept((byte)1);
		for(Emp2 emp : result){
			System.out.println(emp.getEname());
		}
	}
}
