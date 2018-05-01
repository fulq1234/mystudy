package org.hibernate.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.demo.entity.Dept;
import org.hibernate.demo.entity.Emp2;

public class Emp2Dao extends BaseDao {
	
	/**
	 * ����id��ȡֵ
	 * @param id
	 * @return
	 */
	public Emp2 get(Serializable id){
		return (Emp2) this.currentSession().get(Emp2.class, id);
	}
	
	/**
	 * ���������޸Ĳ���
	 * @param emp
	 */
	public void saveOrUpdate(Emp2 emp){
		this.currentSession().saveOrUpdate(emp);
	}
	
	public void delete(Emp2 emp){
		this.currentSession().delete(emp);
	}
	
	/**
	 * ���ݲ��ţ���ѯ�����µ����й�Ա
	 * @param dept������
	 * @return
	 */
	public List<Emp2> findByDept(Dept dept){
		return this.currentSession()
				.createQuery("from Emp2 where dept=?")//from <����> where <�����ֶ�����> = ? . ��Ȼ�����ǲ��Ŷ��󣬵�ʵ���ϻ���deptno
				.setParameter(0, dept).list();
		
	}
	
	/**
	 * 
	 * @param deptno������
	 * @return
	 */
	public List<Emp2> findByDept(byte deptno){
		return this.currentSession()
				.createQuery("from Emp2 where dept=?")//from <����> where <�����ֶ�����> = ? . ��Ȼ�����ǲ��Ŷ��󣬵�ʵ���ϻ���deptno
				//.createQuery("from Emp2 where deptno=?")//����������Ǹ�������
				.setByte(0, deptno).list();
		
	}
}
