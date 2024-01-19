package com.fiap.techfood.infrastructure.controller;

import com.fiap.techfood.application.dto.request.CategoryRequestDTO;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Fail.fail;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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
        when()
            .get("/categories/20")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body(notNullValue())
            .body(matchesJsonSchemaInClasspath("schemas/category-schema-get.json"));
    }

    @Test
    void shouldCallFindAllCategorySuccess() {
        fail("not implement yet");
    }

    @Test
    void shouldCallFindProductByCategorySuccess() {
        fail("not implement yet");
    }

    @Test
    void shouldCallDeleteCategorySuccess() {
        fail("not implement yet");
    }

    @Test
    void shouldCallUpdateCategorySuccess() {
        fail("not implement yet");
    }
}
