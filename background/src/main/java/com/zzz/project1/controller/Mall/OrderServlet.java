package com.zzz.project1.controller.Mall;

import com.google.gson.Gson;
import com.zzz.project1.model.Result;
import com.zzz.project1.model.bo.GetOrderByStateBO;
import com.zzz.project1.model.vo.GetOrderByStateVO;
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

@WebServlet("/api/mall/order/*")
public class OrderServlet extends HttpServlet {

    private OrderService orderService = new OrderServiceImpl();
    private Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requstURI = request.getRequestURI();
        String action = requstURI.replace("/api/mall/order/","");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/mall/order/", "");
        if ("getOrderByState".equals(action)) {
            getOrderByState(request, response);
        }
    }

    private void getOrderByState(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer state = Integer.parseInt(request.getParameter("state"));
        String token = request.getParameter("token");
        List<GetOrderByStateVO> getOrderByStateVO = orderService.getOrderByState(state,token);
        response.getWriter().println(gson.toJson(Result.ok(getOrderByStateVO)));
    }
}
