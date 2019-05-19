package com.fuzy.myshiro.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author fuzy
 */
@Data
public class Module implements Serializable {
    private Integer id;

    private String feature;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature == null ? null : feature.trim();
    }
}