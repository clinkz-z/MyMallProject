package com.zzz.project1.service;

import com.zzz.project1.dao.AdminDao;
import com.zzz.project1.dao.AdminDaoImpl;
import com.zzz.project1.model.Admin;
import com.zzz.project1.model.bo.AdminChangePwdBO;
import com.zzz.project1.model.bo.AdminLoginBO;
import com.zzz.project1.model.bo.AdminUpdataInfoBO;

import java.util.List;

public class AdminServiceImpl implements AdminService {

    private AdminDao adminDao = new AdminDaoImpl();

    @Override
    public Admin login(AdminLoginBO loginBO) {
        Admin admin = new Admin();
        admin.setEmail(loginBO.getEmail());
        admin.setPwd(loginBO.getPwd());

        return adminDao.login(admin);
    }

    @Override
    public List<Admin> allAdmins() {
        return adminDao.allAdmins();
    }

    @Override
    public boolean deleteAdmins(String id) {
        return adminDao.deleteAdmins(id);
    }

    @Override
    public Admin changePwd(AdminChangePwdBO changePwdBO) {
        Admin admin = new Admin();
        admin.setNickname(changePwdBO.getAdminToken());
        admin.setPwd(changePwdBO.getOldPwd());
        if(adminDao.login(admin) != null){

        }
        adminDao.changePwd(admin);
        return null;
    }

    @Override
    public int addAdminInfo(AdminUpdataInfoBO updataInfoBO) {
        Admin admin = new Admin();
        admin.setNickname(updataInfoBO.getNickname());
        admin.setPwd(updataInfoBO.getPwd());
        admin.setEmail(updataInfoBO.getEmail());

        return adminDao.addAdminss(admin);
    }

    @Override
    public int updataAdminInfo(AdminUpdataInfoBO updataInfoBO) {
        Admin admin = new Admin();
        admin.setNickname(updataInfoBO.getNickname());
        admin.setPwd(updataInfoBO.getPwd());
        admin.setEmail(updataInfoBO.getEmail());
        admin.setId(updataInfoBO.getId());
        return adminDao.updataAdminss(admin);
    }
}
