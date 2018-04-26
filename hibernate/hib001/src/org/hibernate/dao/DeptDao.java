package org.hibernate.dao;

import org.hibernate.demo.entity.Dept;

public class DeptDao {
	
	/**
	 * ĞÂÔö
	 * @param dept
	 */
	public void save(Dept dept){
		HibernateUtil.currentSession().save(dept);
	}
	
	
}
