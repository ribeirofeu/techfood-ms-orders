package com.fiap.techfood.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.techfood.application.dto.request.OrderRequestDTO;
import com.fiap.techfood.application.dto.request.ProductRequestDTO;
import com.fiap.techfood.application.dto.request.SearchOrdersRequestDTO;
import com.fiap.techfood.domain.customer.Customer;
import com.fiap.techfood.domain.order.Order;
import com.fiap.techfood.domain.order.OrderItem;
import com.fiap.techfood.domain.order.OrderStatus;
import com.fiap.techfood.domain.products.Category;
import com.fiap.techfood.domain.products.Product;
import com.fiap.techfood.infrastructure.repository.entity.CategoryEntity;
import com.fiap.techfood.infrastructure.repository.entity.OrderEntity;
import com.fiap.techfood.infrastructure.repository.entity.ProductEntity;

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

  public static ProductRequestDTO createProductRequestDTOInstance(Long categoryId) {
    return new ProductRequestDTO(
        "BigMac", new BigDecimal(10), categoryId, "Lanche com 2 hamburgueres");
  }

  public static ProductRequestDTO createProductRequestDTOToUpdateInstance() {
    return new ProductRequestDTO("X-Picanha", new BigDecimal(25), 20L, "Lanche de Picanha");
  }

  public static Category createCategoryInstance(Long id) {
    return Category.builder().id(id).name("Bebidas").build();
  }

  public static Product createProductInstance(Long id) {
    return Product.builder().id(id).name("Big Mac").price(new BigDecimal(15)).build();
  }

  public static Customer createCustomerInstance(Long id) {
    return Customer.builder()
        .id(id)
        .name("Cliente Nome")
        .cpf("12345645856")
        .email("email@teste.com")
        .build();
  }

  public static Order createOrderInstance(Long number, OrderStatus status) {

    OrderItem orderItem =
        OrderItem.builder()
            .product(createProductInstance(1L))
            .quantity(2)
            .unitPrice(new BigDecimal(10))
            .build();

    return Order.builder()
        .number(number)
        .customer(createCustomerInstance(1L))
        .items(List.of(orderItem))
        .totalValue(new BigDecimal(1000))
        .status(status)
        .createdDateTime(OffsetDateTime.now())
        .receivedDateTime(OffsetDateTime.now())
        .notes("Order Test")
        .build();
  }

  public static OrderRequestDTO createOrderRequestDTOInstance() {
    Long productId = 10L;

    OrderRequestDTO.OrderItemRequestDTO orderItem =
        OrderRequestDTO.OrderItemRequestDTO.builder().productId(productId).quantity(3).build();
    return new OrderRequestDTO(10L, List.of(orderItem), "Order Test");
  }

  public static OrderEntity createOrderEntityInstance() {
    return OrderEntity.builder()
        .id(1L)
        .totalValue(new BigDecimal(23))
        .status(OrderStatus.CREATED)
        .createdDateTime(OffsetDateTime.now())
        .build();
  }

  public static ProductEntity createProductEntityInstance() {

    return ProductEntity.builder()
        .id(1L)
        .name("Coca Cola")
        .price(new BigDecimal(8))
        .description("Bebida de cola")
        .category(createCategoryEntityInstance())
        .build();
  }

  public static CategoryEntity createCategoryEntityInstance() {
    return CategoryEntity.builder().id(1L).name("Bebidas").build();
  }

  public static SearchOrdersRequestDTO createSearchOrdersRequestDTOInstance(){

      SearchOrdersRequestDTO searchOrdersRequestDTO = new SearchOrdersRequestDTO();
      searchOrdersRequestDTO.setStatus(OrderStatus.IN_PREPARATION);
      searchOrdersRequestDTO.setStartDatetime(OffsetDateTime.now());
      searchOrdersRequestDTO.setEndDatetime(OffsetDateTime.now());

      return searchOrdersRequestDTO;

  }

    public static SearchOrdersRequestDTO createSearchOrdersRequestDTOInvalidRangeInstance(){

        SearchOrdersRequestDTO searchOrdersRequestDTO = new SearchOrdersRequestDTO();
        searchOrdersRequestDTO.setStatus(OrderStatus.IN_PREPARATION);
        searchOrdersRequestDTO.setStartDatetime(OffsetDateTime.now());
        searchOrdersRequestDTO.setEndDatetime(OffsetDateTime.parse("2023-12-12T10:00:00+03:00"));

        return searchOrdersRequestDTO;

    }


}
