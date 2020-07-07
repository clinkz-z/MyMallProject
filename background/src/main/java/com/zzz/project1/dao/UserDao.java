package com.zzz.project1.dao;

import com.zzz.project1.model.User;

import java.util.List;

public interface UserDao {
    List<User> allUser();

    List<User> searchUser(String nickname);

    void deleteUser(String id);
}
