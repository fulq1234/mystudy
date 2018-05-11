package cn.houserent.service;

import cn.houserent.entity.HouseUser;

public interface UserBiz {
    public HouseUser findUser(String name, String password);
}
