
# E-commerce Application API 

Electronic commerce or E-commerce is simply the buying and selling of goods and services using the internet, when shopping online. However, the term is often used to describe all of a seller's efforts when selling products directly to consumers online.

This is an individual project, I have worked on the rest API service for E-commerce Application, and this project is done in around 5 days. This API service provides users to see the products and buy them.

This API performs all the fundamental CRUD operations of E-commerce Application platform with user validation at every step.

This project is deployed on AWS.


## Entity Relationship Diagram

![Screenshot (53)](https://user-images.githubusercontent.com/101392090/210071014-9a9213a2-fc9f-43ca-9027-67e21a631235.png)


## Tech Stack

* Java
* Maven
* Hibernate
* Spring 
* Spring Boot
* Spring Data JPA
* Spring Boot Starter Mail
* Spring Security - JWT
* Lombok
* MySQL
* Swagger
* AWS


## Modules

* Auth Module
* User Module
* Admin Module

## Features

* User and Admin authentication & authorization with JWT token using Spring security.

* Admin Features:
    * Admin can get/delete registered users from database.
    * Admin can add/update/delete/get categories,products,shippers,suppliers and payment details.
    * Admin can get all order details and check all cart details.

* User Features:
    * User can register himself or herself on the platform.
    * User can change his password.
    * User can see all the categories and products available.
    * User can add products to cart as well as he can update or delete the order details.
    * User can place the order.
    * After placing the order user will receive Email.



## Run

Run the project

```bash
  http://ecommerce-env-1.eba-3wcfqrzp.ap-south-1.elasticbeanstalk.com
```
Get all products

```bash
  http://ecommerce-env-1.eba-3wcfqrzp.ap-south-1.elasticbeanstalk.com/user/product
```
Get all categories

```bash
  http://ecommerce-env-1.eba-3wcfqrzp.ap-south-1.elasticbeanstalk.com/user/category
```
## Documentation

* POSTMAN documentation 

```bash
  https://documenter.getpostman.com/view/23490425/2s8Z6yYDM7
```
* Swagger documentation

```bash
  http://ecommerce-env-1.eba-3wcfqrzp.ap-south-1.elasticbeanstalk.com/swagger-ui/#/
```

## Swagger UI
* To get the access to swagger documentation you should be a registerd user.

```bash
  http://ecommerce-env-1.eba-3wcfqrzp.ap-south-1.elasticbeanstalk.com/swagger-ui/#/
```


* You can registerd yourself by below api using postman or any other service
```bash
  http://ecommerce-env-1.eba-3wcfqrzp.ap-south-1.elasticbeanstalk.com/register
```

* Fill following details

```bash
//POST METHOD

  {
    "firstName": "John",
    "lastName": "Mathew",
    "username": "john@01",
    "password": "John@123",
    "mobileNo": "8879259789",
    "email": "john@gmail.com",
    "city": "Mumbai",
    "state": "Maharashtra",
    "country": "India",
    "postalCode": 400061,
    "role": ""
}
```
* After registering you have to login by using below api
```bash
  http://ecommerce-env-1.eba-3wcfqrzp.ap-south-1.elasticbeanstalk.com/login
```
* After login you will get a JWT Token id which you have to add in Request Header by key Authorization. Example is shown below:
```bash
 Authorization: Bearer <token>
```

* For Demo purpose you can login via below user
```bash
//POST METHOD

{
    "username":"kunal",
    "password":"kunal"
}
```

<h1 align="center">Thank you for your time and if you have any questions or suggestions please reach out to me on my email.</h1>
