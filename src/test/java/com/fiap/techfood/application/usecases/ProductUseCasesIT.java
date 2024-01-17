package com.fiap.techfood.application.usecases;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import com.fiap.techfood.application.dto.request.ProductRequestDTO;
import com.fiap.techfood.application.interfaces.gateways.CategoryRepository;
import com.fiap.techfood.application.interfaces.gateways.ProductRepository;
import com.fiap.techfood.application.interfaces.usecases.ProductUseCases;
import com.fiap.techfood.domain.commons.exception.BusinessException;
import com.fiap.techfood.domain.products.Product;
import com.fiap.techfood.utils.ModelUtils;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(value = "test")
class ProductUseCasesIT {

  private ProductUseCases productUseCases;

  @Autowired private ProductRepository productRepository;

  @Autowired private CategoryRepository categoryRepository;

  AutoCloseable openMocks;

  @BeforeEach
  void setup() {
    openMocks = MockitoAnnotations.openMocks(this);
    productUseCases = new ProductUseCasesImpl(productRepository, categoryRepository);
  }

  @AfterEach
  void tearDown() throws Exception {
    openMocks.close();
  }

  @Test
  void shouldFindProductSuccess() {
    Product product = productUseCases.findProductById(10L);
    assertEquals("X-Salada", product.getName());
  }

  @Test
  void shouldThrowExceptionFindByIdProduct() {
    assertThrows(BusinessException.class, ()-> productUseCases.findProductById(99L));
  }

  @Test
  void shouldCreateProductSuccess() {
    Long categoryId = 20L;

    ProductRequestDTO requestDTO = ModelUtils.createProductRequestDTOInstance(categoryId);
    Long productId = productUseCases.createProduct(requestDTO);
    assertNotNull(productId);
  }

  @Test
  void shouldFindAllProductSuccess() {
    List<Product> products = productUseCases.findAllProducts();
    assertTrue(products.size() > 1);
  }

  @Test
  void shouldDeleteProductSuccess() {
    assertDoesNotThrow(() -> productUseCases.deleteProduct(40L));
  }

  @Test
  void shouldUpdateProductSuccess() {
    ProductRequestDTO requestDTO = ModelUtils.createProductRequestDTOToUpdateInstance();
    productUseCases.updateProduct(41L, requestDTO);

    Product product = productUseCases.findProductById(41L);

    assertEquals(new BigDecimal("25.00"), product.getPrice());
  }

    @Test
    void shouldThrowExceptionUpdateProductInvalidCategory() {
        Long categoryId = 4L;
        ProductRequestDTO requestDTO = ModelUtils.createProductRequestDTOInstance(categoryId);
        assertThrows(BusinessException.class, () -> productUseCases.updateProduct(41L, requestDTO));
    }
}
