package com.fiap.techfood.application.interfaces.usecases;

import com.fiap.techfood.domain.customer.Customer;
import com.fiap.techfood.application.dto.request.CustomerRequestDTO;
import com.fiap.techfood.domain.payment.PaymentStatus;

public interface CustomerUseCases {

    Long createCustomer(CustomerRequestDTO dto);

    Customer findCustomerByCpf(String cpf);

    void disableCustomer(String cpf);

    void deleteCustomer(String cpf);
}
