package com.fiap.techfood.infrastructure.repository.entity;

import com.fiap.techfood.domain.customer.Customer;
import org.junit.jupiter.api.Test;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerEntityTest {

    @Test
    public void testCustomerEntityBuilder() {
        // Arrange
        Long id = 1L;
        String name = "Test";
        String cpf = "12345678900";
        String email = "test@techfood.com";
        boolean ativo = true;

        // Act
        CustomerEntity customerEntity = CustomerEntity.builder()
                .id(id)
                .name(name)
                .cpf(cpf)
                .email(email)
                .ativo(ativo)
                .build();

        // Assert
        assertNotNull(customerEntity);
        assertEquals(id, customerEntity.getId());
        assertEquals(name, customerEntity.toCustomer().getName());
        assertEquals(cpf, customerEntity.toCustomer().getCpf());
        assertEquals(email, customerEntity.toCustomer().getEmail());
        assertEquals(ativo, customerEntity.toCustomer().isAtivo());
    }

    @Test
    public void testToCustomer() {
        // Arrange
        Long id = 1L;
        String name = "Test";
        String cpf = "12345678900";
        String email = "test@techfood.com";
        boolean ativo = true;

        CustomerEntity customerEntity = CustomerEntity.builder()
                .id(id)
                .name(name)
                .cpf(cpf)
                .email(email)
                .ativo(ativo)
                .build();

        // Act
        Customer customer = customerEntity.toCustomer();

        // Assert
        assertNotNull(customer);
        assertEquals(id, customer.getId());
        assertEquals(name, customer.getName());
        assertEquals(cpf, customer.getCpf());
        assertEquals(email, customer.getEmail());
        assertEquals(ativo, customer.isAtivo());
    }
}
