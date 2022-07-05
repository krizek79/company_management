package com.krizan.employee.repository;

import com.krizan.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findEmployeeById(Long id);
    List<Employee> findAllByCompanyId(Long id);
    @Transactional
    void deleteAllByCompanyId(Long id);
}
