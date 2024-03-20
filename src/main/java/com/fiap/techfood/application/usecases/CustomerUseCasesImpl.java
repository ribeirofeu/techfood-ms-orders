package com.fiap.techfood.application.usecases;

import com.fiap.techfood.application.interfaces.usecases.OrderUseCases;
import com.fiap.techfood.domain.commons.HttpStatusCodes;
import com.fiap.techfood.domain.customer.Customer;
import com.fiap.techfood.application.dto.request.CustomerRequestDTO;
import com.fiap.techfood.domain.commons.exception.BusinessException;
import com.fiap.techfood.application.interfaces.gateways.CustomerRepository;
import com.fiap.techfood.application.interfaces.usecases.CustomerUseCases;
import com.fiap.techfood.domain.payment.PaymentStatus;
import com.fiap.techfood.infrastructure.repository.entity.CustomerEntity;
import lombok.extern.log4j.Log4j2;

import java.util.Optional;

@Log4j2
public class CustomerUseCasesImpl implements CustomerUseCases {

    private final CustomerRepository customerRepository;

    public CustomerUseCasesImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Long createCustomer(CustomerRequestDTO dto) {
        Customer customer = Customer.fromCustomerDto(dto);
        customer.setAtivo(true);
        return customerRepository.save(customer);
    }

    @Override
    public Customer findCustomerByCpf(String cpf) {
        Optional<Customer> customer = customerRepository.findByCpf(cpf);
        return customer.orElseThrow(() -> new BusinessException("Customer not found by CPF", HttpStatusCodes.NOT_FOUND));
    }

    @Override
    public void disableCustomer(String cpf) {
        log.info("Disable Customer CPF {}", cpf);
        Optional<Customer> result = customerRepository.findByCpf(cpf);
        Customer customer = result.orElseThrow(() -> new BusinessException("Customer not found by CPF", HttpStatusCodes.NOT_FOUND));

        customer.setAtivo(false);
        customerRepository.disableByCPF(customer);

    }

    @Override
    public void deleteCustomer(String cpf) {
        log.info("Delete Customer CPF {}", cpf);
        customerRepository.deleteByCPF(cpf);
    }
}
