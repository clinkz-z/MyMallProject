package com.zzz.project1.controller;

import com.google.gson.Gson;
import com.zzz.project1.model.Result;
import com.zzz.project1.model.User;
import com.zzz.project1.service.UserService;
import com.zzz.project1.service.UserServiceImpl;
import com.zzz.project1.utils.HttpUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/admin/user/*")
public class UserServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();
    private Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requstURI = request.getRequestURI();
        String action = requstURI.replace("/api/admin/user/","");
        if ("allUser".equals(action)){
            allUser(request,response);
        }
        if ("searchUser".equals(action)){
            searchUser(request,response);
        }
        if ("deleteUser".equals(action)){
            deleteUser(request,response);
        }
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        userService.deleteUser(id);
        response.getWriter().println(gson.toJson(Result.ok(id)));
    }

    private void searchUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nickname = request.getParameter("word");
        List<User> users = userService.searchUser(nickname);
        response.getWriter().println(gson.toJson(Result.ok(users)));
    }

    private void allUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<User> userList = userService.allUser();
        response.getWriter().println(gson.toJson(Result.ok(userList)));
    }
}
