package com.zzz.project1.model.bo;

public class OrderChangeBO {

    private Integer id;

    private Integer num;

    private Integer spec;//specId

    private String specName;

    private Double amount;

    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getSpec() {
        return spec;
    }

    public void setSpec(Integer spec) {
        this.spec = spec;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
