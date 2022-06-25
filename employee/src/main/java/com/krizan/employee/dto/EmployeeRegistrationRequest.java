package com.krizan.employee.dto;

public record EmployeeRegistrationRequest (
        String firstName,
        String lastName,
        String email,
        String phoneNumber) {
}
