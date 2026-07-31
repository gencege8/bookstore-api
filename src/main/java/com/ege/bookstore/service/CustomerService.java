package com.ege.bookstore.service;

import com.ege.bookstore.entity.Customer;
import com.ege.bookstore.exception.CustomerNotFoundException;
import com.ege.bookstore.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CustomerService {
    private final CustomerRepository repository;
    public CustomerService(CustomerRepository repository){
        this.repository=repository;
    }
    @Transactional(readOnly = true)
    public List<Customer> getAllCustomers(){
        return repository.findAll();
    }
    @Transactional(readOnly = true)
    public Customer getCustomerById(Long id){
        return repository.findById(id).orElseThrow(()->new CustomerNotFoundException(id));
    }
    @Transactional(readOnly = true)
    public Customer getCustomerByUsername(String username){
        return repository.findByUsername(username).orElseThrow(()->new CustomerNotFoundException(username));
    }
    @Transactional
    public Customer registerCustomer(Customer customer){
        if(repository.existsByUsername(customer.getUsername())){
            throw new IllegalArgumentException("Username: '" + customer.getUsername() + "' is already taken");
        }
        return repository.save(customer);
    }
    @Transactional
    public Customer updateCustomer(Long id, Customer updated){
        Customer existing = repository.findById(id).orElseThrow(()->new CustomerNotFoundException(id));
        if(!existing.getUsername().equals(updated.getUsername()) && repository.existsByUsername(updated.getUsername())) {
            throw new IllegalArgumentException("Username: '" + updated.getUsername() + "' is already taken");
        }
        existing.setName(updated.getName());
        existing.setSurname(updated.getSurname());
        existing.setUsername(updated.getUsername());
        return repository.save(existing);
    }
    @Transactional
    public void deleteCustomerById(Long id){
        if(!repository.existsById(id)){
            throw new CustomerNotFoundException(id);
        }
        repository.deleteById(id);
    }

}
