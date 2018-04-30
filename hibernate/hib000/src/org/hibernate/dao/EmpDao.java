package org.hibernate.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.demo.entity.Emp;
import org.hibernate.demo.entity.EmpCondition;

public class EmpDao extends BaseDao {
	
	/**
	 * ��ҳ
	 * @param pageNo���ڼ�ҳ
	 * @param pageSize��һҳ��ʾ����
	 * @return
	 */
	public List<Emp> findByPage(int pageNo,int pageSize){
		return this.currentSession().createQuery("from  Emp order by id")
				.setFirstResult((pageNo -1) * pageSize)//���ôӵڼ�����ʼ
				.setMaxResults(pageSize)//���ö�ȡ����¼��
				.list();
	}
	
	/**
	 * ����������ѯ���
	 * @param hql
	 * @param conditions
	 * @return
	 */
	public List<Emp> findByCondition(String hql,Map<String,Object> conditions){
		Query query = this.currentSession().createQuery(hql);
		query.setProperties(conditions);
		return query.list();
	}
	
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
