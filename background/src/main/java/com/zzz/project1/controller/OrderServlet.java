package com.zzz.project1.controller;

import com.google.gson.Gson;
import com.zzz.project1.model.Result;
import com.zzz.project1.model.bo.PageOrderBO;
import com.zzz.project1.model.vo.PageOrdersVO;
import com.zzz.project1.service.OrderService;
import com.zzz.project1.service.OrderServiceImpl;
import com.zzz.project1.utils.HttpUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/admin/order/*")
public class OrderServlet extends HttpServlet {

    private OrderService orderService = new OrderServiceImpl();
    private Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requstURI = request.getRequestURI();
        String action = requstURI.replace("/api/admin/order/","");
        if ("ordersByPage".equals(action)) {
            ordersByPage(request,response);
        }
    }

    private void ordersByPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //校验前端参数
        String requestBody = HttpUtils.getRequstBody(request);
        PageOrderBO orderBO = gson.fromJson(requestBody, PageOrderBO.class);
        PageOrdersVO orders = orderService.ordersByPage(orderBO);
        response.getWriter().println(gson.toJson(Result.ok(orders)));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
