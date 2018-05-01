package org.hibernate.demo.dao;

import org.hibernate.demo.entity.Dept;

public class DeptDao extends BaseDao{

	/**
	 * һ�Զ�˫�����
	 * @param deptno
	 * @return
	 */
	public Dept get(byte deptno){
		return (Dept) this.currentSession().get(Dept.class, deptno);
	}
	
	/**
	 * save����������cascade����
	 * @param dept
	 */
	public void save(Dept dept){
		this.currentSession().save(dept);
	}
	
	/**
	 * �����ɾ���������Ǹ��־�̬���Ǹ��ݲ�ѯ�õ�
	 * @param deptno
	 */
	public void delete(byte deptno){
		this.currentSession().delete(this.get(deptno));
	}
}
