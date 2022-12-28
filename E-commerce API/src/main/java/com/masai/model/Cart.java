package com.masai.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.springframework.stereotype.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.*;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartID;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate orderDate;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Future
    private LocalDate shipDate;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Future
    private LocalDate deliveryDate;

    @NotNull
    private Double totalOrderAmount;

    @ManyToOne
    private User user;

    @ManyToOne
    private Payment payment;

    @ManyToOne
    private Shipper shipper;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "cart")
    private List<OrderDetails> orderDetails;

    @Transient
    public Double getTotalPrice(){

        Double sum=0.0;

        List<OrderDetails> list = getOrderDetails();

        for(OrderDetails od : list)
        {
            sum += od.getTotalPrice();
        }

        return sum;

    }

    @Transient
    public Integer getTotalCount(){
        return this.orderDetails.size();
    }

}
