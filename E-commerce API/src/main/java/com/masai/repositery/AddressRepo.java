package com.masai.repositery;

import com.masai.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface AddressRepo extends JpaRepository<Address,Integer> {

    List<Address> findByUser_Username(String username);

}
