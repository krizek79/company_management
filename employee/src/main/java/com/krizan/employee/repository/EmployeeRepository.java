package com.krizan.employee.repository;

import com.krizan.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findEmployeeById(Long id);
    List<Employee> findAllByCompanyId(Long id);
    void deleteAllByCompanyId(Long id);
}
