package com.krizan.company.vo;

import com.krizan.company.model.Company;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CompanyWithEmployees {

    private Company company;
    private List<Employee> employees;

    public CompanyWithEmployees() {
        this.employees = new ArrayList<>();
    }
}
