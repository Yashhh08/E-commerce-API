package com.masai.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderDetailsID;

    @OneToOne
    private Product product;

    @NotNull
    private Integer quantity;

    @ManyToOne
    private Supplier supplier;

    @ManyToOne
    @JsonIgnore
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Cart cart;

    @Transient
    public Double getTotalPrice(){
        return (double) (getProduct().getSalePrice() * getQuantity());
    }

}
