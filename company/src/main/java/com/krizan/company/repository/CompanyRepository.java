package com.krizan.company.repository;

import com.krizan.company.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findCompanyById(Long id);
}
