package com.krizan.employee.service;

import com.krizan.employee.dto.EmployeeRegistrationRequest;
import com.krizan.employee.dto.EmployeeUpdateRequest;
import com.krizan.employee.exception.NotFoundException;
import com.krizan.employee.model.Employee;
import com.krizan.employee.repository.EmployeeRepository;
import com.krizan.employee.vo.Amount;
import com.krizan.employee.vo.Company;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public record EmployeeServiceImpl(EmployeeRepository employeeRepository, RestTemplate restTemplate) implements EmployeeService {
    public Employee registerEmployee(EmployeeRegistrationRequest request) {
        Company company = restTemplate.getForObject("http://localhost:9002/api/companies/" + request.companyId(), Company.class);
        if (company == null) throw new NotFoundException();
        Employee employee = Employee.builder()
                .companyId(request.companyId())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .phoneNumber(request.phoneNumber())
                .address(request.address())
                .build();
        restTemplate.postForObject("http://localhost:9002/api/companies/setNumberOfEmployees/" + request.companyId(),
                new Amount(1),
                Amount.class);
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
        restTemplate.postForObject("http://localhost:9002/api/companies/setNumberOfEmployees/" + id,
                new Amount(-1),
                Amount.class);
        employeeRepository.delete(employee);
    }

    @Override
    public void deleteAllEmployeesByCompanyId(Long id) {
        Company company = restTemplate.getForObject("http://localhost:9002/api/companies/" + id, Company.class);
        if (company == null) throw new NotFoundException();
        restTemplate.postForObject("http://localhost:9002/api/companies/setNumberOfEmployees/" + id,
                new Amount(-company.getNumberOfEmployees()),
                Amount.class);
        employeeRepository.deleteAllByCompanyId(id);
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

    @Override
    public List<Employee> getAllEmployeesByCompanyId(Long id) {
        Company company = restTemplate.getForObject("http://localhost:9002/api/companies/" + id, Company.class);
        if (company == null) throw new NotFoundException();
        return employeeRepository.findAllByCompanyId(id);
    }
}
