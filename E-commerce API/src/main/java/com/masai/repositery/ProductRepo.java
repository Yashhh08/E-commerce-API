package com.masai.repositery;

import com.masai.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {

    public Optional<Product> findByProductName(String productName);

//    @Query(" select p.productName from Product as p where p.productName like %:productName% ")
//    public List<Product> findProductLike(@Param("productName") String productName);

    public List<Product> findByproductNameContaining(String productName);

    public List<Product> findByCategory(Category category);

    public List<Product> findBySubCategory(String subCategory);

    public List<Product> findByBrand(String brand);

}
