package com.masai.service;

import com.masai.exception.*;
import com.masai.model.*;
import com.masai.repositery.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class ShipperServiceImpl implements ShipperService{

    @Autowired
    private ShipperRepo shipperRepo;

    @Override
    public Shipper add(Shipper shipper) throws CustomException {

        Optional<Shipper> exist = shipperRepo.findByCompanyName(shipper.getCompanyName());

        if(exist.isPresent())
        {
            throw new CustomException(shipper.getCompanyName().toUpperCase()+" already exist");
        }
        else
        {
            return shipperRepo.save(shipper);
        }

    }

    @Override
    public Shipper deleteShipper(Integer id) throws CustomException {

        Shipper shipper = shipperRepo.findById(id).orElseThrow(()->new CustomException("No shipper found with id "+id));

        shipperRepo.delete(shipper);

        return shipper;

    }

    @Override
    public Shipper update(Integer id, Shipper shipper) throws CustomException {

        Shipper exist = shipperRepo.findById(id).orElseThrow(()->new CustomException("No shipper found with id "+id));

        shipper.setShipperID(exist.getShipperID());

        return shipperRepo.save(shipper);

    }

    @Override
    public List<Shipper> findAllShippers() throws CustomException {

        List<Shipper> shippers = shipperRepo.findAll();

        if(shippers.isEmpty())
        {
            throw new CustomException("No shipper found");
        }
        else
        {
            return shippers;
        }

    }

    @Override
    public Shipper findById(Integer id) throws CustomException {

        Shipper shipper = shipperRepo.findById(id).orElseThrow(()->new CustomException("No shipper found with id "+id));

        return shipper;

    }
}
