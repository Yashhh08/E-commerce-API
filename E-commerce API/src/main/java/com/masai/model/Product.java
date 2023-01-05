package com.masai.model;

import com.fasterxml.jackson.annotation.*;
import com.masai.model.DTO.*;
import lombok.*;
import org.hibernate.validator.constraints.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer productID;

    @NotNull
    private String productName;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull
    private String brand;

    @NotNull
//    @URL
    private String imageURL;

    @NotNull
    private Integer salePrice;

    @NotNull
    private Integer marketPrice;

    @NotNull
    private String subCategory;

    private String type;

    @NotNull
    private Integer quantity;

    @ManyToOne
    @JsonIgnore
    private Category category;

}
