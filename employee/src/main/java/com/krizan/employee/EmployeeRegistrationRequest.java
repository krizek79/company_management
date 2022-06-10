package com.krizan.employee;

public record EmployeeRegistrationRequest (
        String firstName,
        String lastName,
        String email,
        String phoneNumber) {
}
