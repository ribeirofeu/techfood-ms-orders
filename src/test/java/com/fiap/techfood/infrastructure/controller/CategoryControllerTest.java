package com.fiap.techfood.infrastructure.controller;

import com.fiap.techfood.application.dto.request.CategoryRequestDTO;
import com.fiap.techfood.application.interfaces.usecases.CategoryUseCases;
import com.fiap.techfood.utils.ModelUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CategoryControllerTest {

    private MockMvc mockMvc;

    AutoCloseable mock;

    @Mock
    private CategoryUseCases categoryUseCases;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
        CategoryController categoryController = new CategoryController(categoryUseCases);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @AfterEach
    void tearDown() throws Exception{
        mock.close();
    }

    @Test
    void shouldCallCreateCategorySuccess() throws Exception {
        CategoryRequestDTO requestDTO = new CategoryRequestDTO("Bebidas");

        mockMvc.perform(post("/categories").content(ModelUtils.asJsonString(requestDTO)).contentType("application/json"))
                .andExpect(status().isCreated());

    }
}
