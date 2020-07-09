package com.zzz.project1.controller.Mall;

import com.google.gson.Gson;
import com.zzz.project1.model.Result;
import com.zzz.project1.model.Type;
import com.zzz.project1.model.vo.GoodsTypeVO;
import com.zzz.project1.service.GoodsService;
import com.zzz.project1.service.GoodsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/mall/index/*")
public class IndexServlet extends HttpServlet {

    private GoodsService goodsService = new GoodsServiceImpl();
    private Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requstURI = request.getRequestURI();
        String action = requstURI.replace("/api/mall/index/","");
        if ("".equals(action)){

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requstURI = request.getRequestURI();
        String action = requstURI.replace("/api/mall/index/","");
        if ("getType".equals(action)){
            getType(request,response);
        }
    }

    private void getType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Type> typeList = goodsService.getType();
        response.getWriter().println(gson.toJson(Result.ok(typeList)));
    }
}
