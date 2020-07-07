package com.zzz.project1.dao;

import com.zzz.project1.model.User;
import com.zzz.project1.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public List<User> allUser() {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<User> query = null;
        try {
            query = runner.query("select * from user",
                    new BeanListHandler<>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    @Override
    public List<User> searchUser(String nickname) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<User> query = null;
        try {
            query = runner.query("select * from user where nickname like ?",
                    new BeanListHandler<>(User.class),
                    "%" + nickname + "%");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    @Override
    public void deleteUser(String id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("delete from user where id = ?",
                    Integer.parseInt(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
