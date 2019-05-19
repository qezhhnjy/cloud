package com.cotek.boot.shiro;

import lombok.Data;

/**
 * @author fuzy
 * create time 18-11-6-下午1:10
 */
@Data
public class User {
    private long id;
    private String username;
    private String password;
    private String roleName;
    /**
     * 是否禁用
     */
    private boolean locked;

    public User(long id, String username, String password, String aa, boolean locked) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roleName = aa;
        this.locked = locked;
    }
}
