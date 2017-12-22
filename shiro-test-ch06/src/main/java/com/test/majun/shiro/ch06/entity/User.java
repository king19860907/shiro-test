package com.test.majun.shiro.ch06.entity;

/**
 * Created by majun on 20/12/2017.
 */
public class User {

    private Long id;

    private String username;

    private String password;

    private String salt;

    private Boolean locked = Boolean.FALSE;

    public User() {
    }

    public User(String username,String password) {
        this.password = password;
        this.username = username;
    }

    public String getCredentialsSalt(){
        return username+salt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", locked=" + locked +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
