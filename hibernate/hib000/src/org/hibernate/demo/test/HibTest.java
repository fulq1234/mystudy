package org.hibernate.demo.test;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.demo.entity.Dept;
import org.hibernate.demo.entity.Emp;
import org.hibernate.demo.entity.EmpCondition;
import org.hibernate.demo.util.Tool;
import org.hibernate.service.DeptBiz;
import org.hibernate.service.EmpBiz;
import org.junit.Test;

/**
 * Junit������
 * @author Administrator
 *
 */
public class HibTest {
	
	@Test
	public void findProjections_3(){
		List<Dept> result = new DeptBiz().findProjections_3();
		for(Dept obj : result){
			System.out.println(obj.getDname() +"\t" + obj.getLoc());
		}
	}
	
	@Test
	public void findProjections_2(){
		List<Object[]> result = new DeptBiz().findProjections_2();
		for(Object[] obj : result){
			System.out.println(obj[0] +"\t" + obj[1]);
		}
	}
	
	
	@Test
	public void findProjections(){
		List<Object> result = new DeptBiz().findProjections();
		for(Object obj : result){
			System.out.println(obj);
		}
	}
	
	@Test
	public void testFindByPage() throws ParseException{
		int pageNo = 2;
		int pageSize = 1;
		List<Emp> emp = new EmpBiz().findByPage(pageNo,pageSize);
		System.out.println(emp.size());
	}
	
	/**
	 * ʹ��map����ѯ����
	 * @throws ParseException
	 */
	@Test
	public void testfindByCondition_3() throws ParseException{
		Map<String,Object> conditions = new HashMap<String,Object>();
		conditions.put("job","1");
		conditions.put("salary",3333.0);
		conditions.put("from",Tool.strToDate("1981-4-1", "yyyy-mm-dd"));
		conditions.put("to",Tool.strToDate("1985-9-9", "yyyy-mm-dd"));
		
		List<Emp> emp = new EmpBiz().findByCondition(conditions);
		System.out.println(emp.size());
	}
	
	/**
	 * ���Բ�ѯ��
	 * @throws ParseException
	 */
	@Test
	public void testfindByCondition_2() throws ParseException{
		EmpCondition conditions = new EmpCondition();
		conditions.setJob("1");
		conditions.setSalary(3333.0);
		conditions.setFrom(Tool.strToDate("1981-4-1", "yyyy-mm-dd"));
		conditions.setTo(Tool.strToDate("1985-9-9", "yyyy-mm-dd"));
		
		List<Emp> emp = new EmpBiz().findByCondition(conditions);
		System.out.println(emp.size());
	}
	
	@Test
	public void testCountEmp(){
		long l = new EmpBiz().countEmp();
		System.out.println(l);
	}
	
	@Test
	public void testQueryAllEmp(){
		List<Emp> list= new EmpBiz().findAllEmp();
		System.out.println(list.size());
	}
	
	/**
	 * saveOrUpdate����ִ̬��update������
	 */
	@Test
	public void testSaveUpdate2(){
		Emp emp = new Emp();
		emp.setEmpNo(1);//������˸�����ֵ�������Ǻ����ݿ���һ�������Ӧ����ʵsession��û���������ʱ�����saveOrUpdateʱ��ִ��update
		emp.setEmpName("new Emp2");
		
		new EmpBiz().saveOrUpdate(emp);
		
	}
	
	/**
	 * ��Ӧ��ʱ����saveorUpdate����ִ�б������
	 * ִ��ǰ�����һ��select��䣬����Ϊ����������ʽ��increment������Ǵ����ݿ���ȡ�����ֵ,���ֵ+1������Ҫ�����ݡ�
	 */
	@Test
	public void testSaveUpdate(){
		Emp emp = new Emp();
		emp.setEmpName("new Emp444");
		
		new EmpBiz().saveOrUpdate(emp);
		
	}
	
	@Test
	public void testDelete(){
		new DeptBiz().delete((byte)1);
	}
	
	@Test
	public void testUpdate_2(){
		Dept dept = new Dept();
		dept.setDeptno((byte)11);
		dept.setDname("������");
		dept.setLoc("����1");
		
		new DeptBiz().update(dept);
	}
	
	/**
	 * ���Ը���
	 */
	@Test
	public void testUpdate(){
		Dept dept = new Dept();
		dept.setDeptno((byte)11);
		dept.setDname("������");
		dept.setLoc("����2");
		
		new DeptBiz().modifyDept(dept);
	}
	
	@Test
	public void testGet(){
		Dept result = new DeptBiz().findById_get((byte)1);
		System.out.println(result.getDname());
	}
	
	@Test
	public void testLoad(){
		Dept result = new DeptBiz().findById_load((byte)1);
		/**�鲻����ֵ����Ϊ��Ĭ�ϵ��ӳټ��ء� 
		* ��Ҫ�õ�ֵ��
		* 1.������class����������lazy="false"
		* 2.�ڻỰ�ر�֮ǰ���ֵ
		**/
		System.out.println(result.getDeptno());
		System.out.println(result.getDname());
		
	}
	/**
	 * ������������
	 */
	@Test
	public void testAddNew(){
		Dept dept = new Dept();
		dept.setDeptno((byte)16);
		dept.setDname("������");
		dept.setLoc("�����к�����14");
		
		new DeptBiz().addNewDept(dept);
	}
	
	
	@Test
	public void firstShow(){
		Configuration conf = null;//����hibernate�������ļ�
		SessionFactory sf = null;//�����Ự,ά����Hibernate�����е���Ҫ��Ϣ
		Session session = null;//���ձ�ĸ�������ݿ���һ�ι�ͨ���ͽлỰ��Sessionʵʩ�־û������ؼ�API
		Transaction tx = null;//�������
		
//		conf = new Configuration();//�õ�Configurationʵ��
//		conf.configure();//���������ļ�,����hibernate.cfg.xml��Ĭ��λ�����棬���Ծ����޲ξ��С�����ֵ����Configuration������γ���һ���������Ժϲ���һ��
	 try{
		 conf= new Configuration().configure();
		 sf = conf.buildSessionFactory();//��������
		 
		 /*Ϊʲô�Ƽ�ʹ��getCurrentSession  1.�Ự����2.��֤ʹ�õ���ͬһ��session
		 * openSesion ÿ�δ򿪶�����һ���µ�session
		 * getCurrentSesion ʲô�е�ǰ�Ự,��hibernate.cfg.xml���и�����current_session_context_class��ֵ��thread��getCurrentSession()�Ϳ��Եõ�һ�����̰߳󶨵ĻỰ��
		 * �����õ���session��ʲô�ص��ǣ�
		 * 		1.���û�оͻᴴ��һ���µĻỰ������о��õ�ǰ��session
		 * 		2.session���Զ��رգ���ô�Զ��ر��ǣ������װ��һ�����񻷾��У����������commit����rollback���� session�͹ر�
		 */
		 session = sf.getCurrentSession();//�Ƽ�ʹ��
		 //session = sf.openSession();
		 
		 tx = session.beginTransaction();//�Ự�رջ��������������棬����Ҫ��������
		 
		 Dept dept = new Dept();
		 dept.setDeptno((byte)10);
		 dept.setDname("�з���");
		 dept.setLoc("�����к�����");
		 session.save(dept);
		 
		 
		 tx.commit();
	 }catch(HibernateException e){
		 e.printStackTrace();
		 if(tx != null){
			 tx.rollback();
		 }
		 
	 }/*finally{
		 session.close();
	 }����������ĵõ�session�ķ�������Ҫ�ֶ��ر�session���������ڻ��Ƶõ��ķ��������Զ��ر�session,�����ֶ��ر�session*/
		
		
		
		
	}
}
