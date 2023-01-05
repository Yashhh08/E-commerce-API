package com.masai.service;

import com.masai.exception.*;
import com.masai.model.*;

import java.util.*;

public interface OrderDetailsService {

    public OrderDetails addOrderDetails(String username, Integer productID, Integer qty, Integer supplierID) throws CustomException;

    public OrderDetails deleteOrderDetails(Integer orderDetailsID) throws CustomException;

    public OrderDetails findOrderDetailsById(Integer orderDetailsID) throws CustomException;

    public List<OrderDetails> findAllOrderDetailsByUsername(String username) throws CustomException;

    public List<OrderDetails> findOrderDetailsByUsername(String username) throws CustomException;

    public OrderDetails findOrderDetailsByProductID(Integer productID) throws CustomException;

    public Integer countMax() throws CustomException;

}
