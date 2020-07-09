package com.zzz.project1.dao;

import com.zzz.project1.model.User;
import com.zzz.project1.model.bo.SignupBO;
import com.zzz.project1.model.bo.UserLoginBO;
import com.zzz.project1.model.bo.UserUpdateDataBO;
import com.zzz.project1.model.bo.UserUpdatePwdBO;
import com.zzz.project1.model.vo.UserDataVO;
import com.zzz.project1.model.vo.UserLoginVO;
import com.zzz.project1.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
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

    @Override
    public int signup(SignupBO signupBO) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        int code = 0;
        try {
            code = runner.update("insert into user values (null,?,?,?,?,?,?)",
                    signupBO.getEmail(),
                    signupBO.getNickname(),
                    signupBO.getPwd(),
                    signupBO.getRecipient(),
                    signupBO.getAddress(),
                    signupBO.getPhone());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return code;
    }

    @Override
    public UserLoginVO login(UserLoginBO userLoginBO) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        UserLoginVO query = null;
        try {
            query = runner.query("select nickname as name from user where email = ? and pwd = ?",
                    new BeanHandler<>(UserLoginVO.class),
                    userLoginBO.getEmail(),
                    userLoginBO.getPwd());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    @Override
    public UserDataVO data(String token) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        UserDataVO query = null;
        try {
            query = runner.query("select * from user where nickname = ?",
                    new BeanHandler<>(UserDataVO.class),
                    token);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    @Override
    public void updataPwd(UserUpdatePwdBO userUpdatePwdBO) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("update user set pwd = ? where id = ?",
                    userUpdatePwdBO.getNewPwd(),
                    userUpdatePwdBO.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int updataUserData(UserUpdateDataBO userUpdateDataBO) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        int code = 0;
        try {
            code = runner.update("update user set address = ?, nickname = ?, phone = ?, recipient = ? where id = ?",
                    userUpdateDataBO.getAddress(),
                    userUpdateDataBO.getNickname(),
                    userUpdateDataBO.getPhone(),
                    userUpdateDataBO.getRecipient(),
                    userUpdateDataBO.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return code;
    }
}
