package com.zzz.project1.service;

import com.zzz.project1.dao.GoodsDao;
import com.zzz.project1.dao.GoodsDaoImpl;
import com.zzz.project1.model.Goods;
import com.zzz.project1.model.Spec;
import com.zzz.project1.model.Type;
import com.zzz.project1.model.bo.*;
import com.zzz.project1.model.vo.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GoodsServiceImpl implements GoodsService{

    private GoodsDao goodsDao = new GoodsDaoImpl();

    @Override
    public List<Type> getType() {
        return goodsDao.getType();
    }

    @Override
    public Boolean addType(GoodsAddTypeBO addTypeBO) {
        Type type = new Type();
        type.setName(addTypeBO.getName());
        return !goodsDao.searchType(type) && goodsDao.addType(type);
    }

    @Override
    public List<GoodsTypeVO> goodsByType(String typeId) {
        return goodsDao.getGoodsByType(typeId);
    }

    @Override
    public void addGoods(GoodsUpdataBO updataBO) {
        List<SpecBO> specList = updataBO.getSpecList();
        Goods goods = getGoods(updataBO);
        goodsDao.addGoods(goods);
        int id = goodsDao.lastInsertId();
        List<Spec> specs = new ArrayList<>();
        for (SpecBO specBO : specList) {
            Spec spec = new Spec(null,specBO.getSpecName(),specBO.getStockNum(),specBO.getUnitPrice(),id);
            specs.add(spec);
        }
        goodsDao.addSpecs(specs);
    }

    @Override
    public Map<String, Object> getGoodsInfo(String id) {
        return goodsDao.getGoodsInfo(id);
    }

    @Override
    public boolean addSpec(GoodsAddSpecBO addSpecBO) {
        Spec spec = new Spec(null,addSpecBO.getSpecName(),addSpecBO.getStockNum(),addSpecBO.getUnitPrice(),addSpecBO.getGoodsId());
        if ( goodsDao.checkSpec(spec) ) {
            return false;
        }
        return goodsDao.addSpecs(spec);
    }

    @Override
    public void deleteSpec(GoodsDeleteSpecBO deleteSpecBO) {
        goodsDao.deleteSpec(deleteSpecBO);
    }

    @Override
    public void updataGoods(GoodsUpdataBO updataBO) {
        Goods goods = getGoods(updataBO);
        goodsDao.updataGoods(goods);
        goodsDao.updataSpecs(updataBO);
    }

    @Override
    public void deleteGoods(String id) {
        goodsDao.deleteGoods(id);
        goodsDao.deleteSpecByGoodsId(id);
    }

    @Override
    public List<MsgReplyVO> repliedMsg() {
        return goodsDao.repliedMsg();
    }

    @Override
    public List<MsgNoReplyVO> noReplyMsg() {
        return goodsDao.noReplyMsg();
    }

    @Override
    public void reply(ReplyBO replyBO) {
        goodsDao.reply(replyBO);
    }

    @Override
    public List<SearchGoodsVO> searchGoods(String keyword) {
        return goodsDao.searchGoods(keyword);
    }

    @Override
    public List<CommentsVO> getGoodsComment(int goodsId) {
        return goodsDao.getGoodsComment(goodsId);
    }

    private Goods getGoods(GoodsUpdataBO updataBO) {
        List<SpecBO> specList = updataBO.getSpecList();
        double price = specList.get(0).getUnitPrice();
        int stockNum = specList.get(0).getStockNum();
        for (int i = 1; i < specList.size(); i++) {
            if (price > specList.get(i).getUnitPrice()){
                price = specList.get(i).getUnitPrice();
            }
            if (stockNum < specList.get(i).getStockNum()){
                stockNum = specList.get(i).getStockNum();
            }
        }
        return new Goods(updataBO.getId(),
                updataBO.getName(),
                updataBO.getImg(),
                price,
                updataBO.getTypeId(),
                stockNum,
                updataBO.getDesc());
    }
}
