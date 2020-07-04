package com.zzz.project1.service;

import com.zzz.project1.model.Admin;

import com.zzz.project1.model.bo.AdminAddBO;
import com.zzz.project1.model.bo.AdminLoginBO;
import com.zzz.project1.model.bo.AdminSearchBO;

import java.util.List;

public interface AdminService {
    Admin login(AdminLoginBO loginBO);

    List<Admin> allAdmins();

    boolean deleteAdmins(String id);

    int addAdminInfo(AdminAddBO updataInfoBO);

    int updataAdminInfo(AdminAddBO updataInfoBO, String updataId);

    Admin getAdminsInfo(String id);

    List<Admin> getSearchAdmins(AdminSearchBO searchBO);
}
