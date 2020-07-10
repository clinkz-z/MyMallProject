package com.zzz.project1.controller.Admin;

import com.google.gson.Gson;
import com.zzz.project1.model.Result;
import com.zzz.project1.model.Type;
import com.zzz.project1.model.bo.*;
import com.zzz.project1.model.vo.GoodsTypeVO;
import com.zzz.project1.model.vo.MsgNoReplyVO;
import com.zzz.project1.model.vo.MsgReplyVO;
import com.zzz.project1.service.GoodsService;
import com.zzz.project1.service.GoodsServiceImpl;
import com.zzz.project1.utils.FileUploadUtils;
import com.zzz.project1.utils.HttpUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/api/admin/goods/*")
public class GoodsServlet extends HttpServlet {

    private GoodsService goodsService = new GoodsServiceImpl();
    private Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requstURI = request.getRequestURI();
        String action = requstURI.replace("/api/admin/goods/","");
        if ("addType".equals(action)) {
            addType(request, response);
        }
        if ("imgUpload".equals(action)) {
            imgUpload(request, response);
        }
        if ("addGoods".equals(action)) {
            addGoods(request, response);
        }
        if ("addSpec".equals(action)) {
            addSpec(request, response);
        }
        if ("deleteSpec".equals(action)) {
            deleteSpec(request, response);
        }
        if ("updateGoods".equals(action)) {
            updateGoods(request, response);
        }
        if ("reply".equals(action)) {
            reply(request,response);
        }
    }

    private void reply(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requstBody = HttpUtils.getRequestBody(request);
        ReplyBO replyBO = gson.fromJson(requstBody, ReplyBO.class);
        goodsService.reply(replyBO);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    private void updateGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        GoodsUpdataBO updataBO = gson.fromJson(requestBody, GoodsUpdataBO.class);

        goodsService.updataGoods(updataBO);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    private void deleteSpec(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        GoodsDeleteSpecBO deleteSpecBO = gson.fromJson(requestBody, GoodsDeleteSpecBO.class);
        goodsService.deleteSpec(deleteSpecBO);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    private void addSpec(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        GoodsAddSpecBO addSpecBO = gson.fromJson(requestBody, GoodsAddSpecBO.class);
        boolean addSpec =  goodsService.addSpec(addSpecBO);
        if (addSpec) {
            response.getWriter().println(gson.toJson(Result.ok(addSpecBO)));
        } else {
            response.getWriter().println(gson.toJson(Result.error("重复规格名称")));
        }

    }

    private void addGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        GoodsUpdataBO updataBO = gson.fromJson(requestBody, GoodsUpdataBO.class);

        goodsService.addGoods(updataBO);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    /**
     * 新增商品图片
     * common-fileupload
     * 1. 执行具体的业务逻辑，上传图片
     * 2. 响应--抓服务器上的响应报文
     * @param request
     * @param response
     */
    private void imgUpload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> map = FileUploadUtils.parseRequest(request);
        String file = (String) map.get("file");
        //这里面有一点需要注意
        //服务器上的路径没有携带域名端口号，那么就表示图片就从当前域名端口去取
        //如果图片和页面不在一个域，那么应当指明文件所在域
        //应当写个全路径 http://localhost:8084
        String domain = (String) getServletContext().getAttribute("domain");
        response.getWriter().println(gson.toJson(Result.ok(domain + file)));
    }

    private void addType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        GoodsAddTypeBO addTypeBO = gson.fromJson(requestBody, GoodsAddTypeBO.class);

        Boolean addType = goodsService.addType(addTypeBO);
        if (!addType) {
            response.getWriter().println(Result.error("类目名已存在"));
        } else {
            response.getWriter().println(Result.okMsg("添加成功"));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requstURI = request.getRequestURI();
        String action = requstURI.replace("/api/admin/goods/","");
        if ("getType".equals(action)) {
            getType(request,response);
        }
        if ("getGoodsByType".equals(action)) {
            getGoodsByType(request,response);
        }
        if ("getGoodsInfo".equals(action)) {
            getGoodsInfo(request,response);
        }
        if ("deleteGoods".equals(action)) {
            deleteGoods(request,response);
        }
        if ("noReplyMsg".equals(action)) {
            noReplyMsg(request,response);
        }
        if ("repliedMsg".equals(action)) {
            repliedMsg(request,response);
        }
    }

    private void repliedMsg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<MsgReplyVO> mesReplyVO = goodsService.repliedMsg();
        response.getWriter().println(gson.toJson(Result.ok(mesReplyVO)));
    }

    private void noReplyMsg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<MsgNoReplyVO> msgNoReply = goodsService.noReplyMsg();
        response.getWriter().println(gson.toJson(Result.ok(msgNoReply)));
    }

    private void deleteGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        goodsService.deleteGoods(id);
        response.getWriter().println(gson.toJson(Result.ok()));
    }

    private void getGoodsInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        Map<String, Object> getInfoVO = goodsService.getGoodsInfo(id);
        response.getWriter().println(gson.toJson(Result.ok(getInfoVO)));
    }

    private void getGoodsByType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String typeId = request.getParameter("typeId");
        List<GoodsTypeVO> goodsTypeVO = goodsService.goodsByType(typeId);
        response.getWriter().println(gson.toJson(Result.ok(goodsTypeVO)));
    }

    private void getType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Type> typeList = goodsService.getType();
        response.getWriter().println(gson.toJson(Result.ok(typeList)));
    }
}
