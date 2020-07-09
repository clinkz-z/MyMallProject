package com.zzz.project1.service;

import com.zzz.project1.dao.OrderDao;
import com.zzz.project1.dao.OrderDaoImpl;
import com.zzz.project1.model.Orders;
import com.zzz.project1.model.bo.OrderChangeBO;
import com.zzz.project1.model.bo.PageOrderBO;
import com.zzz.project1.model.vo.*;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();
    private int goodsId;//更新order时其实的商品id
    /**
     * 根据传入不同的参数，执行不同的查询，返回对应的结果
     * @param orderBO
     * @return
     */
    @Override
    public PageOrdersVO ordersByPage(PageOrderBO orderBO) {
        int totalCounts = orderDao.getTotalCounts(orderBO);
        //查询当前分页结果 page1 1-5  page2 6-10 page3 11-15
        List<PageOrderInfoVO> orderInfoVOS = orderDao.ordersByPage(orderBO);
        //for —— 取出每个stateId —— state
        PageOrdersVO pageOrdersVO = new PageOrdersVO();
        pageOrdersVO.setTotal(totalCounts);
        pageOrdersVO.setOrders(orderInfoVOS);
        return pageOrdersVO;
    }

    @Override
    public OrderByIdVO order(String id) {
        OrderByIdVO order = orderDao.order(id);
        goodsId = order.getGoodsId();
        List<OrderSpecVO> specs = orderDao.getSpecsByGoodsId(goodsId);
        order.setSpec(specs);
        order.setCurSpec(new OrderSpecVO(order.getGoodsDetailId()));
        return order;
    }

    @Override
    public void changeOrder(OrderChangeBO orderChangeBO) {
        int goodsDetailId = orderChangeBO.getSpec();
        SpecVO upSpec = orderDao.getSpec(goodsId,goodsDetailId);

        Orders order = new Orders();
        order.setId(orderChangeBO.getId());
        order.setSpec(upSpec.getSpecName());
        order.setGoodsDetailId(upSpec.getId());
        order.setAmount(upSpec.getUnitPrice() * orderChangeBO.getNum());
        order.setNum(orderChangeBO.getNum());
        order.setStateId(orderChangeBO.getState());

        orderDao.changeOrder(order);
    }

    @Override
    public void deleteOrder(String id) {
        orderDao.deleteOrder(id);
    }

    @Override
    public List<GetOrderByStateVO> getOrderByState(Integer state, String token) {
        List<GetOrderByStateVO> getOrderList = orderDao.getOrderByState(state, token);
        for (GetOrderByStateVO order: getOrderList) {
            int goodsId = order.getGoodsID();
            order.setGoods(orderDao.getGoodsByGoodsId(goodsId));
            GoodsByStateVO goods = order.getGoods();
            goods.setGoodsDetailId(order.getGoodsDetailId());
            goods.setUnitPrice(order.getAmount()/order.getGoodsNum());
            goods.setSpec(orderDao.getSpec(goodsId, goods.getGoodsDetailId()).getSpecName());
        }
        return getOrderList;
    }

}
