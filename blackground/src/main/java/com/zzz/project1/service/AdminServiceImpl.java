package com.zzz.project1.service;

import com.zzz.project1.dao.AdminDao;
import com.zzz.project1.dao.AdminDaoImpl;
import com.zzz.project1.model.Admin;
import com.zzz.project1.model.bo.AdminAddBO;
import com.zzz.project1.model.bo.AdminLoginBO;
import com.zzz.project1.model.bo.AdminSearchBO;

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
    public int addAdminInfo(AdminAddBO updataInfoBO) {
        Admin admin = new Admin();
        admin.setNickname(updataInfoBO.getNickname());
        admin.setPwd(updataInfoBO.getPwd());
        admin.setEmail(updataInfoBO.getEmail());

        return adminDao.addAdminss(admin);
    }

    @Override
    public int updataAdminInfo(AdminAddBO updataInfoBO, String updataId) {
        Admin updataAdmin = new Admin();
        updataAdmin.setNickname(updataInfoBO.getNickname());
        updataAdmin.setPwd(updataInfoBO.getPwd());
        updataAdmin.setEmail(updataInfoBO.getEmail());
        return adminDao.updataAdminss(updataAdmin,updataId);
    }

    @Override
    public Admin getAdminsInfo(String id) {
        Admin admin = new Admin();
        admin.setId(Integer.parseInt(id));
        return adminDao.getAdminsInfo(admin);
    }

    @Override
    public List<Admin> getSearchAdmins(AdminSearchBO searchBO) {
        Admin admin = new Admin();
        admin.setEmail(searchBO.getEmail());
        admin.setNickname(searchBO.getNickname());
        return adminDao.getSearchAdmins(admin);
    }
}
