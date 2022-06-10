package com.krizan.employee;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/employee")
public record EmployeeController(EmployeeService employeeService) {

    @PostMapping
    public void registerEmployee(@RequestBody EmployeeRegistrationRequest request) {
        log.info("new Employee registration {}", request);
        employeeService.registerEmployee(request);
    }
}
