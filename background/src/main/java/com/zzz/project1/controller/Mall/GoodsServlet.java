package com.zzz.project1.controller.Mall;

import com.google.gson.Gson;
import com.zzz.project1.model.Result;
import com.zzz.project1.model.vo.GoodsGetInfoVO;
import com.zzz.project1.model.vo.GoodsTypeVO;
import com.zzz.project1.model.vo.SearchGoodsVO;
import com.zzz.project1.model.vo.SpecVO;
import com.zzz.project1.service.GoodsService;
import com.zzz.project1.service.GoodsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/api/mall/goods/*")
public class GoodsServlet extends HttpServlet {

    private GoodsService goodsService = new GoodsServiceImpl();
    private Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requstURI = request.getRequestURI();
        String action = requstURI.replace("/api/mall/goods/","");
        if ("getGoodsByType".equals(action)){
            getGoodsByType(request,response);
        }
        if ("getGoodsInfo".equals(action)){
            getGoodsInfo(request,response);
        }
        if ("searchGoods".equals(action)) {
            searchGoods(request,response);
        }
//        if ("getGoodsComment".equals(action)) {
//            getGoodsComment(request,response);
//        }

    }

    private void searchGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String keyword = request.getParameter("keyword");
        List<SearchGoodsVO> searchGoodsList = goodsService.searchGoods(keyword);
        response.getWriter().println(gson.toJson(Result.ok(searchGoodsList)));
    }


    @SuppressWarnings("unchecked")
    private void getGoodsInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        Map<String, Object> getInfo = goodsService.getGoodsInfo(id);
        GoodsGetInfoVO getInfoVO = (GoodsGetInfoVO) getInfo.get("goods");
        List<SpecVO> specs = (List<SpecVO>) getInfo.get("specs");
        getInfoVO.setSpecs(specs);
        response.getWriter().println(gson.toJson(Result.ok(getInfoVO)));
    }

    private void getGoodsByType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String typeId = request.getParameter("typeId");
        List<GoodsTypeVO> goodsTypeVO = goodsService.goodsByType(typeId);
        response.getWriter().println(gson.toJson(Result.ok(goodsTypeVO)));
    }
}
