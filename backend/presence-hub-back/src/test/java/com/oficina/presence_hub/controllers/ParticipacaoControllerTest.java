package com.oficina.presence_hub.controllers;

import com.oficina.presence_hub.dtos.ParticipacaoDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import utils.TestUtils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ParticipacaoControllerTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void testCreateParticipacao() {
        ParticipacaoDTO participacaoDTO = TestUtils.buildParticipacaoDTOwithoutId();
        given()
                .contentType(ContentType.JSON)
                .body(participacaoDTO)
                .when()
                .post("/participacoes")
                .then()
                .statusCode(200)
                .body("presente", equalTo(true));
    }

    @Test
    public void testGetAllParticipacoes() {
        given()
                .when()
                .get("/participacoes")
                .then()
                .statusCode(200);
    }

    @Test
    public void testGetParticipacaoById() {
        given()
                .when()
                .get("/participacoes/999")
                .then()
                .statusCode(200)
                .body("id", equalTo(999));
    }

    @Test
    public void testUpdateParticipacao() {
        given()
                .when()
                .put("/participacoes/999")
                .then()
                .statusCode(200);
    }

    @Test
    public void testDeleteParticipacao() {
        given()
                .when()
                .delete("/participacoes/998")
                .then()
                .statusCode(204);
    }
}