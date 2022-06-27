package com.krizan.company.dto;

import com.krizan.company.model.Address;

public record CompanyRegisterRequest(
        String name,
        Address address) {
}
