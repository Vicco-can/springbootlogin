package com.company.corporation.springboot.springbootlogin.controller;

import com.company.corporation.springboot.springbootlogin.entity.Company;
import com.company.corporation.springboot.springbootlogin.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CompanyController {

    @Autowired
    private ICompanyService companyService;

    @RequestMapping("company")
    @ResponseBody
    public List<Company> findCompany(HttpServletRequest request, String companyid){

        return companyService.findCompanyById(companyid);
    }
}
