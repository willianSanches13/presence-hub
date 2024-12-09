package com.oficina.presence_hub.controllers;

import com.oficina.presence_hub.dtos.AlunoDTO;
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
public class AlunoControllerTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    void createAlunoTest() {
        AlunoDTO alunoDto = TestUtils.buildAlunoDTOwithoutId();

        given()
                .contentType(ContentType.JSON)
                .body(alunoDto)
                .when()
                .post("/alunos")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("nome", equalTo("John Doe"))
                .body("email", equalTo("john.doe@example.com"));
    }

    @Test
    void getAllAlunosTest() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/alunos")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("$", not(empty()));
    }

    @Test
    void getAlunoByIdTest() {
        Long alunoId = 999L; // Adjust this ID based on your test data

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/alunos/{id}", alunoId)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(alunoId.intValue()));
    }

    @Test
    void updateAlunoTest() {
        Long alunoId = 999L;
        AlunoDTO alunoDto = TestUtils.buildAlunoDTOwithId(alunoId);

        given()
                .contentType(ContentType.JSON)
                .body(alunoDto)
                .when()
                .put("/alunos/{id}", alunoId)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("nome", equalTo("Jane Doe"))
                .body("email", equalTo("jane.doe@example.com"));
    }

    @Test
    void deleteAlunoTest() {
        Long alunoId = 998L; // Adjust this ID based on your test data

        given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/alunos/{id}", alunoId)
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }
}