package com.masai.model;

import io.swagger.models.auth.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shipper {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shipper_seq")
    @SequenceGenerator(name = "shipper_seq")
    private Integer shipperID;

    @NotNull
    private String companyName;

    @NotNull
    @Size(min = 10,max = 10, message = "Please provide valid contact number")
    private String contactNo;

}
