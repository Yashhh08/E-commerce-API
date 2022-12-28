package com.masai.service;

import com.masai.exception.*;
import com.masai.model.*;
import com.masai.model.DTO.*;
import com.masai.repositery.*;
import org.aspectj.weaver.ast.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.util.*;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PaymentRepo paymentRepo;

    @Autowired
    private ShipperRepo shipperRepo;

    @Autowired
    private OrderDetailsRepo orderDetailsRepo;

    @Autowired
    private CartRepo cartRepo;

    @Override
    public Cart addToCart(String username, Integer paymentID, Integer shipperID) throws CustomException {

        List<OrderDetails> orderDetails = orderDetailsRepo.findByUser_Username(username);

        List<OrderDetails> currentOrders = new ArrayList<>();

        for(OrderDetails od : orderDetails)
        {
            if(od.getCart()==null)
            {
                currentOrders.add(od);
            }
        }

        if(currentOrders.isEmpty())
        {
            throw new CustomException("Your cart is empty");
        }

        User user = userRepo.findByUsername(username).orElseThrow(()->new CustomException("No user found with username : "+username));

        Payment payment = paymentRepo.findById(paymentID).orElseThrow(()->new CustomException("No payment method found with id : "+paymentID));

        Shipper shipper = shipperRepo.findById(shipperID).orElseThrow(()->new CustomException("No Shipper found with id : "+shipperID));

        Cart cart = new Cart();

        Integer max = cartRepo.countMax();

        if(max==null)
        {
            max=0;
        }

        cart.setCartID(max+1);
        cart.setOrderDetails(currentOrders);
        cart.setOrderDate(LocalDate.now());
        cart.setShipDate(LocalDate.now().plusDays(2));
        cart.setDeliveryDate(LocalDate.now().plusDays(6));
        cart.setTotalOrderAmount(cart.getTotalPrice());
        cart.setUser(user);
        cart.setPayment(payment);
        cart.setShipper(shipper);

        Cart currCart = cartRepo.save(cart);

        currentOrders.forEach(order->
        {
            order.setCart(currCart);
            orderDetailsRepo.save(order);
        });

        return currCart;

    }
}
