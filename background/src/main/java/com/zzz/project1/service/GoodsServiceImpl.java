package com.zzz.project1.service;

import com.zzz.project1.dao.GoodsDao;
import com.zzz.project1.dao.GoodsDaoImpl;
import com.zzz.project1.model.Goods;
import com.zzz.project1.model.Spec;
import com.zzz.project1.model.Type;
import com.zzz.project1.model.bo.GoodsAddBO;
import com.zzz.project1.model.bo.GoodsAddTypeBO;
import com.zzz.project1.model.bo.SpecBO;
import com.zzz.project1.model.vo.GoodsTypeVO;

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
    public void addGoods(GoodsAddBO goodsAddBo) {
        List<SpecBO> specList = goodsAddBo.getSpecList();
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
        Goods goods = new Goods(null,
                goodsAddBo.getName(),
                goodsAddBo.getImg(),
                price,
                goodsAddBo.getTypeId(),
                stockNum,
                goodsAddBo.getDesc());
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
}
