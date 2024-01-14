package com.fiap.techfood.application.usecases;

import com.fiap.techfood.application.dto.request.CategoryRequestDTO;
import com.fiap.techfood.application.interfaces.gateways.CategoryRepository;
import com.fiap.techfood.application.interfaces.usecases.CategoryUseCases;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CategoryUseCasesImplTest {

  private CategoryUseCases categoryUseCases;

  @Mock private CategoryRepository categoryRepository;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
    categoryUseCases = new CategoryUseCasesImpl(categoryRepository);
  }

  @Test
  void shouldCreateCategorySuccess() {
    CategoryRequestDTO requestDTO = new CategoryRequestDTO("Bebidas");
    assertDoesNotThrow(() -> categoryUseCases.createCategory(requestDTO));
    verify(categoryRepository).save(any());
  }
}
