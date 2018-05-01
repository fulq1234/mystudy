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
	 * 以游离态删除操作
	 */
	@Test
	public void testDelete(){
		Emp2 emp = new Emp2();
		emp.setEmpno((short)1);
		new Emp2Biz().delete(emp);
	}
	
	/**
	 * 情况2.empno存在，持久态，执行更新操作
	 */
	@Test
	public void testSaveOrUpdate2(){
		Emp2 emp = new Emp2();
		emp.setEmpno((short)1);
		emp.setEname("emp456");
		emp.setComm(122d);
		emp.setJob("打杂");
		emp.setHiredate(new Date());
		emp.setSal(100d);
	
		//指定外键的时候，要定义一个外键
		Dept dept = new Dept();
		dept.setDeptno((byte)1);
		//dept.setDname("市场部");
		emp.setDept(dept);
		new Emp2Biz().saveOrUpdateEmp2(emp);
		
	}
	
	/**
	 * 情况1，如果Emp2没有empno值，就执行新增操作，Dept表中对应数据必须存在。
	 */
	@Test
	public void testSaveOrUpdate(){
		Emp2 emp = new Emp2();
		emp.setEname("emp123");
		emp.setComm(122d);
		emp.setJob("打杂");
		emp.setHiredate(new Date());
		emp.setSal(100d);
	
		//指定外键的时候，要定义一个外键
		Dept dept = new Dept();
		dept.setDeptno((byte)1);
		//dept.setDname("市场部");
		emp.setDept(dept);
		new Emp2Biz().saveOrUpdateEmp2(emp);
		
	}
	
	@Test
	public void testEmpToDept(){
		Emp2 emp = new Emp2Biz().findById((short)1);
	
		/*System.out.println(emp.getEname());
		System.out.println(emp.getDept().getDname());
		在这里得不到Dept的值，需要在事务里面获到值,在事务里面获到后，在外面也就能获取了。*/
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
