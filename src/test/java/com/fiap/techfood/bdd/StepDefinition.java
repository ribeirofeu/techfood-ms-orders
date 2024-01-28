package com.fiap.techfood.bdd;

import com.fiap.techfood.application.dto.request.CategoryRequestDTO;
import com.fiap.techfood.domain.products.Category;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;

public class StepDefinition {

    private Response response;

    private String ENDPOINT_API = "http://localhost:8080/categories";

    @Quando("cadastrar uma nova categoria")
    public void cadastrar_uma_nova_categoria() {
        CategoryRequestDTO requestDTO = new CategoryRequestDTO("Drinks");
        response = given()
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .body(requestDTO)
                    .when()
                        .post(ENDPOINT_API);
    }

    @Entao("categoria é registrada com sucesso")
    public void categoria_e_registrada_com_sucesso() {
        response.then()
                .statusCode(HttpStatus.CREATED.value());
    }
}
