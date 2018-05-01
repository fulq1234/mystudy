package org.hibernate.demo.test;

import org.hibernate.demo.entity.Dept;
import org.hibernate.demo.entity.Emp2;
import org.hibernate.service.DeptBiz;
import org.junit.Test;

public class HibTest {

	/**
	 * 如果没有加cascade,就只删除部门，员工表中对应的外键，定义成空
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
		dept.setLoc("宁波");
		
		Emp2 emp1 = new Emp2();
		emp1.setEname("dp1 e1");
		Emp2 emp2 = new Emp2();
		emp2.setEname("dp1 e2");
		
		emp1.setDept(dept);//设置外键
		emp2.setDept(dept);//设置外键
		
		//要想在保存部门的时候，保存雇员，必须知道部门跟雇员的关系，这个仅仅有外键，是不够的
		dept.getEmps().add(emp1);
		dept.getEmps().add(emp2);
		
		new DeptBiz().addNewDept(dept);;
		
	}
	
	@Test
	public void testFindDeptById(){
		Dept dept = new DeptBiz().findById((byte)1);
		
	}
}
