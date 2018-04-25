package org.hibernate.demo.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.demo.entity.Dept;
import org.junit.Test;

public class HibTest {
	
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
