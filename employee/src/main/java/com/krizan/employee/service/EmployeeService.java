package com.krizan.employee.service;

import com.krizan.employee.dto.EmployeeRegistrationRequest;
import com.krizan.employee.dto.EmployeeUpdateRequest;
import com.krizan.employee.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee registerEmployee(EmployeeRegistrationRequest request);
    Employee updateEmployee(Long id, EmployeeUpdateRequest request);
    void deleteEmployee(Long id);
    void deleteAllEmployeesByCompanyId(Long id);
    Employee getEmployeeById(Long id);
    List<Employee> getAllEmployeesByCompanyId(Long id);
}
