package org.hibernate.dao;

import java.util.List;

import org.hibernate.demo.entity.Emp;

public class EmpDao extends BaseDao {

	public void saveOrUpdate(Emp emp){
		this.currentSession().saveOrUpdate(emp);
	}
	
	public List<Emp> findAllEmps(){
		return this.currentSession().createQuery("form Emp").list();
		
	}
}
