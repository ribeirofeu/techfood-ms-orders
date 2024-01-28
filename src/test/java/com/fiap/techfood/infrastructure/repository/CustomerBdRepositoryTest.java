package com.fiap.techfood.infrastructure.repository;

import com.fiap.techfood.domain.customer.Customer;
import com.fiap.techfood.infrastructure.repository.entity.CustomerEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class CustomerBdRepositoryTest {

    @InjectMocks
    private CustomerBdRepository customerBdRepository;

    @Mock
    private SpringCustomerRepository springCustomerRepository;

    AutoCloseable openMocks;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void shouldSaveCustomerSuccess(){
        Customer customer = Customer.builder().build();
        CustomerEntity entity = CustomerEntity.builder().id(1L).build();
        when(springCustomerRepository.save(any())).thenReturn(entity);

        assertDoesNotThrow( () -> customerBdRepository.save(customer));
    }

    @Test
    void shouldFindCustomerByCpfSuccess(){
        CustomerEntity entity = CustomerEntity.builder().id(1L).build();

        when(springCustomerRepository.findByCpf(anyString())).thenReturn(Optional.of(entity));
        Optional<Customer> result = customerBdRepository.findByCpf("34565434533");

        assertTrue(result.isPresent());
    }

    @Test
    void shouldFindCustomerByIDSuccess(){
        CustomerEntity entity = CustomerEntity.builder().id(1L).build();

        when(springCustomerRepository.findById(anyLong())).thenReturn(Optional.of(entity));
        Optional<Customer> result = customerBdRepository.findById(1L);

        assertTrue(result.isPresent());
    }






}
