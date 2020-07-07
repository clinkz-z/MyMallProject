package com.zzz.project1.service;


import com.zzz.project1.model.bo.PageOrderBO;
import com.zzz.project1.model.vo.PageOrdersVO;

public interface OrderService {

    PageOrdersVO ordersByPage(PageOrderBO orderBO);
}
