package com.masai.controller;

import com.masai.exception.*;
import com.masai.model.*;
import com.masai.model.DTO.*;
import com.masai.repositery.*;
import com.masai.security.*;
import com.masai.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private JwtUtil jwtUtil;

//    USER SERVICE
    @Autowired
    private UserService userService;

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() throws CustomException{

        List<User> users = userService.getAllUsers();

        return new ResponseEntity<>(users,HttpStatus.OK);

    }

    @GetMapping("/getUser/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) throws CustomException{

        User user = userService.getUserByUsername(username);

        return new ResponseEntity<>(user,HttpStatus.OK);

    }

    @DeleteMapping("/deleteUser/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable("username") String username) throws CustomException{

        String deleted = userService.deleteUser(username);

        return new ResponseEntity<>(deleted,HttpStatus.OK);

    }


//    CATEGORY SERVICE

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/category")
    public ResponseEntity<Category> addCategory(@Valid @RequestBody Category category) throws CustomException{

        Category added = categoryService.addCategory(category);

        return new ResponseEntity<>(added,HttpStatus.CREATED);

    }

    @DeleteMapping("/category/{categoryName}")
    public ResponseEntity<Category> deleteCategory(@PathVariable("categoryName") String categoryName) throws CustomException{

        Category deleted = categoryService.deleteCategory(categoryName);

        return new ResponseEntity<>(deleted,HttpStatus.OK);

    }

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

    @PostMapping("/product/{categoryName}")
    public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product, @PathVariable("categoryName") String categoryName) throws CustomException{

        Product added = productService.add(product,categoryName);

        return new ResponseEntity<>(added,HttpStatus.CREATED);

    }

    @DeleteMapping("/product")
    public ResponseEntity<Product> deleteProduct(@RequestParam String productName) throws CustomException{

        Product deleted = productService.delete(productName);

        return new ResponseEntity<>(deleted,HttpStatus.OK);

    }

    @DeleteMapping("/product/id")
    public ResponseEntity<Product> deleteProductById(@RequestParam Integer productID) throws CustomException{

        Product product = productService.delete(productID);

        return new ResponseEntity<>(product,HttpStatus.OK);

    }

    @PutMapping("/product/{productName}")
    public ResponseEntity<Product> updateProduct(@PathVariable("productName") String productName, @Valid @RequestBody Product product) throws CustomException{

        Product updated = productService.update(productName,product);

        return new ResponseEntity<>(updated,HttpStatus.OK);

    }

    @GetMapping("/product")
    public ResponseEntity<List<Product>> findAllProducts() throws CustomException{

        List<Product> products = productService.findAll();

        return new ResponseEntity<>(products,HttpStatus.OK);

    }


    @GetMapping("/product/{productName}")
    public ResponseEntity<Product> findByProductName(@PathVariable("productName") String productName) throws CustomException{

        Product product = productService.findByName(productName);

        return new ResponseEntity<>(product,HttpStatus.OK);

    }

    @GetMapping("/product/like/{productName}")
    public ResponseEntity<List<Product>> findByProductNameContaining(@PathVariable("productName") String productName) throws CustomException{

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


//  PAYMENT SERVICE

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payment")
    public ResponseEntity<Payment> addPaymentMethod(@Valid @RequestBody Payment payment) throws CustomException{

        Payment added = paymentService.add(payment);

        return new ResponseEntity<>(added,HttpStatus.CREATED);

    }

    @DeleteMapping("/payment/{paymentId}")
    public ResponseEntity<Payment> deletePaymentMethod(@PathVariable("paymentId") Integer id) throws CustomException{

        Payment payment = paymentService.delete(id);

        return new ResponseEntity<>(payment,HttpStatus.OK);

    }

    @GetMapping("/payment")
    public ResponseEntity<List<Payment>> getAllPaymentMethods() throws CustomException{

        List<Payment> payments = paymentService.getAll();

        return new ResponseEntity<>(payments,HttpStatus.OK);

    }

    @GetMapping("/payment/{paymentId}")
    public ResponseEntity<Payment> getPaymentMethodByID(@PathVariable("paymentId") Integer id) throws CustomException{

        Payment payment = paymentService.getByID(id);

        return new ResponseEntity<>(payment,HttpStatus.OK);

    }


//    SHIPPER SERVICE

    @Autowired
    private ShipperService shipperService;

    @PostMapping("/shipper")
    public ResponseEntity<Shipper> addShipper(@Valid @RequestBody Shipper shipper) throws CustomException{

        Shipper added = shipperService.add(shipper);

        return new ResponseEntity<>(added,HttpStatus.CREATED);

    }

    @DeleteMapping("/shipper/{id}")
    public ResponseEntity<Shipper> deleteShipper(@PathVariable("id") Integer id) throws CustomException{

        Shipper shipper = shipperService.deleteShipper(id);

        return new ResponseEntity<>(shipper,HttpStatus.OK);

    }

    @PutMapping("/shipper/{id}")
    public ResponseEntity<Shipper> updateShipper(@PathVariable("id") Integer id ,@Valid @RequestBody Shipper shipper) throws CustomException{

        Shipper updated = shipperService.update(id, shipper);

        return new ResponseEntity<>(updated,HttpStatus.OK);

    }

    @GetMapping("/shipper")
    public ResponseEntity<List<Shipper>> findAllShippers() throws CustomException{

        List<Shipper> shippers = shipperService.findAllShippers();

        return new ResponseEntity<>(shippers,HttpStatus.OK);

    }

    @GetMapping("/shipper/{id}")
    public ResponseEntity<Shipper> findShipperById(@PathVariable("id") Integer id) throws CustomException{

        Shipper shipper = shipperService.findById(id);

        return new ResponseEntity<>(shipper,HttpStatus.OK);

    }


//    SUPPLIER SERVICE

    @Autowired
    private SupplierService supplierService;

    @PostMapping("/supplier")
    public ResponseEntity<Supplier> addSupplier(@Valid @RequestBody Supplier supplier) throws CustomException{

        Supplier added = supplierService.addSupplier(supplier);

        return new ResponseEntity<>(added,HttpStatus.CREATED);

    }

    @DeleteMapping("/supplier/{id}")
    public ResponseEntity<Supplier> deleteSupplier(@PathVariable("id") Integer id) throws CustomException{

        Supplier supplier = supplierService.deleteSupplier(id);

        return new ResponseEntity<>(supplier,HttpStatus.OK);

    }

    @PutMapping("/supplier/{id}")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable("id") Integer id, @Valid @RequestBody Supplier supplier) throws CustomException{

        Supplier updated = supplierService.updateSupplier(id, supplier);

        return new ResponseEntity<>(updated,HttpStatus.OK);

    }

    @GetMapping("/supplier")
    public ResponseEntity<List<Supplier>> findAllSuppliers() throws CustomException{

        List<Supplier> suppliers = supplierService.findAll();

        return new ResponseEntity<>(suppliers,HttpStatus.OK);

    }

    @GetMapping("/supplier/{id}")
    public ResponseEntity<Supplier> findSupplierBySupplierId(@PathVariable("id") Integer id) throws CustomException{

        Supplier supplier = supplierService.findById(id);

        return new ResponseEntity<>(supplier,HttpStatus.OK);

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
