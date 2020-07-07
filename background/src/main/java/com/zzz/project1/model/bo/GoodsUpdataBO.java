package com.zzz.project1.model.bo;

import java.util.List;

public class GoodsUpdataBO {

    private String name;

    private String img;

    private String desc;

    private Integer typeId;

    private List<SpecBO> specList;

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        if (id == null) this.id = null;
        else this.id = id;
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

    public List<SpecBO> getSpecList() {
        return specList;
    }

    public void setSpecList(List<SpecBO> specList) {
        this.specList = specList;
    }
}
