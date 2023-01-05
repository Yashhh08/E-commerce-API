package com.masai.repositery;

import com.masai.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface PaymentRepo extends JpaRepository<Payment,Integer> {

    public Optional<Payment> findByPaymentMethod(String method);

}
