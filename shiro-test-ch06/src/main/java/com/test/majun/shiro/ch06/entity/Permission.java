package com.test.majun.shiro.ch06.entity;

/**
 * Created by majun on 20/12/2017.
 */
public class Permission {

    private Long id;

    private String permission;

    private String description;

    private Boolean available = Boolean.FALSE;

    private Permission(){}

    public Permission(String permission, String description, Boolean available) {
        this.permission = permission;
        this.description = description;
        this.available = available;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "available=" + available +
                ", id=" + id +
                ", permission='" + permission + '\'' +
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

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
