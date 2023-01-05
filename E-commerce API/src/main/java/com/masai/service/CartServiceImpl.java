package com.masai.service;

import com.masai.configuration.*;
import com.masai.exception.*;
import com.masai.model.*;
import com.masai.model.DTO.*;
import com.masai.repositery.*;

//import com.twilio.rest.api.v2010.account.*;
//import com.twilio.type.*;

import org.aspectj.weaver.ast.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.time.format.*;
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

    @Autowired
    private Mail mail;

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

//        MAIL SERVICE

        mail.sendEmail(user.getEmail(),
                "Order placed successfully",
                "Hi "+user.getUsername()+"\n"+
                        "Ordered from E-commerce Application\n"+
                        "Expected delivery by : "+cart.getDeliveryDate().format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))+"\n"+
                        "Your package will be delivered between 7:00AM and 10:00PM by our Delivery Agent. You can pay Rs."+cart.getTotalPrice()+" digitally via SMS Pay Link sent by the Delivery Agent, or by cash.\n" +
                        "To ensure your safety, the Delivery Agent will drop the package at your doorstep, ring the doorbell and then move back to maintain adequate distance while waiting for you to collect your package.\n" +
                        "Thank You for choosing Ecommerce Application."
        );

////        SMS SERVICE
//
//        final String ACCOUNT_SID = "ACb5e9f023f81cc2bcf7842577101fc7b9";
//        final String AUTH_TOKEN = "9b6ab608d8205104b7b741766ea3f584";
//        final String SendNumber = "+17203304650";
//
//        Message message = Message.creator(
//
//                new PhoneNumber(user.getMobileNo()),
//                new PhoneNumber(SendNumber),
//
//                "Order placed successfully from E-commerce Application"
//        ).create();
//
//        System.out.println(message.getSid());

        return currCart;

    }
}
