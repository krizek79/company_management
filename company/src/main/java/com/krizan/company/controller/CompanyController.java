package com.krizan.company.controller;

import com.krizan.company.dto.CompanyRegisterRequest;
import com.krizan.company.dto.CompanyResponse;
import com.krizan.company.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/companies")
public record CompanyController(CompanyService companyService) {

    @PostMapping
    public ResponseEntity<CompanyResponse> registerCompany(@RequestBody CompanyRegisterRequest request) {
        return new ResponseEntity<>(new CompanyResponse(companyService.registerCompany(request)), HttpStatus.CREATED);
    }
}
