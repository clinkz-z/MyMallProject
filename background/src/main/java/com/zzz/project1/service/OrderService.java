package com.zzz.project1.service;


import com.zzz.project1.model.bo.OrderChangeBO;
import com.zzz.project1.model.bo.PageOrderBO;
import com.zzz.project1.model.vo.GetOrderByStateVO;
import com.zzz.project1.model.vo.OrderByIdVO;
import com.zzz.project1.model.vo.PageOrdersVO;

import java.util.List;

public interface OrderService {

    PageOrdersVO ordersByPage(PageOrderBO orderBO);

    OrderByIdVO order(String id);

    void changeOrder(OrderChangeBO orderChangeBO);

    void deleteOrder(String id);

    List<GetOrderByStateVO> getOrderByState(Integer state, String token);
}
