package com.masai.repositery;

import com.masai.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface SupplierRepo extends JpaRepository<Supplier,Integer> {

    public Optional<Supplier> findByCompanyName(String companyName);

}
