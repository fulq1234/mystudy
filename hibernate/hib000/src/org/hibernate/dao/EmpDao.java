package org.hibernate.dao;

import java.util.List;

import org.hibernate.demo.entity.Emp;

public class EmpDao extends BaseDao {

	public void saveOrUpdate(Emp emp){
		this.currentSession().saveOrUpdate(emp);
	}
	
	/**
	 * 得到所有数据
	 * @return
	 */
	public List<Emp> findAllEmps(){
		return this.currentSession().createQuery("form Emp").list();
		
	}
	
	public Long countEmp(){
		return (Long)this.currentSession().createQuery("select count(id) from Emp").uniqueResult();
	}
	
}
