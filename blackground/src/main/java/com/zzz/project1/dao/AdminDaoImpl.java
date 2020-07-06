package com.zzz.project1.dao;

import com.alibaba.druid.util.StringUtils;
import com.zzz.project1.model.Admin;
import com.zzz.project1.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminDaoImpl implements AdminDao {

    @Override
    public Admin login(Admin admin) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Admin query = null;
        try {
            query = runner.query("select * from admin where email = ? and pwd = ?",
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
    public int addAdminss(Admin admin) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Admin query = null;
        int info = 0;
        try {
            query = runner.query("select * from admin where email = ?",
                    new BeanHandler<>(Admin.class),
                    admin.getEmail());
            if (query != null) {
                return info;
            }
            info = runner.execute("insert into admin (email,nickname,pwd) values (?,?,?)",
                    admin.getEmail(),
                    admin.getNickname(),
                    admin.getPwd());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return info;
    }

    @Override
    public int updataAdminss(Admin updataAdmin, String updataId) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Admin query = null;
        int info = 0;
        try {
            query = runner.query("select * from admin where email = ?",
                    new BeanHandler<>(Admin.class),
                    updataAdmin.getEmail());
            if (query != null) {
                return info;
            }

            info = runner.execute("update admin set email = ?,nickname = ?,pwd = ? where id = ?",
                    updataAdmin.getEmail(),
                    updataAdmin.getNickname(),
                    updataAdmin.getPwd(),
                    updataId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return info;
    }

    @Override
    public Admin getAdminsInfo(Admin admin) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Admin query = null;
        try {
            query = runner.query("select * from admin where id = ?",
                    new BeanHandler<>(Admin.class),
                    admin.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Admin> getSearchAdmins(Admin admin) {
        Map<String,Object> result = getDynamicSql(admin);
        String sql = (String) result.get("sql");
        List<String> params = (List<String>) result.get("params");

        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<Admin> admins = null;
        try {
            admins = runner.query(sql,new BeanListHandler<>(Admin.class),
                    params.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admins;
    }

    @Override
    public Admin confirmAdmin(Admin admin) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Admin query = null;
        try {
            query = runner.query("select * from admin where nickname = ? and pwd = ?",
                    new BeanHandler<>(Admin.class),
                    admin.getNickname(),
                    admin.getPwd());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    @Override
    public boolean changePwd(Admin admin) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        int info = 0;
        try {
            info = runner.update("update admin set pwd = ? where id = ?",
                    admin.getPwd(),
                    admin.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return info != 0;
    }

    private Map<String,Object> getDynamicSql(Admin admin) {
        String base = "select * from admin where 1 = 1";
        List<String> params = new ArrayList<>();
        if (!StringUtils.isEmpty(admin.getEmail())){
            base = base + " and email like ?";
            params.add("%" + admin.getEmail() + "%");
        }
        if (!StringUtils.isEmpty(admin.getNickname())){
            base = base + " and nickname like ?";
            params.add("%" + admin.getNickname() + "%");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("sql", base);
        map.put("params", params);

        return map;
    }

}
