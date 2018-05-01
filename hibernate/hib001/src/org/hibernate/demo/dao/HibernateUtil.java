package org.hibernate.demo.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static Configuration conf;
	private final static SessionFactory sf;
	
	static{
		try{
			conf = new Configuration().configure();
			sf = conf.buildSessionFactory();
		}catch(HibernateException e){
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);//抛出自定义异常
		}
	
				
	}
	
	private HibernateUtil(){}
	/**
	 * 获得会话
	 * @return
	 */
	public static Session currentSession(){
		return sf.getCurrentSession();
	}
}
