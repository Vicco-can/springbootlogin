package com.company.corporation.springboot.springbootlogin.entity;

public class Company {
    private Long id;

    private String companyId;

    private String companyName;

    private String companyAdminTel;

    private String companyAddress;

    private String companyEmail;

    private String cityId;

    private String areaId;

    private String companyStatus;

    public Company(Long id, String companyId, String companyName, String companyAdminTel, String companyAddress, String companyEmail, String cityId, String areaId, String companyStatus) {
        this.id = id;
        this.companyId = companyId;
        this.companyName = companyName;
        this.companyAdminTel = companyAdminTel;
        this.companyAddress = companyAddress;
        this.companyEmail = companyEmail;
        this.cityId = cityId;
        this.areaId = areaId;
        this.companyStatus = companyStatus;
    }

    public Company() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getCompanyAdminTel() {
        return companyAdminTel;
    }

    public void setCompanyAdminTel(String companyAdminTel) {
        this.companyAdminTel = companyAdminTel == null ? null : companyAdminTel.trim();
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress == null ? null : companyAddress.trim();
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail == null ? null : companyEmail.trim();
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId == null ? null : cityId.trim();
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId == null ? null : areaId.trim();
    }

    public String getCompanyStatus() {
        return companyStatus;
    }

    public void setCompanyStatus(String companyStatus) {
        this.companyStatus = companyStatus == null ? null : companyStatus.trim();
    }
}