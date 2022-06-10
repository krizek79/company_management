package com.krizan.employee;

import org.springframework.stereotype.Service;

@Service
public record EmployeeService() {
    public void registerEmployee(EmployeeRegistrationRequest request) {
        Employee employee = Employee.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .phoneNumber(request.phoneNumber())
                .build();
    }
}
