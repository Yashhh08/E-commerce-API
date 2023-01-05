package com.masai.service;

import com.masai.exception.*;
import com.masai.model.*;
import com.masai.repositery.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class SupplierServiceImpl implements SupplierService{

    @Autowired
    private SupplierRepo supplierRepo;

    @Override
    public Supplier addSupplier(Supplier supplier) throws CustomException {

        Optional<Supplier> exist = supplierRepo.findByCompanyName(supplier.getCompanyName());

        if(exist.isPresent())
        {
            throw new CustomException(supplier.getCompanyName().toUpperCase()+" already exist");
        }
        else
        {
            return supplierRepo.save(supplier);
        }

    }

    @Override
    public Supplier deleteSupplier(Integer id) throws CustomException {

        Supplier supplier = supplierRepo.findById(id).orElseThrow(()->new CustomException("Not found any supplier with id "+id));

        supplierRepo.delete(supplier);

        return supplier;

    }

    @Override
    public Supplier updateSupplier(Integer id, Supplier supplier) throws CustomException {

        Supplier exist = supplierRepo.findById(id).orElseThrow(()->new CustomException("Not found any supplier with id "+id));

        supplier.setSupplierID(exist.getSupplierID());

        return supplierRepo.save(supplier);

    }

    @Override
    public List<Supplier> findAll() throws CustomException {

        List<Supplier> suppliers = supplierRepo.findAll();

        if(suppliers.isEmpty())
        {
            throw new CustomException("No supplier found");
        }
        else
        {
            return suppliers;
        }

    }

    @Override
    public Supplier findById(Integer id) throws CustomException {

        Supplier exist = supplierRepo.findById(id).orElseThrow(()->new CustomException("Not found any supplier with id "+id));

        return exist;

    }
}
