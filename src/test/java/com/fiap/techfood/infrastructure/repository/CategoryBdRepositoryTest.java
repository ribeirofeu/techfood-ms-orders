package com.fiap.techfood.infrastructure.repository;

import com.fiap.techfood.domain.products.Category;
import com.fiap.techfood.domain.products.Product;
import com.fiap.techfood.infrastructure.repository.entity.CategoryEntity;
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

class CategoryBdRepositoryTest {

    @InjectMocks
    private CategoryBdRepository categoryBdRepository;

    @Mock
    private SpringCategoryRepository springCategoryRepository;

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
    void shouldFindCategoryByIdSuccess(){
        CategoryEntity entity = CategoryEntity.builder().name("Bebidas").build();
        when(springCategoryRepository.findById(anyLong())).thenReturn(Optional.of(entity));

        Optional<Category> result = categoryBdRepository.findById(1L);

        assertTrue(result.isPresent());

    }

    @Test
    void shouldSaveCategorySuccess(){
        CategoryEntity entity = CategoryEntity.builder().name("Bebidas").build();
        Category category = Category.builder().name("Bebidas").build();

        when(springCategoryRepository.save(any())).thenReturn(entity);

        assertDoesNotThrow( () -> categoryBdRepository.save(category));

    }


    @Test
    void shouldFindAllCategorySuccess(){
        CategoryEntity entity = CategoryEntity.builder().name("Bebidas").build();

        when(springCategoryRepository.findAll()).thenReturn(List.of(entity));

        List<Category> categories = categoryBdRepository.findAll();

        assertTrue(categories.size() >= 1);

    }

    @Test
    void shouldFindProductsByCategorySuccess(){
        ProductEntity productEntity = ModelUtils.createProductEntityInstance();
        CategoryEntity entity = CategoryEntity.builder().name("Bebidas").products(List.of(productEntity)).build();

        when(springCategoryRepository.findById(anyLong())).thenReturn(Optional.of(entity));

        List<Product> products = categoryBdRepository.findProductByCategory(1L);
        assertTrue(products.size() >= 1);

    }

    @Test
    void shouldDeleteCategorySuccess(){
        assertDoesNotThrow( () -> categoryBdRepository.deleteCategory(1L));

    }
}
