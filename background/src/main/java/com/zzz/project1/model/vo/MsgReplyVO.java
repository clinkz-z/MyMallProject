package com.zzz.project1.model.vo;

import java.sql.Date;

public class MsgReplyVO {

    private Integer id;

    private Integer userId;

    private Integer goodsId;

    private String content;

    private String replyContent;

    private Integer state;

    private Date createtime;

    private MsgGoodsVO goods = new MsgGoodsVO();

    private MsgUserVO user = new MsgUserVO();

    public void setGoodsName(String goods){
        this.goods.setName(goods);
    }

    public void setUserName(String user){
        this.user.setName(user);
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

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public MsgGoodsVO getGoods() {
        return goods;
    }

    public void setGoods(MsgGoodsVO goods) {
        this.goods = goods;
    }

    public MsgUserVO getUser() {
        return user;
    }

    public void setUser(MsgUserVO user) {
        this.user = user;
    }

}
