package com.masai.repositery;

import com.masai.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface CartRepo extends JpaRepository<Cart,Integer> {

    @Query("select max(c.cartID) from Cart as c")
    Integer countMax();

}
