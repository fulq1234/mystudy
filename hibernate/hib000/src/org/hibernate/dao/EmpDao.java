package org.hibernate.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.demo.entity.Emp;
import org.hibernate.demo.entity.EmpCondition;

public class EmpDao extends BaseDao {
	
	/**
	 * 根据条件查询语句
	 * @param hql： hsql语句
	 * @param conditions: 条件封装类
	 * @return
	 */
	public List<Emp> findByCondition(String hql,EmpCondition conditions){
		Query query = this.currentSession().createQuery(hql);
		query.setProperties(conditions);
		return query.list();
	}
	
	/**
	 * 保存
	 * @param emp
	 */
	public void saveOrUpdate(Emp emp){
		this.currentSession().saveOrUpdate(emp);
	}
	
	/**
	 * 得到所有数据
	 * 如果出现错误“unexpected token: form”，一般是from写错了
	 * @return
	 */
	public List<Emp> findAllEmps(){
		return this.currentSession().createQuery("from Emp").list();
		
	}
	
	public Long countEmp(){
		return (Long)this.currentSession().createQuery("select count(id) from Emp").uniqueResult();
	}
	
}
