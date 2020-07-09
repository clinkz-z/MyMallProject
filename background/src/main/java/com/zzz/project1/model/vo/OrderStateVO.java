package com.zzz.project1.model.vo;

public class OrderStateVO {

    private Integer id;

    private String name;

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

    public OrderStateVO() {
    }

    public OrderStateVO(Integer id) {
        this.id = id;
    }

    public OrderStateVO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
