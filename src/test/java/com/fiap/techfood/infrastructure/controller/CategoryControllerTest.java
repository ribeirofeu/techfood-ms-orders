package com.fiap.techfood.infrastructure.controller;

import com.fiap.techfood.application.dto.request.CategoryRequestDTO;
import com.fiap.techfood.application.interfaces.usecases.CategoryUseCases;
import com.fiap.techfood.domain.products.Category;
import com.fiap.techfood.domain.products.Product;
import com.fiap.techfood.utils.ModelUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
        CategoryRequestDTO requestDTO = new CategoryRequestDTO("Drinks");

        mockMvc.perform(post("/categories").content(ModelUtils.asJsonString(requestDTO)).contentType("application/json"))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldCallFindCategoryByIdSuccess() throws Exception {

        Category category = Category.builder().id(1L).name("Bebidas").build();

        when(categoryUseCases.findCategoryById(anyLong())).thenReturn(category);

        mockMvc.perform(get("/categories/1"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldCallFindAllCategorySuccess() throws Exception {

        Category category = Category.builder().id(1L).name("Bebidas").build();
        when(categoryUseCases.findAllCategories()).thenReturn(List.of(category));

        mockMvc.perform(get("/categories"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldCallFindProductByCategorySuccess() throws Exception {

        Product product = Product.builder().build();
        when(categoryUseCases.findProductByCategory(anyLong())).thenReturn(List.of(product));

        mockMvc.perform(get("/categories/1/products"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldCallDeleteCategorySuccess() throws Exception {
        mockMvc.perform(delete("/categories/1"))
                .andExpect(status().isNoContent());
    }
    @Test
    void shouldCallUpdateCategorySuccess() throws Exception {
        CategoryRequestDTO requestDTO = new CategoryRequestDTO("Bebidas");

        mockMvc.perform(put("/categories/1").content(ModelUtils.asJsonString(requestDTO)).contentType("application/json"))
                .andExpect(status().isOk());
    }

}
