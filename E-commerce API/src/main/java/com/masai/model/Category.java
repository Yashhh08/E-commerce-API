package com.masai.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.springframework.beans.factory.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer categoryID;

    @NotNull
    private String categoryName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private List<Product> products = new ArrayList<>();

}
