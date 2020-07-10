package com.zzz.project1.controller.Admin;

import com.google.gson.Gson;
import com.zzz.project1.model.Result;
import com.zzz.project1.model.bo.OrderChangeBO;
import com.zzz.project1.model.bo.PageOrderBO;
import com.zzz.project1.model.vo.OrderByIdVO;
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
        if ("changeOrder".equals(action)) {
            changeOrder(request, response);
        }
    }

    private void changeOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        OrderChangeBO orderChangeBO = gson.fromJson(requestBody, OrderChangeBO.class);
        orderService.changeOrder(orderChangeBO);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    private void ordersByPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //校验前端参数
        String requestBody = HttpUtils.getRequestBody(request);
        PageOrderBO orderBO = gson.fromJson(requestBody, PageOrderBO.class);
        PageOrdersVO orders = orderService.ordersByPage(orderBO);
        response.getWriter().println(gson.toJson(Result.ok(orders)));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requstURI = request.getRequestURI();
        String action = requstURI.replace("/api/admin/order/","");
        if ("order".equals(action)) {
            order(request,response);
        }
        if ("deleteOrder".equals(action)) {
            deleteOrder(request,response);
        }
    }

    private void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        orderService.deleteOrder(id);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    /**
     * 根据订单id获取订单
     * @param request
     * @param response
     * @throws IOException
     */
    private void order(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        OrderByIdVO orderByIdVO = orderService.order(id);
        response.getWriter().println(gson.toJson(Result.ok(orderByIdVO)));
    }
}
