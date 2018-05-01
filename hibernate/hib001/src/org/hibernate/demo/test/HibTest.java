package org.hibernate.demo.test;

import org.hibernate.demo.entity.Dept;
import org.hibernate.demo.entity.Emp2;
import org.hibernate.service.DeptBiz;
import org.junit.Test;

public class HibTest {

	/**
	 * ���û�м�cascade,��ֻɾ�����ţ�Ա�����ж�Ӧ�����������ɿ�
	 */
	@Test
	public void delteDept(){
		new DeptBiz().deleteDept((byte)82);
		
	}
	
	/**
	 * cascade
	 */
	@Test
	public void testAddNewDept(){
		Dept dept = new Dept();
		dept.setDeptno((byte)83);
		dept.setDname("dept004");
		dept.setLoc("����");
		
		Emp2 emp1 = new Emp2();
		emp1.setEname("dp1 e1");
		Emp2 emp2 = new Emp2();
		emp2.setEname("dp1 e2");
		
		emp1.setDept(dept);//�������
		emp2.setDept(dept);//�������
		
		//Ҫ���ڱ��沿�ŵ�ʱ�򣬱����Ա������֪�����Ÿ���Ա�Ĺ�ϵ�����������������ǲ�����
		dept.getEmps().add(emp1);
		dept.getEmps().add(emp2);
		
		new DeptBiz().addNewDept(dept);;
		
	}
	
	@Test
	public void testFindDeptById(){
		Dept dept = new DeptBiz().findById((byte)1);
		
	}
}
