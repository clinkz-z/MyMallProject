package com.zzz.project1.service;

import com.zzz.project1.model.User;
import com.zzz.project1.model.bo.SignupBO;
import com.zzz.project1.model.bo.UserLoginBO;
import com.zzz.project1.model.bo.UserUpdateDataBO;
import com.zzz.project1.model.bo.UserUpdatePwdBO;
import com.zzz.project1.model.vo.UserDataVO;
import com.zzz.project1.model.vo.UserLoginVO;

import java.util.List;

public interface UserService {
    List<User> allUser();

    List<User> searchUser(String nickname);

    void deleteUser(String id);

    int signup(SignupBO signupBO);

    UserLoginVO login(UserLoginBO userLoginBO);

    UserDataVO data(String token);

    void updataPwd(UserUpdatePwdBO userUpdatePwdBO);

    int updataUserData(UserUpdateDataBO userUpdateDataBO);
}
