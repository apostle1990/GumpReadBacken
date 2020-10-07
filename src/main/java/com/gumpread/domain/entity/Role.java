package com.gumpread.domain.entity;

import lombok.Data;
import org.springframework.data.redis.connection.ReactiveSetCommands;

import java.io.Serializable;

/**
 * role角色表对应的实体;
 */
public class Role implements Serializable {
    private int id;
    private String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }
}
