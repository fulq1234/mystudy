package org.hibernate.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.demo.entity.Dept;
import org.hibernate.demo.entity.Emp2;

public class Emp2Dao extends BaseDao {
	
	/**
	 * 根据id获取值
	 * @param id
	 * @return
	 */
	public Emp2 get(Serializable id){
		return (Emp2) this.currentSession().get(Emp2.class, id);
	}
	
	/**
	 * 新增或者修改操作
	 * @param emp
	 */
	public void saveOrUpdate(Emp2 emp){
		this.currentSession().saveOrUpdate(emp);
	}
	
	public void delete(Emp2 emp){
		this.currentSession().delete(emp);
	}
	
	/**
	 * 根据部门，查询部门下的所有雇员
	 * @param dept：对象
	 * @return
	 */
	public List<Emp2> findByDept(Dept dept){
		return this.currentSession()
				.createQuery("from Emp2 where dept=?")//from <类名> where <类中字段名称> = ? . 虽然传的是部门对象，但实际上还是deptno
				.setParameter(0, dept).list();
		
	}
	
	/**
	 * 
	 * @param deptno：主键
	 * @return
	 */
	public List<Emp2> findByDept(byte deptno){
		return this.currentSession()
				.createQuery("from Emp2 where dept=?")//from <类名> where <类中字段名称> = ? . 虽然传的是部门对象，但实际上还是deptno
				//.createQuery("from Emp2 where deptno=?")//这个和上面那个都可以
				.setByte(0, deptno).list();
		
	}
}
