package com.ege.bookstore.service;

import com.ege.bookstore.dto.CustomerRequest;
import com.ege.bookstore.dto.CustomerResponse;
import com.ege.bookstore.entity.Customer;
import com.ege.bookstore.exception.CustomerNotFoundException;
import com.ege.bookstore.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    @Mock
    private CustomerRepository repository;
    @InjectMocks
    private CustomerService service;
    @Test
    void getCustomerById_returnsCustomer_whenCustomerExists(){
        Customer jack= new Customer("jack99", "Jack", "Sparrow");
        when(repository.findById(9L)).thenReturn(Optional.of(jack));
        CustomerResponse result = service.getCustomerById(9L);
        assertThat(result.getUsername()).isEqualTo("jack99");
        assertThat(result.getName()).isEqualTo("Jack");
    }
    @Test
    void getCustomerById_throwsException_whenCustomerMissing(){
        when(repository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(()->service.getCustomerById(999L)).isInstanceOf(CustomerNotFoundException.class).hasMessageContaining("999");
    }
    @Test
    void updateCustomer_updatesCustomer_whenUsernameUnchanged(){
        Customer jack = new Customer("jack99", "Jack", "Sparrow");
        CustomerRequest updated = new CustomerRequest();
        updated.setUsername("jack99");
        updated.setName("Jackie");
        updated.setSurname("Sparrowski");
        when(repository.findById(9L)).thenReturn(Optional.of(jack));
        when(repository.save(jack)).thenReturn(jack);
        CustomerResponse result = service.updateCustomer(9L, updated);
        assertThat(result.getName()).isEqualTo("Jackie");
        assertThat(result.getSurname()).isEqualTo("Sparrowski");
    }
    @Test
    void updateCustomer_throwsException_whenUsernameTaken(){
        Customer jack = new Customer("jack99", "Jack", "Sparrow");
        CustomerRequest updated = new CustomerRequest();
        updated.setUsername("liz77");
        updated.setName("Jackie");
        updated.setSurname("Sparrowski");
        when(repository.findById(9L)).thenReturn(Optional.of(jack));
        when(repository.existsByUsername("liz77")).thenReturn(true);
        assertThatThrownBy(()->service.updateCustomer(9L, updated)).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("liz77");
        verify(repository, never()).save(any());

    }
    @Test
    void updateCustomer_updatesCustomer_whenNewUsernameFree(){
        Customer jack = new Customer("jack99", "Jack", "Sparrow");
        CustomerRequest updated = new CustomerRequest();
        updated.setUsername("liz77");
        updated.setName("Jackie");
        updated.setSurname("Sparrowski");
        when(repository.findById(9L)).thenReturn(Optional.of(jack));
        when(repository.existsByUsername("liz77")).thenReturn(false);
        when(repository.save(jack)).thenReturn(jack);
        CustomerResponse result = service.updateCustomer(9L, updated);
        assertThat(result.getUsername()).isEqualTo("liz77");
        assertThat(result.getName()).isEqualTo("Jackie");
        assertThat(result.getSurname()).isEqualTo("Sparrowski");
    }
    @Test
    void updateCustomer_throwsException_whenCustomerMissing(){
        CustomerRequest updated = new CustomerRequest();
        updated.setUsername("jack99");
        updated.setSurname("Sparrow");
        updated.setName("Jack");
        when(repository.findById(9L)).thenReturn(Optional.empty());
        assertThatThrownBy(()->service.updateCustomer(9L,updated)).isInstanceOf(CustomerNotFoundException.class).hasMessageContaining("9");
    }
}
