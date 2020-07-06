package com.zzz.project1.model;

public class Goods {

    private Integer id;

    private String name;

    private String img;

    private Double price;

    private Integer tpyeId;

    private Integer stockNum;

    private String desc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getTpyeId() {
        return tpyeId;
    }

    public void setTpyeId(Integer tpyeId) {
        this.tpyeId = tpyeId;
    }

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Goods() {
    }

    public Goods(Integer id, String name, String img, Double price, Integer tpyeId, Integer stockNum, String desc) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.price = price;
        this.tpyeId = tpyeId;
        this.stockNum = stockNum;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", price=" + price +
                ", tpyeId=" + tpyeId +
                ", stockNum=" + stockNum +
                ", desc='" + desc + '\'' +
                '}';
    }
}
