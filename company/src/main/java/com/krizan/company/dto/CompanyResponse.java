package com.krizan.company.dto;

import com.krizan.company.vo.Address;
import com.krizan.company.model.Company;
import lombok.Getter;

@Getter
public class CompanyResponse {

    private final Long id;
    private final String name;
    private final Address address;
    private final Integer numberOfEmployees;

    public CompanyResponse(Company company) {
        this.id = company.getId();
        this.name = company.getName();
        this.address = company.getAddress();
        this.numberOfEmployees = company.getNumberOfEmployees();
    }
}
