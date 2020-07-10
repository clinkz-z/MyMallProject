package com.zzz.project1.dao;

import com.zzz.project1.model.Orders;
import com.zzz.project1.model.bo.CommentsBO;
import com.zzz.project1.model.bo.OrderAddBO;
import com.zzz.project1.model.bo.PageOrderBO;
import com.zzz.project1.model.bo.SettleAccountBO;
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

    void settleAccounts(SettleAccountBO order);

    void pay(int id);

    void confirmReceive(int id);

    void sendComment(CommentsBO commentsBO);

    void addOrder(OrderAddBO orderAddBO);
}
