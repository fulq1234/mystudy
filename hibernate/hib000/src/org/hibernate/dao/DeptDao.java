package org.hibernate.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.demo.entity.Dept;

public class DeptDao extends BaseDao{
	
	/**
	 * ���3.ͶӰ��ѯ��ͨ�����췽����װ�ɶ���
	 * @return
	 */
	public List<Dept> findByProjection_3(){
		return this.currentSession().createQuery("select dname,loc from Dept").list();
	}
	
	/**
	 * ���2.ͶӰ��ѯ,��װ��Object����
	 * @return
	 */
	public List<Object[]> findByProjection_2(){
		return this.currentSession().createQuery("select dname,loc from Dept").list();
	}
	
	/**
	 * ���1. ��װ��Object����,select һ���ֶε�ʱ�򷵻�Object����
	 * ͶӰ��ѯ����ѯһ���������ԣ�����ȫ����ѯ�������ͶӰ��ѯ
	 * ֻҪ����select��䣬hibernate����Ϊ��ɢװ�ģ���Object���Ρ������װ�ɶ��󣬼�ʹselect��ȫ���ֶΣ�Ҳ�����װ�ɶ���
	 * @return
	 */
	public List<Object> findByProjection(){
		return this.currentSession().createQuery("select dname from Dept").list();
	}
	
	public void delete(Serializable id){
		Dept dept = this.load(id);
		this.currentSession().delete(dept);
	}
	
	public void merge(Dept dept){
		currentSession().merge(dept);
	}
	
	/**
	 * ����
	 * @param dept
	 */
	public void save(Dept dept){
		currentSession().save(dept);
	}
	
	/**
	 * ����id�õ�����
	 * @param id
	 * @return
	 */
	public Dept get(Serializable id){
		return (Dept)currentSession().get(Dept.class, id);
	}
	

	public Dept load(Serializable id){
		return (Dept)currentSession().load(Dept.class, id);
	}
}
