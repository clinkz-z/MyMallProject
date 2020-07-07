package com.zzz.project1.service;

import com.zzz.project1.dao.OrderDao;
import com.zzz.project1.dao.OrderDaoImpl;
import com.zzz.project1.model.bo.PageOrderBO;
import com.zzz.project1.model.vo.PageOrderInfoVO;
import com.zzz.project1.model.vo.PageOrdersVO;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();

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
}
