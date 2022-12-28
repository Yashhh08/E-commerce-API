package com.masai.service;

import com.masai.exception.*;
import com.masai.model.*;
import com.masai.model.DTO.*;

import java.util.*;

public interface UserService {

    public User registerUser(User user) throws CustomException;

    public List<User> getAllUsers() throws CustomException;

    public User getUserByUsername(String username) throws CustomException;

    public String deleteUser(String username) throws CustomException;

    public String updateUserPassword(UserDTO userDTO) throws CustomException;

}
