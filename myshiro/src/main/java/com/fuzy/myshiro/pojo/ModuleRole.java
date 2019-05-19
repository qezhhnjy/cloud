package com.fuzy.myshiro.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author fuzy
 */
@Data
public class ModuleRole implements Serializable {
    private Integer mid;

    private Integer rid;

    private static final long serialVersionUID = 1L;

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }
}