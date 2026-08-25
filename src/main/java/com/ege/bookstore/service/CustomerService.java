package com.ege.bookstore.service;

import com.ege.bookstore.dto.CustomerRequest;
import com.ege.bookstore.dto.CustomerResponse;
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
    public List<CustomerResponse> getAllCustomers(){
        return repository.findAll().stream().map(this::toResponse).toList();
    }
    @Transactional(readOnly = true)
    public CustomerResponse getCustomerById(Long id){
        Customer customer=repository.findById(id).orElseThrow(()->new CustomerNotFoundException(id));
        return toResponse(customer);
    }
    @Transactional(readOnly = true)
    public CustomerResponse getCustomerByUsername(String username){
        Customer customer = repository.findByUsername(username).orElseThrow(()->new CustomerNotFoundException(username));
        return toResponse(customer);
    }
    @Transactional
    public CustomerResponse registerCustomer(CustomerRequest customer){
        if(repository.existsByUsername(customer.getUsername())){
            throw new IllegalArgumentException("Username: '" + customer.getUsername() + "' is already taken");
        }
        Customer registered = repository.save(toEntity(customer));
        return toResponse(registered);
    }
    @Transactional
    public CustomerResponse updateCustomer(Long id, CustomerRequest updated){
        Customer existing = repository.findById(id).orElseThrow(()->new CustomerNotFoundException(id));
        if(!existing.getUsername().equals(updated.getUsername()) && repository.existsByUsername(updated.getUsername())) {
            throw new IllegalArgumentException("Username: '" + updated.getUsername() + "' is already taken");
        }
        existing.setName(updated.getName());
        existing.setSurname(updated.getSurname());
        existing.setUsername(updated.getUsername());
        Customer updatedNew = repository.save(existing);
        return toResponse(updatedNew);
    }
    @Transactional
    public void deleteCustomerById(Long id){
        if(!repository.existsById(id)){
            throw new CustomerNotFoundException(id);
        }
        repository.deleteById(id);
    }
    private Customer toEntity(CustomerRequest request){
        return new Customer(request.getUsername(), request.getName(), request.getSurname());
    }
    private CustomerResponse toResponse(Customer customer){
        return new CustomerResponse(customer.getId(), customer.getUsername(), customer.getName(), customer.getSurname());
    }

}
