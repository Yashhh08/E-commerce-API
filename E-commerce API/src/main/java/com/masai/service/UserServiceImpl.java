package com.masai.service;

import com.masai.exception.*;
import com.masai.model.*;
import com.masai.model.DTO.*;
import com.masai.repositery.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User registerUser(User user) throws CustomException {

        Optional<User> found = userRepo.findByUsername(user.getUsername());

        if(found.isPresent())
        {
            throw new CustomException("User already exist with this username please choose different username");
        }
        else
        {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole("ROLE_"+user.getRole().toUpperCase());

            return userRepo.save(user);
        }

    }

    @Override
    public List<User> getAllUsers() throws CustomException {

        List<User> users = userRepo.findAll();

        if(users.isEmpty())
        {
            throw new CustomException("No users found");
        }
        else
        {
            return users;
        }

    }

    @Override
    public User getUserByUsername(String username) throws CustomException {

        User user = userRepo.findByUsername(username).orElseThrow(()->new CustomException("User not found with username " + username));

        return user;


    }

    @Override
    public String deleteUser(String username) throws CustomException {

        User user = userRepo.findByUsername(username).orElseThrow(()->new CustomException("User not found with username " + username));

        userRepo.delete(user);

        return "User "+username+" deleted successfully";

    }

    @Override
    public String updateUserPassword(UserDTO userDTO) throws CustomException {

        User user = userRepo.findByUsername(userDTO.getUsername()).orElseThrow(()->new CustomException("User not found with username " + userDTO.getUsername()));

        if(passwordEncoder.matches(userDTO.getPassword(),user.getPassword()))
        {
            user.setPassword(passwordEncoder.encode(userDTO.getNewPassword()));

            userRepo.save(user);

            return "Password updated for : "+userDTO.getUsername();
        }
        else
        {
            throw new CustomException("Wrong password");
        }

    }
}
