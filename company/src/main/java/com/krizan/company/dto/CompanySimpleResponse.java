package com.krizan.company.dto;

import com.krizan.company.model.Company;
import lombok.Getter;

@Getter
public class CompanySimpleResponse {

    private final Long id;
    private final String name;

    public CompanySimpleResponse(Company company) {
        this.id = company.getId();
        this.name = company.getName();
    }
}
