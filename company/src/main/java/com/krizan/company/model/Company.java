package com.krizan.company.model;

import com.krizan.company.vo.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Company {

    @Id
    @SequenceGenerator(
            name = "company_id_sequence",
            sequenceName = "company_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "company_id_sequence"
    )
    private Long id;
    private String name;
    @Embedded
    private Address address;
    private Integer numberOfEmployees;
}
