package com.masai.service;

import com.masai.exception.*;
import com.masai.model.*;
import com.masai.repositery.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public Category addCategory(Category category) throws CustomException {

        Optional<Category> exist = categoryRepo.findByCategoryName(category.getCategoryName());

        if(exist.isPresent())
        {
            throw new CustomException(category.getCategoryName()+" already exist");
        }
        else
        {
            return categoryRepo.save(category);
        }

    }

    @Override
    public Category deleteCategory(String categoryName) throws CustomException {

        Category category = categoryRepo.findByCategoryName(categoryName).orElseThrow(()->new CustomException(categoryName+" does not exist"));

        categoryRepo.delete(category);

        return category;

    }

    @Override
    public List<Category> getAllCategories() throws CustomException {

        List<Category> categories = categoryRepo.findAll();

        if(categories.isEmpty())
        {
            throw new CustomException("not found anything");
        }
        else
        {
            return categories;
        }

    }

    @Override
    public Category getCategory(String categoryName) throws CustomException {

        Category category = categoryRepo.findByCategoryName(categoryName).orElseThrow(()->new CustomException(categoryName+" does not exist"));

        return category;

    }
}
