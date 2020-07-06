package com.zzz.project1.dao;

import com.zzz.project1.model.Goods;
import com.zzz.project1.model.Spec;
import com.zzz.project1.model.Type;
import com.zzz.project1.model.vo.GoodsTypeVO;

import java.util.List;
import java.util.Map;

public interface GoodsDao {
    List<Type> getType();

    Boolean addType(Type type);

    Boolean searchType(Type type);

    List<GoodsTypeVO> getGoodsByType(String typeId);

    void addGoods(Goods goods);

    int lastInsertId();

    void addSpecs(List<Spec> specs);

    Map<String, Object> getGoodsInfo(String id);
}