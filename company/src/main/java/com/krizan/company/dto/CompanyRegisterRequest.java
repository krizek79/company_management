package com.krizan.company.dto;

import com.krizan.company.vo.Address;

public record CompanyRegisterRequest(
        String name,
        Address address) {
}
