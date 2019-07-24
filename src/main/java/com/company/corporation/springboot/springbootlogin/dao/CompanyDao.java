package com.company.corporation.springboot.springbootlogin.dao;

import com.company.corporation.springboot.springbootlogin.entity.Company;

import java.util.List;

public interface CompanyDao {
    List<Company> findCompanyById(String id);

    List<Company> findCompany(List<String> listCompanyId);
}
