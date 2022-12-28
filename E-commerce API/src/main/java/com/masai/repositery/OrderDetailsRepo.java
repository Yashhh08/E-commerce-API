package com.masai.repositery;

import com.masai.exception.*;
import com.masai.model.*;
import org.aspectj.weaver.ast.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface OrderDetailsRepo extends JpaRepository<OrderDetails,Integer> {

    @Query("select o from OrderDetails o where o.product.productID = ?1")
    public OrderDetails findByProductId(Integer productID);

    List<OrderDetails> findByUser_Username(String username);

    @Query("select max(o.orderDetailsID) from OrderDetails as o")
    Integer countMax();

    @Query("select o from OrderDetails o where o.product.productID = ?1 and o.cart.cartID is null")
    OrderDetails findByProductIdHavingNullCart(Integer productID);



//    @Query("select o from OrderDetails o where o.product.productID = ?1")
//    OrderDetails findByProductIdJPA(Integer productID);

}
