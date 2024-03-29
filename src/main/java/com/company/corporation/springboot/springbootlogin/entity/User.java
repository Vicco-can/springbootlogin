package com.company.corporation.springboot.springbootlogin.entity;

import java.util.Date;

public class User {
    private Long id;

    private String userName;

    private String companyId;

    private String tel;

    private String password;

    private String role;

    private Integer misscount;

    private Date allowtime;

    private String logicalState;

    public User(Long id, String userName, String companyId, String tel, String password, String role, Integer misscount, Date allowtime, String logicalState) {
        this.id = id;
        this.userName = userName;
        this.companyId = companyId;
        this.tel = tel;
        this.password = password;
        this.role = role;
        this.misscount = misscount;
        this.allowtime = allowtime;
        this.logicalState = logicalState;
    }

    public User() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public Integer getMisscount() {
        return misscount;
    }

    public void setMisscount(Integer misscount) {
        this.misscount = misscount;
    }

    public Date getAllowtime() {
        return allowtime;
    }

    public void setAllowtime(Date allowtime) {
        this.allowtime = allowtime;
    }

    public String getLogicalState() {
        return logicalState;
    }

    public void setLogicalState(String logicalState) {
        this.logicalState = logicalState == null ? null : logicalState.trim();
    }
}