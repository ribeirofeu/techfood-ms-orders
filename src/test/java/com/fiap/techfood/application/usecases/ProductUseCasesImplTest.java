package com.fiap.techfood.application.usecases;

import com.fiap.techfood.application.dto.request.ProductRequestDTO;
import com.fiap.techfood.application.interfaces.gateways.CategoryRepository;
import com.fiap.techfood.application.interfaces.gateways.ProductRepository;
import com.fiap.techfood.application.interfaces.usecases.ProductUseCases;
import com.fiap.techfood.utils.ModelUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProductUseCasesImplTest {

    private ProductUseCases productUseCases;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    AutoCloseable openMocks;

    @BeforeEach
    void setup(){
        openMocks = MockitoAnnotations.openMocks(this);
        productUseCases = new ProductUseCasesImpl(productRepository, categoryRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void shouldCreateProductSuccess(){
        ProductRequestDTO requestDTO = ModelUtils.createProductRequestDTOInstance(2L);
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(ModelUtils.createCategoryInstance(1L)));

        assertDoesNotThrow(() -> productUseCases.createProduct(requestDTO));
        verify(categoryRepository).findById(anyLong());
        verify(productRepository).save(any());
    }

}
