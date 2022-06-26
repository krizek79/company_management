package com.krizan.employee.dto;

import com.krizan.employee.model.Address;

public record EmployeeUpdateRequest(
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        Address address) {
}
