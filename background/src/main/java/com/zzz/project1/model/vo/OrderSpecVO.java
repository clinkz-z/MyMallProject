package com.zzz.project1.model.vo;

/**
 * 获取订单修改页面 获取订单规格VO
 */
public class OrderSpecVO {

    private Integer id;

    private String specName;

    private Double unitPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public OrderSpecVO() {
    }

    public OrderSpecVO(Integer id) {
        this.id = id;
    }
}
