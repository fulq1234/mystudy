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
}
