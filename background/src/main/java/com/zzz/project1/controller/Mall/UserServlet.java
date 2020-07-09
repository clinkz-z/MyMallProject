package com.zzz.project1.controller.Mall;

import com.google.gson.Gson;
import com.zzz.project1.model.Result;
import com.zzz.project1.model.bo.SignupBO;
import com.zzz.project1.model.bo.UserLoginBO;
import com.zzz.project1.model.bo.UserUpdateDataBO;
import com.zzz.project1.model.bo.UserUpdatePwdBO;
import com.zzz.project1.model.vo.UserDataVO;
import com.zzz.project1.model.vo.UserLoginVO;
import com.zzz.project1.service.UserService;
import com.zzz.project1.service.UserServiceImpl;
import com.zzz.project1.utils.HttpUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/mall/user/*")
public class UserServlet extends HttpServlet {

    private Gson gson = new Gson();
    private UserService userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requstURI = request.getRequestURI();
        String action = requstURI.replace("/api/mall/user/","");
        if ("signup".equals(action)){
            signup(request,response);
        }
        if ("login".equals(action)) {
            login(request,response);
        }
        if ("updatePwd".equals(action)) {
            updatePwd(request,response);
        }
        if ("updateUserData".equals(action)) {
            updateUserData(request,response);
        }
    }

    private void updateUserData(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requstBody = HttpUtils.getRequstBody(request);
        UserUpdateDataBO userUpdateDataBO = gson.fromJson(requstBody, UserUpdateDataBO.class);
        int code = userService.updataUserData(userUpdateDataBO);
        if (code != 0) {
            UserLoginVO token = new UserLoginVO();
            token.setToken(userUpdateDataBO.getNickname());
            response.getWriter().println(gson.toJson(Result.ok(token)));
        } else {
            response.getWriter().println(gson.toJson(Result.error("修改失败")));
        }
    }

    private void updatePwd(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requstBody = HttpUtils.getRequstBody(request);
        UserUpdatePwdBO userUpdatePwdBO = gson.fromJson(requstBody,UserUpdatePwdBO.class);
        String newPwd = userUpdatePwdBO.getNewPwd();
        String confirmPwd = userUpdatePwdBO.getConfirmPwd();
        if ( !newPwd.equals(confirmPwd)) {
            response.getWriter().println(Result.error("两次密码不一致"));
            return;
        }
        userService.updataPwd(userUpdatePwdBO);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requstBody = HttpUtils.getRequstBody(request);
        UserLoginBO userLoginBO = gson.fromJson(requstBody, UserLoginBO.class);
        UserLoginVO loginVO = userService.login(userLoginBO);
        if (loginVO != null) {
            loginVO.setCode(0);
            loginVO.setToken(loginVO.getName());
            response.getWriter().println(gson.toJson(Result.ok(loginVO)));
        } else {
            response.getWriter().println(gson.toJson(Result.error("登录失败")));
        }
    }

    private void signup(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requstBody = HttpUtils.getRequstBody(request);
        SignupBO signupBO = gson.fromJson(requstBody, SignupBO.class);
        int code = userService.signup(signupBO);
        if (code != 0) {
            UserLoginVO loginVO = new UserLoginVO();
            loginVO.setName(signupBO.getNickname());
            loginVO.setToken(signupBO.getNickname());
            response.getWriter().println(gson.toJson(Result.ok(loginVO)));
        } else {
            response.getWriter().println(gson.toJson(Result.error("注册失败,用户名重复")));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requstURI = request.getRequestURI();
        String action = requstURI.replace("/api/mall/user/","");
        if ("data".equals(action)){
            data(request,response);
        }

    }

    private void data(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String token = request.getParameter("token");
        UserDataVO userDataVO = userService.data(token);
        if (userDataVO != null) {
            userDataVO.setCode(0);
            response.getWriter().println(gson.toJson(Result.ok(userDataVO)));
        } else {
            response.getWriter().println(gson.toJson(Result.error("error")));
        }
    }
}
