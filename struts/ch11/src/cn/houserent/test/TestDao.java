package cn.houserent.test;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.houserent.dao.UserDao;
import cn.houserent.entity.HouseUser;
import cn.houserent.util.HibernateUtil;

public class TestDao {

	@Test
	public void test(){
		Transaction tx = HibernateUtil.getSession().beginTransaction();
		List<HouseUser> hu = HibernateUtil.getSession().createQuery("from HouseUser").list();
		for(HouseUser user : hu){
			System.out.println(user.getUsername());
		}
		tx.commit();
	}
	
	@Test
	public void testSpringSessionFactory(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		SessionFactory sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");
		
		Transaction tx = sessionFactory.openSession().beginTransaction();
		List<HouseUser> hu = sessionFactory.openSession().createQuery("from HouseUser").list();
		for(HouseUser user : hu){
			System.out.println(user.getUsername());
		}
		tx.commit();
	}
	
	@Test
	public void testUserDao(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		UserDao userDao = (UserDao) ctx.getBean("userDao");
		HouseUser user = userDao.findUser("2", "1");
		if(user != null){
			System.out.println("用户存在");
		}else{
			System.out.println("用户不存在");
		}
	}
	
	@Test
	public void testUserDao1(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserDao userDao = (UserDao) ctx.getBean("userDao");
		
		List<HouseUser> users = userDao.findUsers(1, 2);
		for(HouseUser user : users){
			System.out.println(user.getUsername());
		}
	}
}
