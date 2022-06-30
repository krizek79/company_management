package com.krizan.company.service;

import com.krizan.company.dto.CompanyRegisterRequest;
import com.krizan.company.dto.CompanyUpdateRequest;
import com.krizan.company.exception.NotFoundException;
import com.krizan.company.model.Company;
import com.krizan.company.repository.CompanyRepository;
import com.krizan.company.vo.Amount;
import com.krizan.company.vo.CompanyWithEmployees;
import com.krizan.company.vo.EmployeeList;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public record CompanyServiceImpl(CompanyRepository companyRepository, RestTemplate restTemplate) implements CompanyService {
    @Override
    public Company registerCompany(CompanyRegisterRequest request) {
        Company company = Company.builder()
                .name(request.name())
                .address(request.address())
                .numberOfEmployees(0)
                .build();
        return companyRepository.save(company);
    }

    @Override
    public void setNumberOfEmployees(Long id, Amount request) {
        Company company = getCompanyById(id);
        company.setNumberOfEmployees(company.getNumberOfEmployees() + request.amount());
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
        restTemplate.delete("http://localhost:9001/api/employees/company/" + id);
        companyRepository.delete(company);
    }

    @Override
    public Company getCompanyById(Long id) {
        Company company = companyRepository.findCompanyById(id);
        if (company == null) throw new NotFoundException();
        return company;
    }

    @Override
    public CompanyWithEmployees getCompanyWithEmployeesById(Long id) {
        Company company = getCompanyById(id);
        //  TODO: Cannot deserialize value of type `com.krizan.company.vo.EmployeeList` from Array value (token `JsonToken.START_ARRAY`)
        EmployeeList employeeList = restTemplate.getForObject("http://localhost:9001/api/employees", EmployeeList.class);
        CompanyWithEmployees companyWithEmployees = new CompanyWithEmployees();
        companyWithEmployees.setCompany(company);
        if (employeeList != null) {
            companyWithEmployees.setEmployees(employeeList.getEmployees());
        }
        return companyWithEmployees;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }
}
