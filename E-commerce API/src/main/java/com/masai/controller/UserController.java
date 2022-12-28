package com.masai.controller;

import com.masai.exception.*;
import com.masai.model.*;
import com.masai.model.DTO.*;
import com.masai.security.*;
import com.masai.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

//    USER SERVICE

    @PatchMapping("/updatePassword")
    public ResponseEntity<String> updateUserPassword(@Valid @RequestBody UserDTO userDTO) throws CustomException{

        String updated = userService.updateUserPassword(userDTO);

        return new ResponseEntity<>(updated,HttpStatus.OK);

    }


//    CATEGORY SERVICE

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public ResponseEntity<List<Category>> getAllCategories() throws CustomException{

        List<Category> categories = categoryService.getAllCategories();

        return new ResponseEntity<>(categories,HttpStatus.OK);

    }

    @GetMapping("/category/{categoryName}")
    public ResponseEntity<Category> getCategory(@PathVariable("categoryName") String categoryName) throws CustomException{

        Category category = categoryService.getCategory(categoryName);

        return new ResponseEntity<>(category,HttpStatus.OK);

    }



//    PRODUCT SERVICE

    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    public ResponseEntity<List<Product>> findAllProducts() throws CustomException{

        List<Product> products = productService.findAll();

        return new ResponseEntity<>(products,HttpStatus.OK);

    }


    @GetMapping("/product/{productName}")
    public ResponseEntity<Product> findProductByName(@PathVariable("productName") String productName) throws CustomException{

        Product product = productService.findByName(productName);

        return new ResponseEntity<>(product,HttpStatus.OK);

    }

    @GetMapping("/product/like/{productName}")
    public ResponseEntity<List<Product>> findProductsByNameContaining(@PathVariable("productName") String productName) throws CustomException{

        List<Product> products = productService.findProductContaining(productName);

        return new ResponseEntity<>(products,HttpStatus.OK);

    }

    @GetMapping("/product/category/{categoryName}")
    public ResponseEntity<List<Product>> findProductsByCategoryName(@PathVariable("categoryName") String categoryName) throws CustomException{

        List<Product> products = productService.findByCategoryName(categoryName);

        return new ResponseEntity<>(products,HttpStatus.OK);

    }

    @GetMapping("/product/subCategory/{categoryName}")
    public ResponseEntity<List<Product>> findProductsBySubCategory(@PathVariable("categoryName") String categoryName) throws CustomException{

        List<Product> products = productService.findBySubCategory(categoryName);

        return new ResponseEntity<>(products,HttpStatus.OK);

    }

    @GetMapping("/product/brand/{brand}")
    public ResponseEntity<List<Product>> findProductsByBrand(@PathVariable("brand") String brand) throws CustomException{

        List<Product> products = productService.findByBrand(brand);

        return new ResponseEntity<>(products,HttpStatus.OK);

    }

    @GetMapping("/product/sort/{field}")
    public ResponseEntity<List<Product>> findAllProductsWithSorting(@PathVariable("field") String field) throws CustomException{

        List<Product> products = productService.findAllWithSorting(field);

        return new ResponseEntity<>(products,HttpStatus.OK);

    }

    @GetMapping("/product/pagination/{pageNumber}/{pageSize}")
    public ResponseEntity<Page<Product>> findAllProductsWithPagination(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize) throws CustomException{

        Page<Product> products = productService.findAllWithPagination(pageNumber,pageSize);

        return new ResponseEntity<>(products,HttpStatus.OK);

    }

    @GetMapping("/product/paginationSorting/{pageNumber}/{pageSize}/{field}")
    public ResponseEntity<Page<Product>> findAllProductsWithPaginationAndSorting(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize, @PathVariable("field") String field) throws CustomException {

        Page<Product> products = productService.findAllWithPaginationAndSorting(pageNumber,pageSize,field);

        return new ResponseEntity<>(products,HttpStatus.OK);

    }


// ORDER DETAILS SERVICE

    @Autowired
    private OrderDetailsService orderDetailsService;

    @PostMapping("/orderDetails/{productID}/{qty}/{supplierID}")
    public ResponseEntity<OrderDetails> addOrderDetails(@RequestHeader("Authorization") String auth ,@PathVariable Integer productID, @PathVariable Integer qty, @PathVariable Integer supplierID) throws CustomException{

        String token = auth.substring(7);

        String username = jwtUtil.extractUsername(token);

        OrderDetails added = orderDetailsService.addOrderDetails(username,productID,qty,supplierID);

        return new ResponseEntity<>(added,HttpStatus.CREATED);

    }

    @DeleteMapping("/orderDetails/{orderDetailsID}")
    public ResponseEntity<OrderDetails> deleteOrderDetails(@PathVariable Integer orderDetailsID) throws CustomException{

        OrderDetails orderDetails = orderDetailsService.deleteOrderDetails(orderDetailsID);

        return new ResponseEntity<>(orderDetails,HttpStatus.OK);

    }

    @GetMapping("/orderDetails/{orderDetailsID}")
    public ResponseEntity<OrderDetails> findOrderDetailsById(@PathVariable Integer orderDetailsID) throws CustomException{

        OrderDetails orderDetails = orderDetailsService.findOrderDetailsById(orderDetailsID);

        return new ResponseEntity<>(orderDetails,HttpStatus.OK);

    }

    @GetMapping("/orderDetails")
    public ResponseEntity<List<OrderDetails>> findAllOrderDetailsForUser(@RequestHeader("Authorization") String auth) throws CustomException{

        String token = auth.substring(7);

        String username = jwtUtil.extractUsername(token);

        List<OrderDetails> list = orderDetailsService.findOrderDetailsByUsername(username);

        return new ResponseEntity<>(list,HttpStatus.OK);

    }

//    For testing only

//    @GetMapping("/orderDetails/product/{productID}")
//    public OrderDetails findOrderDetailsByProductID(Integer productID) throws CustomException{
//
//        OrderDetails orderDetails = orderDetailsService.findOrderDetailsByProductID(productID);
//
//        return orderDetails;
//
//    }


//  CART SERVICES

    @Autowired
    private CartService cartService;


    @PostMapping("/cart/{paymentID}/{shipperID}")
    public ResponseEntity<Cart> addToCart(@RequestHeader("Authorization") String auth, @PathVariable Integer paymentID, @PathVariable Integer shipperID) throws CustomException {

        String token = auth.substring(7);

        String username = jwtUtil.extractUsername(token);

        Cart cart = cartService.addToCart(username,paymentID,shipperID);

        return new ResponseEntity<>(cart,HttpStatus.CREATED);

    }

}

