package com.fiap.techfood.application.usecases;

import static org.junit.jupiter.api.Assertions.*;

import com.fiap.techfood.application.dto.request.CategoryRequestDTO;
import com.fiap.techfood.application.dto.request.CustomerRequestDTO;
import com.fiap.techfood.application.interfaces.gateways.CategoryRepository;
import com.fiap.techfood.application.interfaces.gateways.CustomerRepository;
import com.fiap.techfood.application.interfaces.usecases.CategoryUseCases;
import com.fiap.techfood.application.interfaces.usecases.CustomerUseCases;
import com.fiap.techfood.domain.customer.Customer;
import com.fiap.techfood.domain.products.Category;
import com.fiap.techfood.domain.products.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles(value = "test")
class CategoryUseCasesIT {

  private CategoryUseCases categoryUseCases;

  @Autowired private CategoryRepository categoryRepository;

  AutoCloseable openMocks;

  @BeforeEach
  void setup() {
    openMocks = MockitoAnnotations.openMocks(this);
    categoryUseCases = new CategoryUseCasesImpl(categoryRepository);
  }

  @AfterEach
  void tearDown() throws Exception {
    openMocks.close();
  }

  @Test
  void shouldCreateCategorySuccess(){
      CategoryRequestDTO requestDTO = new CategoryRequestDTO("Outros");
      Long categoryId = categoryUseCases.createCategory(requestDTO);
      assertEquals(1L, categoryId);
  }

    @Test
    void shouldFindCategoryByIdSuccess(){
        Category category = categoryUseCases.findCategoryById(20L);
        assertEquals("Lanches", category.getName());
    }
    @Test
    void shouldFindAllCategorySuccess(){
        List<Category> categories = categoryUseCases.findAllCategories();
        assertNotNull(categories);
        assertFalse(categories.isEmpty());
    }

    @Test
    void shouldDeleteCategorySuccess(){
        assertDoesNotThrow(() -> categoryUseCases.deleteCategory(40L));
    }

    @Test
    void shouldUpdateCategorySuccess(){
        CategoryRequestDTO requestDTO = new CategoryRequestDTO("Drinks and Water");
        categoryUseCases.updateCategory(41L, requestDTO);
        Category category = categoryUseCases.findCategoryById(41L);

        assertEquals("Drinks and Water", category.getName());
    }


}
