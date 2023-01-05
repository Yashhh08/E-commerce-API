package com.masai.controller;

import com.masai.exception.*;
import com.masai.model.*;
import com.masai.security.*;
import com.masai.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/")
    public String welcome(){
        return "Welcome to Ecommerce Application";
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user) throws CustomException {

        User registered = userService.registerUser(user);

        return new ResponseEntity<>(registered,HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public String generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{

        try
        {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword())
            );
        }
        catch (Exception e)
        {
            throw new Exception("Invalid username/password");
        }

        return jwtUtil.generateToken(jwtRequest.getUsername());

    }

}
