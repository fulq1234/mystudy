package cn.houserent.service.impl;

import cn.houserent.dao.UserDao;
import cn.houserent.entity.HouseUser;
import cn.houserent.service.UserBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserBizImpl implements UserBiz {

    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    @Override
    public HouseUser findUser(String name, String password) {
        return userDao.findUser(name,password);
    }
}
