package com.ege.bookstore.service;

import com.ege.bookstore.entity.Customer;
import com.ege.bookstore.exception.CustomerNotFoundException;
import com.ege.bookstore.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerService {
    private final CustomerRepository repository;
    public CustomerService(CustomerRepository repository){
        this.repository=repository;
    }
    public List<Customer> getAllCustomers(){
        return repository.findAll();
    }
    public Customer getCustomerById(Long id){
        return repository.findById(id).orElseThrow(()->new CustomerNotFoundException(id));
    }
    public Customer getCustomerByUsername(String username){
        return repository.findByUsername(username).orElseThrow(()->new CustomerNotFoundException(username));
    }
    public Customer registerCustomer(Customer customer){
        if(repository.existsByUsername(customer.getUsername())){
            throw new IllegalArgumentException("Username: '" + customer.getUsername() + "' is already taken");
        }
        return repository.save(customer);
    }
    public void deleteCustomerById(Long id){
        if(!repository.existsById(id)){
            throw new CustomerNotFoundException(id);
        }
        repository.deleteById(id);
    }

}
