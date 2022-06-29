package com.krizan.company.service;

import com.krizan.company.dto.AmountRequest;
import com.krizan.company.dto.CompanyRegisterRequest;
import com.krizan.company.dto.CompanyUpdateRequest;
import com.krizan.company.exception.NotFoundException;
import com.krizan.company.model.Company;
import com.krizan.company.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record CompanyServiceImpl(CompanyRepository companyRepository) implements CompanyService {
    @Override
    public Company registerCompany(CompanyRegisterRequest request) {
        Company company = Company.builder()
                .name(request.name())
                .address(request.address())
                .build();
        return companyRepository.save(company);
    }

    @Override
    public Integer setNumberOfEmployees(Long id, AmountRequest request) {
        return null;
    }

    @Override
    public Company updateCompany(Long id, CompanyUpdateRequest request) {
        Company company = getCompanyById(id);
        company.setName(request.name());
        company.setAddress(request.address());
        return companyRepository.save(company);
    }

    @Override
    public void deleteCompany(Long id) {
        Company company = getCompanyById(id);
        //  TODO: delete all employees of this company
        companyRepository.delete(company);
    }

    @Override
    public Company getCompanyById(Long id) {
        Company company = companyRepository.findCompanyById(id);
        if (company == null) throw new NotFoundException();
        return company;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Long getCompanyId(Long id) {
        Company company = getCompanyById(id);
        return company.getId();
    }
}
