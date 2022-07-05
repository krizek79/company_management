package com.krizan.employee.dto;

import com.krizan.employee.vo.Address;
import com.krizan.employee.model.Employee;
import lombok.Getter;

@Getter
public class EmployeeDetailResponse {

    private final Long id;
    private final Long companyId;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phoneNumber;
    private final Address address;

    public EmployeeDetailResponse(Employee employee) {
        this.id = employee.getId();
        this.companyId = employee.getCompanyId();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.email = employee.getEmail();
        this.phoneNumber = employee.getPhoneNumber();
        this.address = employee.getAddress();
    }
}
