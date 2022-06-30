package com.krizan.company.controller;

import com.krizan.company.dto.*;
import com.krizan.company.service.CompanyService;
import com.krizan.company.vo.Amount;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/companies")
public record CompanyController(CompanyService companyService) {

    @PostMapping
    public ResponseEntity<CompanyResponse> registerCompany(@RequestBody CompanyRegisterRequest request) {
        return new ResponseEntity<>(new CompanyResponse(companyService.registerCompany(request)), HttpStatus.CREATED);
    }

    @PostMapping("/setNumberOfEmployees/{id}")
    public void setNumberOfEmployees(@PathVariable("id") Long id, @RequestBody Amount request) {
        companyService.setNumberOfEmployees(id, request);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CompanyResponse> updateCompany(@PathVariable("id") Long id, @RequestBody CompanyUpdateRequest request) {
        return new ResponseEntity<>(new CompanyResponse(companyService.updateCompany(id, request)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable("id") Long id) {
        companyService.deleteCompany(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> getCompanyById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(new CompanyResponse(companyService.getCompanyById(id)), HttpStatus.OK);
    }

    @GetMapping("/{id}/employees")
    public ResponseEntity<CompanyWithEmployeesResponse> getCompanyWithEmployeesById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(new CompanyWithEmployeesResponse(companyService.getCompanyWithEmployeesById(id)),
                HttpStatus.OK);
    }

    @GetMapping
    public List<CompanyResponse> getAllCompanies() {
        return companyService.getAllCompanies()
                .stream()
                .map(CompanyResponse::new)
                .collect(Collectors.toList());
    }
}
