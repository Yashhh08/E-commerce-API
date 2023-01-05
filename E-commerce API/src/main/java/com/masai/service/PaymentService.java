package com.masai.service;

import com.masai.exception.*;
import com.masai.model.*;

import java.util.*;

public interface PaymentService {

    public Payment add(Payment payment) throws CustomException;

    public Payment delete(Integer id) throws CustomException;

    public List<Payment> getAll() throws CustomException;

    public Payment getByID(Integer id) throws CustomException;

}
