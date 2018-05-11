package cn.houserent.dao;

import java.util.List;

import cn.houserent.entity.HouseUser;

public interface UserDao {

	public HouseUser findUser(String name,String password);
	
	public List<HouseUser> findUsers(int page,int limit);	
}
