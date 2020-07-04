package com.zzz.project1.dao;

import com.zzz.project1.model.Admin;

import java.util.List;

public interface AdminDao {
    Admin login(Admin admin);

    List<Admin> allAdmins();

    boolean deleteAdmins(String id);

    Admin changePwd(Admin admin);
}
