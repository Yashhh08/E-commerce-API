package com.masai.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer supplierID;

    @NotNull
    private String companyName;

    @NotNull
    private String city;

    @NotNull
    private String state;

    @NotNull
    private String country;

    @NotNull
    private Integer postalCode;

    @NotNull
    @Size(min = 10,max = 10, message = "please provide valid contact number")
    private String contactNo;

    @NotNull
    @Email
    private String email;

}
