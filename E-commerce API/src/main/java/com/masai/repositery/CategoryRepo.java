package com.masai.repositery;

import com.masai.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer> {

    public Optional<Category> findByCategoryName(String categoryName);

}
