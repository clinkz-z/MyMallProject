package com.zzz.project1.model.vo;

import com.zzz.project1.model.enumerate.OrderState;

import java.util.ArrayList;
import java.util.List;

public class OrderByIdVO {

    private Integer id;

    private Double amount;

    private Integer num;

    private Integer goodsDetailId;

    private Integer state;

    private String goods;

    private Integer goodsId;

    private List<OrderSpecVO> spec;

    private List<OrderStateVO> states;

    private OrderStateVO curState;

    private OrderSpecVO curSpec;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getGoodsDetailId() {
        return goodsDetailId;
    }

    public void setGoodsDetailId(Integer goodsDetailId) {
        this.goodsDetailId = goodsDetailId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        List<OrderStateVO> states = new ArrayList<>();
        states.add(new OrderStateVO(OrderState.UN_PAID.getCode(),OrderState.UN_PAID.getValue()));
        states.add(new OrderStateVO(OrderState.UN_SHIPED.getCode(),OrderState.UN_SHIPED.getValue()));
        states.add(new OrderStateVO(OrderState.DELIVERED.getCode(),OrderState.DELIVERED.getValue()));
        states.add(new OrderStateVO(OrderState.RECEIVED.getCode(),"已完成订单"));
        this.setStates(states);
        this.state = state;
        this.setCurState(new OrderStateVO(state));
        this.state = state;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public List<OrderSpecVO> getSpec() {
        return spec;
    }

    public void setSpec(List<OrderSpecVO> spec) {
        this.spec = spec;
    }

    public List<OrderStateVO> getStates() {
        return states;
    }

    public void setStates(List<OrderStateVO> states) {
        this.states = states;
    }

    public OrderStateVO getCurState() {
        return curState;
    }

    public void setCurState(OrderStateVO curState) {
        this.curState = curState;
    }

    public OrderSpecVO getCurSpec() {
        return curSpec;
    }

    public void setCurSpec(OrderSpecVO curSpec) {
        this.curSpec = curSpec;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }
}
