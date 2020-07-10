package com.zzz.project1.controller.Mall;

import com.google.gson.*;
import com.zzz.project1.model.Result;
import com.zzz.project1.model.bo.CommentsBO;
import com.zzz.project1.model.bo.OrderAddBO;
import com.zzz.project1.model.bo.SettleAccountBO;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/api/mall/order/*")
public class OrderServlet extends HttpServlet {

    private OrderService orderService = new OrderServiceImpl();
    private Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requstURI = request.getRequestURI();
        String action = requstURI.replace("/api/mall/order/","");
        if ("settleAccounts".equals(action)) {
            settleAccounts(request,response);
        }
        if ("sendComment".equals(action)) {
            sendComment(request,response);
        }
        if ("addOrder".equals(action)) {
            addOrder(request,response);
        }
    }

    private void addOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        OrderAddBO orderAddBO = gson.fromJson(requestBody, OrderAddBO.class);
        orderService.addOrder(orderAddBO);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    private void sendComment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        CommentsBO commentsBO = gson.fromJson(requestBody,CommentsBO.class);
        orderService.sendComment(commentsBO);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    private void settleAccounts(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        //通过gson将内容转换成list
        JsonElement jsonElement = new JsonParser().parse(requestBody);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        List<SettleAccountBO> cartList = new ArrayList<>();
        JsonArray SettleAccountList = jsonObject.getAsJsonArray("cartList");
        for (JsonElement element : SettleAccountList) {
            SettleAccountBO cart = gson.fromJson(element,SettleAccountBO.class);
            cartList.add(cart);
        }
        orderService.settleAccounts(cartList);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/mall/order/", "");
        if ("getOrderByState".equals(action)) {
            getOrderByState(request, response);
        }
        if ("deleteOrder".equals(action)) {
            deleteOrder(request,response);
        }
        if ("pay".equals(action)) {
            pay(request,response);
        }
        if ("confirmReceive".equals(action)) {
            confirmReceive(request,response);
        }
    }

    private void confirmReceive(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        orderService.confirmReceive(id);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    private void pay(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        orderService.pay(id);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    private void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        orderService.deleteOrder(id);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    private void getOrderByState(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer state = Integer.parseInt(request.getParameter("state"));
        String token = request.getParameter("token");
        List<GetOrderByStateVO> getOrderByStateVO = orderService.getOrderByState(state,token);
        response.getWriter().println(gson.toJson(Result.ok(getOrderByStateVO)));
    }
}
