package com.masai.service;

import com.masai.exception.*;
import com.masai.model.*;
import com.masai.repositery.*;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService{

    @Autowired
    private OrderDetailsRepo orderDetailsRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private SupplierRepo supplierRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public OrderDetails addOrderDetails(String username ,Integer productID, Integer qty, Integer supplierID) throws CustomException {

        User user = userRepo.findByUsername(username).orElseThrow(()->new CustomException("No user found with username : "+username));

        OrderDetails found = orderDetailsRepo.findByProductIdHavingNullCart(productID);

        Optional<Product> product = productRepo.findById(productID);

        Optional<Supplier> supplier = supplierRepo.findById(supplierID);

        if(found==null || found.getCart()!=null)
        {
            OrderDetails orderDetails = new OrderDetails();

            Integer max = orderDetailsRepo.countMax();

            if(max==null)
            {
                max=0;
            }

            orderDetails.setOrderDetailsID(max+1);

            System.out.println("no order details found for product id :"+productID);

            if(product.isEmpty())
            {
                throw new CustomException("No product found with id : "+productID);
            }

            if(supplier.isEmpty())
            {
                throw new CustomException("No supplier found with id : "+supplierID);
            }

            orderDetails.setUser(user);
            orderDetails.setProduct(product.get());
            orderDetails.setQuantity(qty);
            orderDetails.setSupplier(supplier.get());

            product.get().setQuantity(product.get().getQuantity() - orderDetails.getQuantity());

            productRepo.save(product.get());

            return orderDetailsRepo.save(orderDetails);
        }
        else
        {
            found.setQuantity(found.getQuantity()+qty);

            product.get().setQuantity(product.get().getQuantity() - qty);

            productRepo.save(product.get());

            return orderDetailsRepo.save(found);
        }

    }

    @Override
    public OrderDetails deleteOrderDetails(Integer orderDetailsID) throws CustomException {

        OrderDetails orderDetails = orderDetailsRepo.findById(orderDetailsID).orElseThrow(()-> new CustomException("No orderDetails found for id : "+ orderDetailsID));

        Optional<Product> product = productRepo.findById(orderDetails.getProduct().getProductID());

        product.get().setQuantity(product.get().getQuantity() + orderDetails.getQuantity());

        productRepo.save(product.get());

        orderDetailsRepo.delete(orderDetails);

        return orderDetails;

    }

    @Override
    public OrderDetails findOrderDetailsById(Integer orderDetailsID) throws CustomException {

        OrderDetails orderDetails = orderDetailsRepo.findById(orderDetailsID).orElseThrow(()-> new CustomException("No orderDetails found for id : "+ orderDetailsID));

        return orderDetails;

    }

    @Override
    public List<OrderDetails> findAllOrderDetailsByUsername(String username) throws CustomException {

        User user = userRepo.findByUsername(username).orElseThrow(()->new CustomException("No user found with username : "+username));

        List<OrderDetails> orderDetailsList = orderDetailsRepo.findByUser_Username(username);

        if(orderDetailsList.isEmpty())
        {
            throw new CustomException("No orderDetails found");
        }
        else
        {
            return orderDetailsList;
        }

    }


    @Override
    public List<OrderDetails> findOrderDetailsByUsername(String username) throws CustomException {

        User user = userRepo.findByUsername(username).orElseThrow(()->new CustomException("No user found with username : "+username));

        List<OrderDetails> orderDetailsList = orderDetailsRepo.findByUser_Username(username);

        List<OrderDetails> currentOrders = new ArrayList<>();

        if(orderDetailsList.isEmpty())
        {
            throw new CustomException("No orderDetails found");
        }
        else
        {
            for(OrderDetails curr : orderDetailsList)
            {
                if(curr.getCart()==null)
                {
                    currentOrders.add(curr);
                }
            }
        }

        if(currentOrders.isEmpty())
        {
            throw new CustomException("No current orders found");
        }
        else
        {
            return currentOrders;
        }

    }

    @Override
    public OrderDetails findOrderDetailsByProductID(Integer productID) throws CustomException {

        OrderDetails details = orderDetailsRepo.findByProductId(productID);

        if(details==null)
        {
            throw new CustomException("No orderDetails found for product id : "+productID);
        }

        return details;

    }

    @Override
    public Integer countMax() throws CustomException {

        Integer max = orderDetailsRepo.countMax();

        if(max==null)
        {
            return 0;
        }

        return max;

    }
}
