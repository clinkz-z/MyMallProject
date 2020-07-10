package com.zzz.project1.model.bo;

import java.util.List;

/**
 * 购物车下单BO
 */
public class SettleAccountBO {

    private Integer id;

    private Integer goodsNum;

    private Double amount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
