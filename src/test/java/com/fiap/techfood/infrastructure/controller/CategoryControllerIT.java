package com.fiap.techfood.infrastructure.controller;

import com.fiap.techfood.application.dto.request.CategoryRequestDTO;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = "test")
@AutoConfigureTestDatabase
class CategoryControllerIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void shouldCallCreateCategorySuccess() {
        CategoryRequestDTO requestDTO = new CategoryRequestDTO("category test");
        given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(requestDTO)
        .when()
            .post("/categories")
        .then()
            .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    void shouldCallFindCategoryByIdSuccess() {
        Long id = 20L;

        when()
            .get("/categories/{id}", id)
        .then()
            .statusCode(HttpStatus.OK.value())
            .body(notNullValue())
            .body(matchesJsonSchemaInClasspath("schemas/category-schema.json"));
    }

    @Test
    void shouldCallFindAllCategorySuccess() {
        when()
            .get("/categories")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body(matchesJsonSchemaInClasspath("schemas/list-category-schema.json"));
    }

    @Test
    void shouldCallFindProductByCategorySuccess() {
        Long id = 30L;
        when()
            .get("/categories/{id}/products", id)
        .then()
            .statusCode(HttpStatus.OK.value())
            .body(matchesJsonSchemaInClasspath("schemas/list-products-category-schema.json"));
    }

    @Test
    void shouldCallDeleteCategorySuccess() {
        Long id = 41L;
        when()
            .delete("/categories/{id}", id)
        .then()
            .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    void shouldCallUpdateCategorySuccess() {
        CategoryRequestDTO requestDTO = new CategoryRequestDTO("category test update");
        Long id = 20L;

        given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(requestDTO)
        .when()
            .put("/categories/{id}", id)
        .then()
            .statusCode(HttpStatus.OK.value())
            .body(equalTo("Category Updated Successful"));;
    }
}
