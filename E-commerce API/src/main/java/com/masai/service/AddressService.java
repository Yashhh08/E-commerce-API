package com.masai.service;

import com.masai.exception.*;
import com.masai.model.*;

import java.util.*;

public interface AddressService {

    public Address addAddress(String username, Address address) throws CustomException;

    public String deleteAddress(String username, Integer id) throws CustomException;

    public List<Address> findAllAddress(String username) throws CustomException;

}
