package com.zzz.project1.service;

import com.zzz.project1.model.User;

import java.util.List;

public interface UserService {
    List<User> allUser();

    List<User> searchUser(String nickname);

    void deleteUser(String id);
}
