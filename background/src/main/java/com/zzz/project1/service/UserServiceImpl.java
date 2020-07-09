package com.zzz.project1.service;

import com.zzz.project1.dao.UserDao;
import com.zzz.project1.dao.UserDaoImpl;
import com.zzz.project1.model.User;
import com.zzz.project1.model.bo.SignupBO;
import com.zzz.project1.model.bo.UserLoginBO;
import com.zzz.project1.model.bo.UserUpdateDataBO;
import com.zzz.project1.model.bo.UserUpdatePwdBO;
import com.zzz.project1.model.vo.UserDataVO;
import com.zzz.project1.model.vo.UserLoginVO;

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

    @Override
    public int signup(SignupBO signupBO) {
        return userDao.signup(signupBO);
    }

    @Override
    public UserLoginVO login(UserLoginBO userLoginBO) {
        return userDao.login(userLoginBO);
    }

    @Override
    public UserDataVO data(String token) {
        return userDao.data(token);
    }

    @Override
    public void updataPwd(UserUpdatePwdBO userUpdatePwdBO) {
        userDao.updataPwd(userUpdatePwdBO);
    }

    @Override
    public int updataUserData(UserUpdateDataBO userUpdateDataBO) {
        return userDao.updataUserData(userUpdateDataBO);
    }
}
