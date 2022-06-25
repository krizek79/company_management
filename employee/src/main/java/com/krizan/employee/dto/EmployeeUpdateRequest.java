package com.krizan.employee.dto;

public record EmployeeUpdateRequest(
        String firstName,
        String lastName,
        String email,
        String phoneNumber) {
}
