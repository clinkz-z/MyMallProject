package com.zzz.project1.model.vo;

import java.sql.Date;

public class GetOrderByStateVO {

    private Integer id;

    private Integer goodsID;

    private Integer state;

    private Integer goodsNum;

    private Double amount;

    private Integer goodsDetailId;

    private Date createtime;

    private Boolean hasComment;

    private GoodsByStateVO goods;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsID() {
        return goodsID;
    }

    public void setGoodsID(Integer goodsID) {
        this.goodsID = goodsID;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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

    public Integer getGoodsDetailId() {
        return goodsDetailId;
    }

    public void setGoodsDetailId(Integer goodsDetailId) {
        this.goodsDetailId = goodsDetailId;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Boolean getHasComment() {
        return hasComment;
    }

    public void setHasComment(Boolean hasComment) {
        this.hasComment = hasComment;
    }

    public GoodsByStateVO getGoods() {
        return goods;
    }

    public void setGoods(GoodsByStateVO goods) {
        this.goods = goods;
    }
}
