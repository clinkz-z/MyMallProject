package com.zzz.project1.model.vo;

import com.zzz.project1.model.enumerate.OrderState;

/**
 * 后台管理系统分页管理订单查询每个订单详情VO
 */
public class PageOrderInfoVO {

    private Integer id;

    private Integer userId;

    private Integer goodsDetailId;

    private String goods;

    private String spec;

    private Integer goodsNum;

    private Double amount;

    private Integer stateId;

    private String state;

    private PageOrderInfoUserVO user = new PageOrderInfoUserVO();

    public void setNickname(String nickname){
        user.setNickname(nickname);
    }

    public void setName(String name){
        user.setName(name);
    }

    public void setAddress(String address){
        user.setAddress(address);
    }

    public void setPhone(String phone){
        user.setPhone(phone);
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGoodsDetailId() {
        return goodsDetailId;
    }

    public void setGoodsDetailId(Integer goodsDetailId) {
        this.goodsDetailId = goodsDetailId;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
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

    public Integer getStateId() {
        return stateId;
    }

    /**
     * 在这里做手脚
     * dbutils是如果封装数据的？
     * 取出每列的列名 id useId 反射查询 setId setUserId
     * @param stateId
     */
    public void setStateId(Integer stateId) {
        if (stateId.equals(OrderState.UN_PAID.getCode())){
            setState(OrderState.UN_PAID.getValue());
        }
        if (stateId.equals(OrderState.UN_SHIPED.getCode())){
            setState(OrderState.UN_SHIPED.getValue());
        }
        if (stateId.equals(OrderState.DELIVERED.getCode())){
            setState(OrderState.DELIVERED.getValue());
        }
        if (stateId.equals(OrderState.RECEIVED.getCode())){
            setState(OrderState.RECEIVED.getValue());
        }

        this.stateId = stateId;
    }

    public String  getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public PageOrderInfoUserVO getUser() {
        return user;
    }

    public void setUser(PageOrderInfoUserVO user) {
        this.user = user;
    }
}
