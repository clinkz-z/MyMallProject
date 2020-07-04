package com.zzz.project1.service;

import com.zzz.project1.model.Admin;

import com.zzz.project1.model.bo.AdminChangePwdBO;
import com.zzz.project1.model.bo.AdminLoginBO;

import java.util.List;

public interface AdminService {
    Admin login(AdminLoginBO loginBO);

    List<Admin> allAdmins();

    boolean deleteAdmins(String id);

    Admin changePwd(AdminChangePwdBO changePwdBO);
}
