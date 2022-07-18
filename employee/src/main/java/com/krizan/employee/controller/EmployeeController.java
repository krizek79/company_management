package com.krizan.employee.controller;

import com.krizan.employee.dto.EmployeeDetailResponse;
import com.krizan.employee.dto.EmployeeRegistrationRequest;
import com.krizan.employee.dto.EmployeeSimpleResponse;
import com.krizan.employee.dto.EmployeeUpdateRequest;
import com.krizan.employee.service.EmployeeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/employees")
public record EmployeeController(EmployeeServiceImpl employeeService) {

    @PostMapping
    public ResponseEntity<EmployeeDetailResponse> registerEmployee(@RequestBody EmployeeRegistrationRequest request) {
        return new ResponseEntity<>(new EmployeeDetailResponse(employeeService.registerEmployee(request)), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeDetailResponse> updateEmployee(
            @PathVariable("id") Long id,
            @RequestBody EmployeeUpdateRequest request) {
        return new ResponseEntity<>(new EmployeeDetailResponse(employeeService.updateEmployee(id, request)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
    }

    @DeleteMapping("/company/{id}")
    public void deleteAllEmployeesByCompanyId(@PathVariable("id") Long id) {
        employeeService.deleteAllEmployeesByCompanyId(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDetailResponse> getEmployeeById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(new EmployeeDetailResponse(employeeService.getEmployeeById(id)), HttpStatus.OK);
    }

    @GetMapping("/company/{id}")
    public List<EmployeeSimpleResponse> getAllEmployeesByCompanyId(@PathVariable("id") Long id) {
        return employeeService.getAllEmployeesByCompanyId(id)
                .stream()
                .map(EmployeeSimpleResponse::new)
                .collect(Collectors.toList());
    }
}
