package com.zzz.project1.filter;

import com.google.gson.Gson;
import com.zzz.project1.model.Result;
import com.zzz.project1.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/api/mall/*")
public class MallFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        response.setContentType("text/html;charset=utf-8");
        //将*改为页面所在的域名
        response.setHeader("Access-Control-Allow-Origin","http://localhost:8085");
        response.setHeader("Access-Control-Allow-Methods","POST,GET,OPTIONS,PUT,DELETE");
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,Authorization,Content-Type");
        response.setHeader("Access-Control-Allow-Credentials","true");

        String requestURI = request.getRequestURI();
        //判断哪些URI需要拦截
//        if(!request.getMethod().equals("OPTIONS")){
//            if(auth(requestURI)){
//                User user = (User) request.getSession().getAttribute("user");
//                if(user == null){
//                    response.getWriter().println(new Gson().toJson(Result.error("该借口需要登陆后访问！！！")));
//                    return;
//                }
//            }
//        }
        chain.doFilter(req, resp);
    }

    private boolean auth(String requestURI) {
        String requestURI1 = requestURI.replace("/api/mall/","");
        if("/api/mall/user/login".equals(requestURI) || "/api/mall/user/logoutAdmin".equals(requestURI) || requestURI1.startsWith("goods") || requestURI1.startsWith("index")){
            return false;
        }
        return true;
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
