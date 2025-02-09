package com.oficina.presence_hub.controllers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.MultiPartSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.containsString;

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
    void showUploadPageTest() {
        given()
                .when()
                .get("/certificados/upload")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void validatePdfTest() {
        MockMultipartFile file = new MockMultipartFile("file", "test.pdf", "application/pdf", "test content".getBytes());

        given()
                .multiPart((MultiPartSpecification) file)
                .when()
                .post("/certificados/validate")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("isValid", equalTo(true)); // Adjust based on expected behavior
    }

    @Test
    void validatePdfWithMissingFileTest() {
        given()
                .contentType(ContentType.MULTIPART)
                .when()
                .post("/certificados/validate")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("error", containsString("Failed to read the file"));
    }

    @Test
    void validatePdfWithInvalidFileTest() {
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "invalid content".getBytes());

        given()
                .multiPart((MultiPartSpecification) file)
                .when()
                .post("/certificados/validate")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("isValid", equalTo(false)); // Adjust based on expected behavior
    }
}