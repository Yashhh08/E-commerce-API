package com.masai.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addressID;

    @NotNull
    private String streetAddress;

    @NotNull
    private String city;

    @NotNull
    private String state;

    @NotNull
    @Size(min = 6, max = 6, message = "provide valid postal code")
    private String postalCode;

    @NotNull
    private String country;

    @JsonIgnore
    @ManyToOne
    User user;

}
