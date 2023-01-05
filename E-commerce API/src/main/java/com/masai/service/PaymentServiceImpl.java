package com.masai.service;

import com.masai.exception.*;
import com.masai.model.*;
import com.masai.repositery.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private PaymentRepo paymentRepo;

    @Override
    public Payment add(Payment payment) throws CustomException {

        Optional<Payment> exist = paymentRepo.findByPaymentMethod(payment.getPaymentMethod());

        if(exist.isPresent())
        {
            throw new CustomException(payment.getPaymentMethod()+" already exist");
        }
        else
        {
            return paymentRepo.save(payment);
        }

    }

    @Override
    public Payment delete(Integer id) throws CustomException {

        Payment payment = paymentRepo.findById(id).orElseThrow(()->new CustomException("No payment method found with id "+id));

        paymentRepo.delete(payment);

        return payment;

    }

    @Override
    public List<Payment> getAll() throws CustomException {

        List<Payment> payments = paymentRepo.findAll();

        if(payments.isEmpty())
        {
            throw new CustomException("No payment method found");
        }
        else
        {
            return payments;
        }

    }

    @Override
    public Payment getByID(Integer id) throws CustomException {

        Payment payment = paymentRepo.findById(id).orElseThrow(()->new CustomException("No payment method found with id "+id));

        return payment;

    }
}
