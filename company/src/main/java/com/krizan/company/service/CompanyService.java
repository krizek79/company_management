package com.krizan.company.service;

import com.krizan.company.dto.CompanyRegisterRequest;
import com.krizan.company.dto.CompanyUpdateRequest;
import com.krizan.company.model.Company;

import java.util.List;

public interface CompanyService {

    Company registerCompany(CompanyRegisterRequest request);
    Company updateCompany(Long id, CompanyUpdateRequest request);
    void deleteCompany(Long id);
    Company getCompanyById(Long id);
    List<Company> getAllCompanies();
    Integer getNumberOfEmployees(Long id);
}
