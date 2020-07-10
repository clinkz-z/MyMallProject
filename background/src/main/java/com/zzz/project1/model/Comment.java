package com.zzz.project1.model;

import java.sql.Date;

public class Comment {

    private String content;

    private Integer goodsDetailId;

    private Integer goodsId;

    private Integer orderId;

    private Integer score;

    private String userName;

    private Date createTime;

    private String specName;

    public Comment() {
    }

    public Comment(String content, Integer goodsDetailId, Integer goodsId, Integer orderId, Integer score, String userName, Date createTime, String specName) {
        this.content = content;
        this.goodsDetailId = goodsDetailId;
        this.goodsId = goodsId;
        this.orderId = orderId;
        this.score = score;
        this.userName = userName;
        this.createTime = createTime;
        this.specName = specName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getGoodsDetailId() {
        return goodsDetailId;
    }

    public void setGoodsDetailId(Integer goodsDetailId) {
        this.goodsDetailId = goodsDetailId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "content='" + content + '\'' +
                ", goodsDetailId=" + goodsDetailId +
                ", goodsId=" + goodsId +
                ", orderId=" + orderId +
                ", score=" + score +
                ", userName='" + userName + '\'' +
                ", createTime=" + createTime +
                ", specName='" + specName + '\'' +
                '}';
    }
}
