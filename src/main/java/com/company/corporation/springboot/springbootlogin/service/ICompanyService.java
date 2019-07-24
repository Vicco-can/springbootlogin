package com.company.corporation.springboot.springbootlogin.service;

import com.company.corporation.springboot.springbootlogin.entity.Company;

import java.util.List;

public interface ICompanyService {

    List<Company> findCompanyById(String id);

    List<Company> findCompany(List<String> listCompanyId);
}
