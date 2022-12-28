package com.masai.service;

import com.masai.exception.*;
import com.masai.model.*;
import com.masai.repositery.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.security.core.parameters.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public Product add(Product product, String categoryName) throws CustomException {

        Optional<Product> productExist = productRepo.findByProductName(product.getProductName());

        if(productExist.isPresent())
        {
            throw new CustomException("product "+product.getProductName().toUpperCase()+" already exist");
        }
        else
        {
            Category category = categoryRepo.findByCategoryName(categoryName).orElseThrow(()->new CustomException("Category "+categoryName.toUpperCase()+" does not exist"));

            category.getProducts().add(product);

            product.setCategory(category);

            return productRepo.save(product);
        }

    }

    @Override
    public Product delete(String productName) throws CustomException {

        Product product = productRepo.findByProductName(productName).orElseThrow(()->new CustomException(productName+" does not exist"));

        productRepo.delete(product);

        return product;

    }

    @Override
    public Product delete(Integer productID) throws CustomException {

        Product product = productRepo.findById(productID).orElseThrow(()->new CustomException("No product found with id : "+productID));

        productRepo.delete(product);

        return product;

    }

    @Override
    public Product update(String productName, Product product) throws CustomException {

        Product exist = productRepo.findByProductName(productName).orElseThrow(()->new CustomException(productName+" does not exist"));

        product.setProductID(exist.getProductID());

        Optional<Category> category = categoryRepo.findById(exist.getCategory().getCategoryID());

        if(category.isPresent())
        {
            product.setCategory(category.get());
        }
        else
        {
            throw new CustomException("Category not found");
        }

        return productRepo.save(product);

    }

    @Override
    public List<Product> findAll() throws CustomException {

        List<Product> products = productRepo.findAll();

        if(products.isEmpty())
        {
            throw new CustomException("No products found");
        }
        else
        {
            return products;
        }

    }

    @Override
    public Product findByName(String productName) throws CustomException {

        Product exist = productRepo.findByProductName(productName).orElseThrow(()->new CustomException(productName+" does not exist"));

        return exist;

    }

    @Override
    public List<Product> findProductContaining(String productName) throws CustomException {

        List<Product> products = productRepo.findByproductNameContaining(productName);

        if(products.isEmpty())
        {
            throw new CustomException("No product found");
        }
        else
        {
            return products;
        }

    }

    @Override
    public List<Product> findByCategoryName(String categoryName) throws CustomException {

        Category category = categoryRepo.findByCategoryName(categoryName).orElseThrow(()->new CustomException("Category "+categoryName.toUpperCase()+" does not exist"));

        List<Product> products = productRepo.findByCategory(category);

        if(products.isEmpty())
        {
            throw new CustomException("No product found");
        }
        else
        {
            return products;
        }

    }

    @Override
    public List<Product> findBySubCategory(String categoryName) throws CustomException {

        List<Product> products = productRepo.findBySubCategory(categoryName);

        if(products.isEmpty())
        {
            throw new CustomException("No products found in "+categoryName.toUpperCase());
        }
        else
        {
            return products;
        }

    }

    @Override
    public List<Product> findByBrand(String brand) throws CustomException {

        List<Product> products = productRepo.findByBrand(brand);

        if(products.isEmpty())
        {
            throw new CustomException("No products found in "+brand.toUpperCase());
        }
        else
        {
            return products;
        }

    }

    @Override
    public List<Product> findAllWithSorting(String field) throws CustomException {

        List<Product> products = productRepo.findAll(Sort.by(Sort.Direction.ASC,field));

        return products;

    }

    @Override
    public Page<Product> findAllWithPagination(int pageNumber, int pageSize) throws CustomException {

        Page<Product> products = productRepo.findAll(PageRequest.of(pageNumber,pageSize));

        return products;

    }

    @Override
    public Page<Product> findAllWithPaginationAndSorting(int pageNumber, int pageSize, String field) throws CustomException {

        Page<Product> products = productRepo.findAll(PageRequest.of(pageNumber,pageSize).withSort(Sort.by(Sort.Direction.ASC,field)));

        return products;

    }
}
