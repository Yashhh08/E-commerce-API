package com.masai.service;

import com.masai.exception.*;
import com.masai.model.*;

import java.util.*;

public interface CategoryService {

    public Category addCategory(Category category) throws CustomException;

    public Category deleteCategory(String categoryName) throws CustomException;

    public List<Category> getAllCategories() throws CustomException;

    public Category getCategory(String categoryName) throws CustomException;

}
