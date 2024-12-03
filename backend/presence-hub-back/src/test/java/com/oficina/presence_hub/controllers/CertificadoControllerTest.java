package com.oficina.presence_hub.controllers;

import com.oficina.presence_hub.dtos.CertificadoDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import utils.TestUtils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CertificadoControllerTest {


    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    void createCertificadoTest() {
        CertificadoDTO certificadoDto = TestUtils.buildCertificadoDTOwithoutId();

        given()
                .contentType(ContentType.JSON)
                .body(certificadoDto)
                .when()
                .post("/certificados")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("aluno.nome", equalTo("John Doe"))
                .body("workshop.titulo", equalTo("Workshop 1"))
                .body("assinaturaDigital", equalTo("new-digital-signature"));
    }

    @Test
    void getAllCertificadosTest() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/certificados")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("$", not(empty()));
    }

    @Test
    void getCertificadoByIdTest() {
        Long certificadoId = 992L; // Adjust this ID based on your test data

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/certificados/{id}", certificadoId)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(certificadoId.intValue()));
    }

    @Test
    void updateCertificadoTest() {
        Long certificadoId = 992L;
        CertificadoDTO certificadoDto = TestUtils.buildCertificadoDTO();

        given()
                .contentType(ContentType.JSON)
                .body(certificadoDto)
                .when()
                .put("/certificados/{id}", certificadoId)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("assinaturaDigital", equalTo("new-digital-signature"));
    }

    @Test
    void deleteCertificadoTest() {
        Long certificadoId = 991L; // Adjust this ID based on your test data

        given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/certificados/{id}", certificadoId)
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }
}