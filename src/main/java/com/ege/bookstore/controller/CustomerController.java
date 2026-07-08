package com.ege.bookstore.controller;

import com.ege.bookstore.entity.Customer;
import com.ege.bookstore.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customers")
public class CustomerController {
    private final CustomerService service;
    public CustomerController(CustomerService service){
        this.service=service;
    }
    @GetMapping
    public List<Customer> getAllCustomers(){
        return service.getAllCustomers();
    }
    @GetMapping("/by-username")
    public Customer getCustomerByUsername(@RequestParam String username){
        return service.getCustomerByUsername(username);
    }
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id){
        return service.getCustomerById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer registerCustomer(@RequestBody Customer customer){
        return service.registerCustomer(customer);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomerById(@PathVariable Long id){
        service.deleteCustomerById(id);
    }

}
