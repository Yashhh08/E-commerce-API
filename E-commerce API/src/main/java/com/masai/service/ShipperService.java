package com.masai.service;

import com.masai.exception.*;
import com.masai.model.*;

import java.util.*;

public interface ShipperService {

    public Shipper add(Shipper shipper) throws CustomException;

    public Shipper deleteShipper(Integer id) throws CustomException;

    public Shipper update(Integer id , Shipper shipper) throws CustomException;

    public List<Shipper> findAllShippers() throws CustomException;

    public Shipper findById(Integer id) throws CustomException;

}
