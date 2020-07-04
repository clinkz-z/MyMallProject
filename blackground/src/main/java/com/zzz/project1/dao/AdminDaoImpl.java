package com.zzz.project1.dao;

import com.zzz.project1.model.Admin;
import com.zzz.project1.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class AdminDaoImpl implements AdminDao {

    @Override
    public Admin login(Admin admin) {
        QueryRunner runnner = new QueryRunner(DruidUtils.getDataSource());
        Admin query = null;
        try {
            query = runnner.query("select * from admin where email = ? and pwd = ?",
                    new BeanHandler<>(Admin.class),
                    admin.getEmail(),
                    admin.getPwd());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    @Override
    public List<Admin> allAdmins() {
        QueryRunner runnner = new QueryRunner(DruidUtils.getDataSource());
        List<Admin> admins = null;
        try {
            admins = runnner.query("select * from admin",
                    new BeanListHandler<Admin>(Admin.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }

    @Override
    public boolean deleteAdmins(String id) {
        QueryRunner runnner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runnner.update("delete from admin where id = ?", Integer.parseInt(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Admin changePwd(Admin admin) {
        return null;
    }

}
