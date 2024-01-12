package com.fiap.techfood.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.techfood.application.dto.request.OrderRequestDTO;
import com.fiap.techfood.application.dto.request.ProductRequestDTO;
import com.fiap.techfood.domain.customer.Customer;
import com.fiap.techfood.domain.order.Order;
import com.fiap.techfood.domain.order.OrderStatus;
import com.fiap.techfood.domain.products.Category;
import com.fiap.techfood.domain.products.Product;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

public class ModelUtils {

  public static String asJsonString(Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  public static ProductRequestDTO createProductRequestDTOInstance(){
    return new ProductRequestDTO("BigMac", new BigDecimal(10), 1L, "Lanche com 2 hamburgueres");
  }

  public static Category createCategoryInstance(Long id){
      return Category.builder().id(id).name("Bebidas").build();
  }

  public static Product createProductInstance(Long id){
      return Product.builder().id(id).name("Big Mac").price(new BigDecimal(15)).build();
  }

  public static Customer createCustomerInstance(Long id){
      return Customer.builder().id(id).name("Cliente Nome").cpf("12345645856").email("email@teste.com").build();
  }

  public static Order createOrderInstance(Long number) {

    return Order.builder()
        .number(number)
        .customer(createCustomerInstance(1L))
        .items(List.of())
        .totalValue(new BigDecimal(1000))
        .status(OrderStatus.CREATED)
        .createdDateTime(OffsetDateTime.now())
        .receivedDateTime(OffsetDateTime.now())
        .notes("Order Test")
        .build();
  }

  public static OrderRequestDTO createOrderRequestDTOInstance() {
      return new OrderRequestDTO(1L, List.of(), "Order Test");
  }

}
