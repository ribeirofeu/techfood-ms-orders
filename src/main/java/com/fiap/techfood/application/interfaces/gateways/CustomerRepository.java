package com.fiap.techfood.application.interfaces.gateways;

import com.fiap.techfood.domain.customer.Customer;
import com.fiap.techfood.infrastructure.repository.entity.CustomerEntity;

import java.util.Optional;

public interface CustomerRepository {
    Long save(Customer customer);

    Optional<Customer> findByCpf(String cpf);

    Optional<Customer> findById(Long id);

    void deleteByCPF(String cpf);

    void disableByCPF(Customer customer);
}
