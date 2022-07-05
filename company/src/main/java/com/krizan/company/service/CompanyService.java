package com.krizan.company.service;

import com.krizan.company.dto.CompanyRegisterRequest;
import com.krizan.company.dto.CompanyUpdateRequest;
import com.krizan.company.model.Company;
import com.krizan.company.vo.Amount;
import com.krizan.company.vo.CompanyWithEmployees;

import java.util.List;

public interface CompanyService {

    Company registerCompany(CompanyRegisterRequest request);
    void setNumberOfEmployees(Long id, Amount request);
    Company updateCompany(Long id, CompanyUpdateRequest request);
    void deleteCompany(Long id);
    Company getCompanyById(Long id);
    CompanyWithEmployees getCompanyWithDetails(Long id);
    List<Company> getAllCompanies();
}
