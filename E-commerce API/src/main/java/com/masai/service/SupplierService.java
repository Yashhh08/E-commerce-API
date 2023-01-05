package com.masai.service;

import com.masai.exception.*;
import com.masai.model.*;

import java.util.*;

public interface SupplierService {

    public Supplier addSupplier(Supplier supplier) throws CustomException;

    public Supplier deleteSupplier(Integer id) throws CustomException;

    public Supplier updateSupplier(Integer id, Supplier supplier) throws CustomException;

    public List<Supplier> findAll() throws CustomException;

    public Supplier findById(Integer id) throws CustomException;

}
