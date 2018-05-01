package org.hibernate.demo.test;

import org.hibernate.demo.entity.Dept;
import org.hibernate.service.DeptBiz;
import org.junit.Test;

public class HibTest {

	@Test
	public void testFindDeptById(){
		Dept dept = new DeptBiz().findById((byte)1);
		
	}
}
