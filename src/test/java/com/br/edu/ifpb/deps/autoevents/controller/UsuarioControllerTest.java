package com.br.edu.ifpb.deps.autoevents.controller;

import com.br.edu.ifpb.deps.autoevents.dto.request.UsuarioRequest;
import com.br.edu.ifpb.deps.autoevents.model.Usuario;
import com.br.edu.ifpb.deps.autoevents.service.UsuarioService;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.Month;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UsuarioControllerTest {
    private final String PATH = "http://localhost:8080/api/usuarios";
    Faker faker = new Faker();

    @Autowired
    private UsuarioService service;

    @Test
    public void cadastrarUsuario(){
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestTeste())
                .when()
                .post(PATH)
                .then()
                .log()
                .ifValidationFails()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void cadastrarEmailDuplicadoErro(){
        UsuarioRequest request = requestTeste();

        System.out.println(request.getEmail());

        this.service.criarUsuario(request);
            RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(request)
                    .when()
                    .post(PATH)
                    .then()
                    .log()
                    .ifValidationFails()
                    .statusCode(HttpStatus.SC_BAD_REQUEST);

    }

    @Test
    public void cadastrarErroDataFutura(){
        UsuarioRequest request = requestTeste();
        this.service.criarUsuario(request);
        request.setDataNascimento(LocalDate.of(2021, Month.AUGUST, 11));
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post(PATH)
                .then()
                .log()
                .ifValidationFails()
                .statusCode(HttpStatus.SC_BAD_REQUEST);

    }

    @Test
    public void buscarUsuario(){
        Usuario user = this.service.criarUsuario(requestTeste());
        RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get(PATH + "/{id}", user.getId())
                .then()
                .log()
                .ifValidationFails()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void buscarUsuarioIdErrado(){
        Usuario user = this.service.criarUsuario(requestTeste());
        RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get(PATH + "/{id}", Long.MIN_VALUE)
                .then()
                .log()
                .ifValidationFails()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void atualizarUsuario(){

        UsuarioRequest request = requestTeste();
        Usuario user = this.service.criarUsuario(request);
        request.setDataNascimento(LocalDate.of(1981, Month.AUGUST, 19));

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .put(PATH + "/{id}", user.getId())
                .then()
                .log()
                .ifValidationFails()
                .statusCode(HttpStatus.SC_OK)
                .body("dataNascimento", CoreMatchers.equalTo(request.getDataNascimento().toString()));
    }

    @Test
    public void atualizarUsuarioFalhaId(){

        UsuarioRequest request = requestTeste();
        this.service.criarUsuario(request);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .put(PATH + "/{id}", Long.MIN_VALUE)
                .then()
                .log()
                .ifValidationFails()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void listarUsuarios(){
        RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get(PATH)
                .then()
                .log()
                .ifValidationFails()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void removerUsuarioSucesso(){
        Usuario usuario = this.service.criarUsuario(requestTeste());
        RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .delete(PATH + "/{id}", usuario.getId())
                .then()
                .log()
                .ifValidationFails()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void removerFalhaIdErrado(){
        RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .delete(PATH + "/{id}", Long.MIN_VALUE)
                .then()
                .log()
                .ifValidationFails()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    private UsuarioRequest requestTeste(){
        UsuarioRequest request = new UsuarioRequest();

        request.setDataNascimento(LocalDate.of(1968, Month.MAY, 18));
        request.setNome(faker.name().firstName());
        request.setSenha(faker.funnyName().name());
        request.setEmail(faker.internet().emailAddress());
        return request;
    }
}
