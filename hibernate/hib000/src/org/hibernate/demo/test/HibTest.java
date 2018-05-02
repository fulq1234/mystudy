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
 * Junit测试类
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
	 * 使用map当查询条件
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
	 * 测试查询类
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
	 * saveOrUpdate游离态执行update方法。
	 */
	@Test
	public void testSaveUpdate2(){
		Emp emp = new Emp();
		emp.setEmpNo(1);//这个给了个主键值，看着是和数据库中一个对象对应，其实session中没有它，这个时候调用saveOrUpdate时，执行update
		emp.setEmpName("new Emp2");
		
		new EmpBiz().saveOrUpdate(emp);
		
	}
	
	/**
	 * 对应临时对象，saveorUpdate方法执行保存操作
	 * 执行前会出现一个select语句，是因为主键增长方式是increment，这个是从数据库中取出最大值,最大值+1就是所要的数据。
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
		dept.setDname("新名称");
		dept.setLoc("广新1");
		
		new DeptBiz().update(dept);
	}
	
	/**
	 * 测试更新
	 */
	@Test
	public void testUpdate(){
		Dept dept = new Dept();
		dept.setDeptno((byte)11);
		dept.setDname("新名称");
		dept.setLoc("广新2");
		
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
		/**查不出来值，因为是默认的延迟加载。 
		* 想要得到值，
		* 1.可以在class上设置属性lazy="false"
		* 2.在会话关闭之前输出值
		**/
		System.out.println(result.getDeptno());
		System.out.println(result.getDname());
		
	}
	/**
	 * 测试新增方法
	 */
	@Test
	public void testAddNew(){
		Dept dept = new Dept();
		dept.setDeptno((byte)16);
		dept.setDname("招生部");
		dept.setLoc("北京市海淀区14");
		
		new DeptBiz().addNewDept(dept);
	}
	
	
	@Test
	public void firstShow(){
		Configuration conf = null;//加载hibernate的配置文件
		SessionFactory sf = null;//创建会话,维护了Hibernate运行中的重要信息
		Session session = null;//是普遍的概念，和数据库做一次沟通，就叫会话。Session实施持久化操作关键API
		Transaction tx = null;//事务控制
		
//		conf = new Configuration();//得到Configuration实例
//		conf.configure();//加载配置文件,现在hibernate.cfg.xml在默认位置下面，所以就用无参就行。返回值还是Configuration，这就形成了一个链。可以合并成一个
	 try{
		 conf= new Configuration().configure();
		 sf = conf.buildSessionFactory();//创建工厂
		 
		 /*为什么推荐使用getCurrentSession  1.会话管理，2.保证使用的是同一个session
		 * openSesion 每次打开都创建一个新的session
		 * getCurrentSesion 什么叫当前会话,在hibernate.cfg.xml中有个设置current_session_context_class，值是thread，getCurrentSession()就可以得到一个和线程绑定的会话。
		 * 这样得到的session有什么特点那？
		 * 		1.如果没有就会创建一个新的会话，如果有就用当前的session
		 * 		2.session会自动关闭，怎么自动关闭那？它会包装在一个事务环境中，事务结束（commit或者rollback）， session就关闭
		 */
		 session = sf.getCurrentSession();//推荐使用
		 //session = sf.openSession();
		 
		 tx = session.beginTransaction();//会话关闭机制是在事务里面，所以要开启事务
		 
		 Dept dept = new Dept();
		 dept.setDeptno((byte)10);
		 dept.setDname("研发部");
		 dept.setLoc("北京市海淀区");
		 session.save(dept);
		 
		 
		 tx.commit();
	 }catch(HibernateException e){
		 e.printStackTrace();
		 if(tx != null){
			 tx.rollback();
		 }
		 
	 }/*finally{
		 session.close();
	 }如果是其它的得到session的方法，需要手动关闭session，但是现在机制得到的方法，会自动关闭session,不必手动关闭session*/
		
		
		
		
	}
}
