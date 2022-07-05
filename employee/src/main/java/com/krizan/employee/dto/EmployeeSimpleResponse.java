package com.krizan.employee.dto;

import com.krizan.employee.model.Employee;
import lombok.Getter;

@Getter
public class EmployeeSimpleResponse {

    private final Long id;
    private final String firstName;
    private final String lastName;

    public EmployeeSimpleResponse(Employee employee) {
        this.id = employee.getId();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
    }
}
