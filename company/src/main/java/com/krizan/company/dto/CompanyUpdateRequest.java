package com.krizan.company.dto;

import com.krizan.company.model.Address;

public record CompanyUpdateRequest(
        String name,
        Address address) {
}
