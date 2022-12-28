package com.masai.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userID;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Size(min = 10, max = 10, message = "provide valid mobile number")
    private String mobileNo;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String city;

    @NotNull
    private String state;

    @NotNull
    private String country;

    @NotNull
    private Integer postalCode;

    @NotNull
    @Size(min = 4, message = "username should be atleast of size 4")
    private String username;

    @NotNull
    @Size(min = 4, message = "password should be atleast of size 4")
    private String password;

    @NotNull
    private String role;

}
