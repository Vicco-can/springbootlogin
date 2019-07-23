package com.company.corporation.springboot.springbootlogin.service.impl;

import com.company.corporation.springboot.springbootlogin.dao.CompanyDao;
import com.company.corporation.springboot.springbootlogin.entity.Company;
import com.company.corporation.springboot.springbootlogin.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements ICompanyService {

    @Autowired
    private CompanyDao companyDao;

    @Override
    public List<Company> findCompanyById(String id){
        return companyDao.findCompanyById(id);
    }
}
