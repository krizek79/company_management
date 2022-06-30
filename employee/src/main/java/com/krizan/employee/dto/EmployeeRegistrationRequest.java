package com.krizan.employee.dto;

import com.krizan.employee.vo.Address;

public record EmployeeRegistrationRequest (
        Long companyId,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        Address address) {
}
