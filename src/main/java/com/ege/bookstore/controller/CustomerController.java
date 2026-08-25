package com.ege.bookstore.controller;

import com.ege.bookstore.dto.CustomerRequest;
import com.ege.bookstore.dto.CustomerResponse;
import com.ege.bookstore.entity.Customer;
import com.ege.bookstore.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService service;
    public CustomerController(CustomerService service){
        this.service=service;
    }
    @GetMapping
    public List<CustomerResponse> getAllCustomers(){
        return service.getAllCustomers();
    }
    @GetMapping("/by-username")
    public CustomerResponse getCustomerByUsername(@RequestParam String username){
        return service.getCustomerByUsername(username);
    }
    @GetMapping("/{id}")
    public CustomerResponse getCustomerById(@PathVariable Long id){
        return service.getCustomerById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse registerCustomer(@Valid @RequestBody CustomerRequest customer){
        return service.registerCustomer(customer);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomerById(@PathVariable Long id){
        service.deleteCustomerById(id);
    }

    @PutMapping("/{id}")
    public CustomerResponse updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerRequest customer){
        return service.updateCustomer(id, customer);
    }

}
