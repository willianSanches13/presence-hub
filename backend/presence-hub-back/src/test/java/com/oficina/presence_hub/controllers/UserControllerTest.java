package com.oficina.presence_hub.controllers;

import com.oficina.presence_hub.dtos.CredenciaisDTO;
import com.oficina.presence_hub.dtos.TokenDTO;
import com.oficina.presence_hub.entities.User;
import com.oficina.presence_hub.services.UserService;
import com.oficina.presence_hub.config.JwtService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserControllerTest {

    @LocalServerPort
    private int port;

    @MockBean
    private UserService userService;

    @MockBean
    private JwtService jwtService;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    void registerUserTest() {
        User user = new User();
        user.setLogin("testuser");
        user.setPassword("password");

        doNothing().when(userService).save(any(User.class));

        given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("/users/register")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("login", equalTo("testuser"));
    }

    @Test
    void loginUserTest() {
        CredenciaisDTO credenciais = new CredenciaisDTO();
        credenciais.setLogin("testuser");
        credenciais.setPassword("password");

        User user = new User();
        user.setLogin("testuser");
        user.setPassword("password");

        doNothing().when(userService).authenticar(any(User.class));
        Mockito.when(jwtService.gerarToken(any(User.class))).thenReturn("mockToken");

        given()
                .contentType(ContentType.JSON)
                .body(credenciais)
                .when()
                .post("/users/login")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("login", equalTo("testuser"))
                .body("token", equalTo("mockToken"));
    }

    @Test
    void loginUserWithInvalidCredentialsTest() {
        CredenciaisDTO credenciais = new CredenciaisDTO();
        credenciais.setLogin("invaliduser");
        credenciais.setPassword("wrongpassword");

        doThrow(new UsernameNotFoundException("Invalid credentials")).when(userService).authenticar(any(User.class));

        given()
                .contentType(ContentType.JSON)
                .body(credenciais)
                .when()
                .post("/users/login")
                .then()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .body("status", equalTo(401))
                .body("error", equalTo("Unauthorized"));
    }
}