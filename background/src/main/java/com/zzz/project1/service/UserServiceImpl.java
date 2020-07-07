package com.zzz.project1.service;

import com.zzz.project1.dao.UserDao;
import com.zzz.project1.dao.UserDaoImpl;
import com.zzz.project1.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    UserDao userDao = new UserDaoImpl();

    @Override
    public List<User> allUser() {
        return userDao.allUser();
    }

    @Override
    public List<User> searchUser(String nickname) {
        return userDao.searchUser(nickname);
    }

    @Override
    public void deleteUser(String id) {
        userDao.deleteUser(id);
    }
}
