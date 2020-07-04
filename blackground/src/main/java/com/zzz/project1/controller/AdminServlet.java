package com.zzz.project1.controller;

import com.google.gson.Gson;
import com.zzz.project1.model.Admin;
import com.zzz.project1.model.Result;
import com.zzz.project1.model.bo.AdminChangePwdBO;
import com.zzz.project1.model.bo.AdminLoginBO;
import com.zzz.project1.model.vo.AdminLoginVO;
import com.zzz.project1.service.AdminService;
import com.zzz.project1.service.AdminServiceImpl;
import com.zzz.project1.utils.HttpUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/admin/admin/*")
public class AdminServlet extends HttpServlet {

    private AdminService adminService = new AdminServiceImpl();
    Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requstURI = request.getRequestURI();
        String action = requstURI.replace("/api/admin/admin/","");
        if ("login".equals(action)){
            login(request,response);
        }
        if ("changePwd".equals(action)){
            changePwd(request,response);
        }

    }

    /**
     * 修改密码
     * @param request
     * @param response
     */
    private void changePwd(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requstBody = HttpUtils.getRequstBody(request);
        AdminChangePwdBO changePwdBO = gson.fromJson(requstBody, AdminChangePwdBO.class);
        System.out.println(changePwdBO);
        if (!changePwdBO.getConfirmPwd().equals(changePwdBO.getNewPwd())){
            response.getWriter().println(gson.toJson(Result.error("两次确认密码不正确")));
            return;
        }
        Admin changePwd = adminService.changePwd(changePwdBO);
    }

    /**
     * 登录请求
     * @param request
     * @param response
     */
    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String requstBody = HttpUtils.getRequstBody(request);
        AdminLoginBO loginBO = gson.fromJson(requstBody, AdminLoginBO.class);
        System.out.println(loginBO);

        Admin login = adminService.login(loginBO);
        if (login != null){
            AdminLoginVO loginVO = new AdminLoginVO();
            loginVO.setToken(login.getNickname());
            loginVO.setName(login.getNickname());
            response.getWriter().println(gson.toJson(Result.ok(loginVO)));
        }else {
            response.getWriter().println(gson.toJson(Result.error("用户名或者密码错误")));
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requstURI = request.getRequestURI();
        String action = requstURI.replace("/api/admin/admin/","");
        if ("allAdmins".equals(action)){
            allAdmins(request,response);
        }
        if ("deleteAdmins".equals(action)) {
            deleteAdmins(request,response);
        }
    }

    /**
     * 删除所选admin账号的信息
     * @param request
     * @param response
     */
    private void deleteAdmins(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");

        boolean delete = adminService.deleteAdmins(id);
        response.getWriter().println(gson.toJson(Result.ok("删除成功")));
    }

    /**
     * 显示所有的admin账号信息
     * 1.查询数据库
     * 2.做出响应
     * @param request
     * @param response
     */
    private void allAdmins(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Admin> adminList = adminService.allAdmins();
        Result result = new Result();
        result.setCode(0);
        result.setData(adminList);
        response.getWriter().println(gson.toJson(result));
    }
}
