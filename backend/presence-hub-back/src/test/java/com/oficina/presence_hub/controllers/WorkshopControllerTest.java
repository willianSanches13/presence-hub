package com.oficina.presence_hub.controllers;

import com.oficina.presence_hub.dtos.WorkshopDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import utils.TestUtils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class WorkshopControllerTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    void createWorkshopTest() {
        WorkshopDTO workshop = TestUtils.buildWorkshopDTO();

        given()
                .contentType(ContentType.JSON)
                .body(workshop)
                .when()
                .post("/workshops")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("$", notNullValue());
    }

    @Test
    void getAllWorkshopsTest() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/workshops")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("$", hasSize(greaterThan(0)));
    }

    @Test
    void getWorkshopByIdTest() {
        Long workshopId = 997L;

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/workshops/{id}", workshopId)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(workshopId.intValue()));
    }

    @Test
    void updateWorkshopTest() {
        Long workshopId = 996L;
        WorkshopDTO workshop = TestUtils.buildWorkshopDTOWithoutParticipacoes();
        String expectedDate = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);

        given()
                .contentType(ContentType.JSON)
                .body(workshop)
                .when()
                .put("/workshops/{id}", workshopId)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(workshopId.intValue()))
                .body("titulo", equalTo("New Workshop"))
                .body("descricao", equalTo("New Description for new workshop"))
                .body("data", equalTo(expectedDate));
    }

    @Test
    void deleteWorkshopTest() {
        Long workshopId = 995L;

        given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/workshops/{id}", workshopId)
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }
}