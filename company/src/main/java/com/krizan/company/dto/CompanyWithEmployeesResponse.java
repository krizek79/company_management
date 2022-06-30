package com.krizan.company.dto;

import com.krizan.company.vo.Address;
import com.krizan.company.vo.CompanyWithEmployees;
import com.krizan.company.vo.Employee;
import lombok.Getter;

import java.util.List;

@Getter
public class CompanyWithEmployeesResponse {

    private final Long id;
    private final String name;
    private final Address address;
    private final Integer numberOfEmployees;
    private final List<Employee> employees;

    public CompanyWithEmployeesResponse(CompanyWithEmployees companyWithEmployees) {
        this.id = companyWithEmployees.getCompany().getId();
        this.name = companyWithEmployees.getCompany().getName();
        this.address = companyWithEmployees.getCompany().getAddress();
        this.numberOfEmployees = companyWithEmployees.getCompany().getNumberOfEmployees();
        this.employees = companyWithEmployees.getEmployees();
    }
}
