package com.masai.service;

import com.masai.exception.*;
import com.masai.model.*;
import org.springframework.data.domain.*;
import org.springframework.security.core.parameters.*;

import java.util.*;

public interface ProductService{

    public Product add(Product product, String categoryName) throws CustomException;

    public Product delete(String productName) throws CustomException;

    public Product delete(Integer productID) throws CustomException;

    public Product update(String productName, Product product) throws CustomException;

    public List<Product> findAll() throws CustomException;

    public Product findByName(String productName) throws CustomException;

    public List<Product> findProductContaining(String productName) throws CustomException;

    public List<Product> findByCategoryName(String categoryName) throws CustomException;

    public List<Product> findBySubCategory(String subCategory) throws CustomException;

    public List<Product> findByBrand(String brand) throws CustomException;

    public List<Product> findAllWithSorting(String field) throws CustomException;

    public Page<Product> findAllWithPagination(int pageNumber, int pageSize) throws CustomException;

    public Page<Product> findAllWithPaginationAndSorting(int pageNumber, int pageSize, String field) throws CustomException;

}
