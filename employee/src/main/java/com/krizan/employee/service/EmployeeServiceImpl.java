package com.krizan.employee.service;

import com.krizan.employee.dto.EmployeeRegistrationRequest;
import com.krizan.employee.dto.EmployeeUpdateRequest;
import com.krizan.employee.exception.NotFoundException;
import com.krizan.employee.model.Employee;
import com.krizan.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record EmployeeServiceImpl(EmployeeRepository employeeRepository) implements EmployeeService {
    public Employee registerEmployee(EmployeeRegistrationRequest request) {
        //  TODO: validate companyId
        Employee employee = Employee.builder()
                .companyId(request.companyId())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .phoneNumber(request.phoneNumber())
                .address(request.address())
                .build();
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, EmployeeUpdateRequest request) {
        Employee employee = getEmployeeById(id);
        employee.setFirstName(request.firstName());
        employee.setLastName(request.lastName());
        employee.setEmail(request.email());
        employee.setPhoneNumber(request.phoneNumber());
        employee.setAddress(request.address());
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = getEmployeeById(id);
        employeeRepository.delete(employee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Employee employee = employeeRepository.findEmployeeById(id);
        if (employee == null) throw new NotFoundException();
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
