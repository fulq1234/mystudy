package cn.houserent.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.houserent.entity.HouseUser;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	public UserDaoImpl(){

	}

	@Autowired
	public UserDaoImpl(
			@Qualifier("sessionFactory") SessionFactory sessionFactory){
		this.setSessionFactory(sessionFactory);
	}


	@Override
	public HouseUser findUser(String name, String password) {
		// TODO Auto-generated method stub
		List<HouseUser> users = this.getHibernateTemplate().find("from HouseUser u where u.username=? and u.password=?",name,password);
		if(users.size() >0){
			return users.get(0);
		}else{
			return null;
		}
	}

	
	@Override
	public List<HouseUser> findUsers(final int page, final int size) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().execute(new HibernateCallback<List<HouseUser>>(){

			@Override
			public List<HouseUser> doInHibernate(Session session) throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				Query query = session.createQuery("from HouseUser");
				query.setFirstResult((page -1)*size);
				query.setMaxResults(size);
				return query.list();
			}
			
		});
	}

}
