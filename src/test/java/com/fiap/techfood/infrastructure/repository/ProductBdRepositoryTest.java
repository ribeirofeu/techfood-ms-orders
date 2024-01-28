package com.fiap.techfood.infrastructure.repository;

import com.fiap.techfood.domain.products.Category;
import com.fiap.techfood.domain.products.Product;
import com.fiap.techfood.infrastructure.repository.entity.ProductEntity;
import com.fiap.techfood.utils.ModelUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class ProductBdRepositoryTest {

  @InjectMocks private ProductBdRepository productBdRepository;

  @Mock private SpringProductRepository springProductRepository;

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
  void shouldFindProductByIdSuccess() {

    ProductEntity entity = ModelUtils.createProductEntityInstance();
    when(springProductRepository.findById(anyLong())).thenReturn(Optional.of(entity));
    Optional<Product> product = productBdRepository.findById(1L);

    assertTrue(product.isPresent());
  }

  @Test
  void shouldSaveProductSuccess() {
    Category category = Category.builder().name("Lanches").build();

    Product product = Product.builder().category(category).build();

    assertDoesNotThrow(() -> productBdRepository.save(product));
  }

  @Test
  void shouldFindAllProductSuccess() {

    ProductEntity entity = ModelUtils.createProductEntityInstance();
    when(springProductRepository.findAll()).thenReturn(List.of(entity));
    List<Product> products = productBdRepository.findAll();

    assertTrue(products.size() >= 1);
  }

  @Test
  void shouldFindAllProductInSuccess() {

    ProductEntity entity = ModelUtils.createProductEntityInstance();
    when(springProductRepository.findAllByIdIn(any())).thenReturn(List.of(entity));

    List<Product> products = productBdRepository.findAllByIdIn(List.of(1L));

    assertTrue(products.size() >= 1);
  }

  @Test
  void shouldDeleteProductSuccess() {
    assertDoesNotThrow(() -> productBdRepository.deleteProduct(1L));
  }

  @Test
  void shouldUpdateProductSuccess() {
    Category category = Category.builder().name("Lanches").build();

    Product product = Product.builder().category(category).build();

    assertDoesNotThrow(() -> productBdRepository.updateProduct(product));
  }
}
