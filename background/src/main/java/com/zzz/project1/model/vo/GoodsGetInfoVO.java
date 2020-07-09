package com.zzz.project1.model.vo;

import com.zzz.project1.model.bo.SpecBO;

import java.util.List;

public class GoodsGetInfoVO {
    private String name;

    private String img;

    private String desc;

    private Integer typeId;

    private List<SpecVO> specs;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public List<SpecVO> getSpecs() {
        return specs;
    }

    public void setSpecs(List<SpecVO> specs) {
        this.specs = specs;
    }
}
