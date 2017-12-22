package com.test.majun.shiro.ch06.entity;

/**
 * Created by majun on 20/12/2017.
 */
public class Role {

    private Long id;

    private String role;

    private String description;

    private Boolean available = Boolean.FALSE;


    public Role() {
    }

    public Role(String role,String description, Boolean available) {
        this.available = available;
        this.description = description;
        this.role = role;
    }

    @Override
    public String toString() {
        return "Role{" +
                "available=" + available +
                ", id=" + id +
                ", role='" + role + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
