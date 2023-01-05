package com.masai.service;

import com.masai.exception.*;
import com.masai.model.*;
import com.masai.repositery.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AddressRepo addressRepo;

    @Override
    public Address addAddress(String username ,Address address) throws CustomException {

        User user = userRepo.findByUsername(username).orElseThrow(()->new CustomException("No user found"));

        address.setUser(user);

        return addressRepo.save(address);

    }

    @Override
    public String deleteAddress(String username, Integer id) throws CustomException {

        User user = userRepo.findByUsername(username).orElseThrow(()->new CustomException("No user found"));

        Address address = addressRepo.findById(id).orElseThrow(()->new CustomException("No address found with id : "+id));

        if(address.getUser().getUsername().equals(username))
        {
            addressRepo.delete(address);
        }
        else
        {
            throw new CustomException("Unauthorized");
        }


        return "address with id "+id+" deleted successfully";

    }

    @Override
    public List<Address> findAllAddress(String username) throws CustomException {

        User user = userRepo.findByUsername(username).orElseThrow(()->new CustomException("No user found"));

        List<Address> addresses = addressRepo.findByUser_Username(username);

        if(addresses.isEmpty())
        {
            throw new CustomException("No address found");
        }
        else
        {
            return addresses;
        }

    }
}
