package com.masai.repositery;

import com.masai.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface ShipperRepo extends JpaRepository<Shipper,Integer> {

    public Optional<Shipper> findByCompanyName(String companyName);

}
