package com.krizan.company.dto;

import com.krizan.company.vo.Address;

public record CompanyUpdateRequest(
        String name,
        Address address) {
}
