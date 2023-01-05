package com.masai.service;

import com.masai.exception.*;
import com.masai.model.*;
import com.masai.model.DTO.*;

public interface CartService {

    public Cart addToCart(String username, Integer paymentID, Integer shipperID) throws CustomException;

}
