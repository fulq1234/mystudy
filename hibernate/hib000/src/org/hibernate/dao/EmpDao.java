package org.hibernate.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.demo.entity.Emp;
import org.hibernate.demo.entity.EmpCondition;

public class EmpDao extends BaseDao {
	
	/**
	 * ����������ѯ���
	 * @param hql�� hsql���
	 * @param conditions: ������װ��
	 * @return
	 */
	public List<Emp> findByCondition(String hql,EmpCondition conditions){
		Query query = this.currentSession().createQuery(hql);
		query.setProperties(conditions);
		return query.list();
	}
	
	/**
	 * ����
	 * @param emp
	 */
	public void saveOrUpdate(Emp emp){
		this.currentSession().saveOrUpdate(emp);
	}
	
	/**
	 * �õ���������
	 * ������ִ���unexpected token: form����һ����fromд����
	 * @return
	 */
	public List<Emp> findAllEmps(){
		return this.currentSession().createQuery("from Emp").list();
		
	}
	
	public Long countEmp(){
		return (Long)this.currentSession().createQuery("select count(id) from Emp").uniqueResult();
	}
	
}
