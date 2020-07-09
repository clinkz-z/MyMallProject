package com.zzz.project1.dao;

import com.zzz.project1.model.Orders;
import com.zzz.project1.model.bo.PageOrderBO;
import com.zzz.project1.model.vo.*;

import java.util.List;

public interface OrderDao {
    List<PageOrderInfoVO> ordersByPage(PageOrderBO orderBO);

    int getTotalCounts(PageOrderBO orderBO);

    OrderByIdVO order(String id);

    List<OrderSpecVO> getSpecsByGoodsId(int goodsId);

    void changeOrder(Orders orderChangeBO);

    SpecVO getSpec(int goodsId, int goodsDetailId);

    void deleteOrder(String id);

    List<GetOrderByStateVO> getOrderByState(Integer state, String token);

    GoodsByStateVO getGoodsByGoodsId(int goodsId);

}
