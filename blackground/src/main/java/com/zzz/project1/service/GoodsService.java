package com.zzz.project1.service;

import com.zzz.project1.model.Type;
import com.zzz.project1.model.bo.GoodsAddBO;
import com.zzz.project1.model.bo.GoodsAddTypeBO;
import com.zzz.project1.model.vo.GoodsTypeVO;

import java.util.List;
import java.util.Map;

public interface GoodsService {
    List<Type> getType();

    Boolean addType(GoodsAddTypeBO addTypeBO);

    List<GoodsTypeVO> goodsByType(String typeId);

    void addGoods(GoodsAddBO goodsAddBo);

    Map<String, Object> getGoodsInfo(String id);
}
