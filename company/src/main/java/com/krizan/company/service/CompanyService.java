package com.krizan.company.service;

import com.krizan.company.dto.AmountRequest;
import com.krizan.company.dto.CompanyRegisterRequest;
import com.krizan.company.dto.CompanyUpdateRequest;
import com.krizan.company.model.Company;

import java.util.List;

public interface CompanyService {

    Company registerCompany(CompanyRegisterRequest request);
    Integer setNumberOfEmployees(Long id, AmountRequest request);
    Company updateCompany(Long id, CompanyUpdateRequest request);
    void deleteCompany(Long id);
    Company getCompanyById(Long id);
    List<Company> getAllCompanies();
    Long getCompanyId(Long id);
}
