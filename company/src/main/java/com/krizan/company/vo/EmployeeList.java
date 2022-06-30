package com.krizan.company.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EmployeeList {

    private List<Employee> employees;

    public EmployeeList() {
        employees = new ArrayList<>();
    }
}
