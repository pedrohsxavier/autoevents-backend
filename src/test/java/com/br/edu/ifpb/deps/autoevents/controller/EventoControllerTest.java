package com.br.edu.ifpb.deps.autoevents.controller;

import com.br.edu.ifpb.deps.autoevents.dto.request.EventoRequest;
import com.br.edu.ifpb.deps.autoevents.dto.request.UsuarioRequest;
import com.br.edu.ifpb.deps.autoevents.model.Evento;
import com.br.edu.ifpb.deps.autoevents.model.Usuario;
import com.br.edu.ifpb.deps.autoevents.service.EventoService;
import com.br.edu.ifpb.deps.autoevents.service.UsuarioService;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.Month;
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EventoControllerTest {
    private final String PATH = "http://localhost:8080/api/eventos";

    @Autowired
    private EventoService eventoService;

    @Autowired
    private UsuarioService usuarioService;

    Faker faker = new Faker();

    private EventoRequest request;
    private Evento evento;

    @Test
    public void criarEventoTest(){
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(eventoRequestTeste())
                .when()
                .post(PATH)
                .then()
                .log()
                .ifValidationFails()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void listarEventosTest(){
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
    public void buscarEventoTest(){

        request = eventoRequestTeste();
        evento = this.eventoService.cadastrarEvento(request);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get(PATH + "/{id}", evento.getId())
                .then()
                .log()
                .ifValidationFails()
                .statusCode(HttpStatus.SC_OK)
                .body("nome", CoreMatchers.equalTo(evento.getNome()));
    }

    @Test
    public void buscarFalhaIdEvento(){
        RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get(PATH + "/{id}", Long.MAX_VALUE)
                .then()
                .log()
                .ifValidationFails()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void atualizarEvento(){
        request = eventoRequestTeste();
        evento = eventoService.cadastrarEvento(request);
        request.setPais("Argentina (Chupa River)");

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .put(PATH + "/{id}", evento.getId())
                .then()
                .log()
                .ifValidationFails()
                .statusCode(HttpStatus.SC_OK)
                .body("pais", CoreMatchers.equalTo("Argentina (Chupa River)"));
    }

    @Test
    public void atualizarFalhaIdIncorretoEvento(){
        request = eventoRequestTeste();
        evento = eventoService.cadastrarEvento(request);
        request.setPais("Argentina (Chupa River)");

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .put(PATH + "/{id}", Long.MAX_VALUE)
                .then()
                .log()
                .ifValidationFails()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void removerEvento(){
        request = eventoRequestTeste();
        evento = this.eventoService.cadastrarEvento(request);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .delete(PATH + "/{id}", evento.getId())
                .then()
                .log()
                .ifValidationFails()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void removerFalhaIdEvento(){
        request = eventoRequestTeste();
        evento = this.eventoService.cadastrarEvento(request);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .delete(PATH + "/{id}", Long.MAX_VALUE)
                .then()
                .log()
                .ifValidationFails()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    private Usuario usuarioTeste(){
        UsuarioRequest usuarioRequest = new UsuarioRequest();

        usuarioRequest.setDataNascimento(LocalDate.of(1982, Month.MARCH, 19));
        usuarioRequest.setEmail(faker.internet().emailAddress());
        usuarioRequest.setNome(faker.name().firstName());
        usuarioRequest.setSenha(faker.funnyName().name());

        return this.usuarioService.criarUsuario(usuarioRequest);
    }

    private EventoRequest eventoRequestTeste(){

        EventoRequest eventoRequest = new EventoRequest();
        Random random = new Random();
        Usuario user = usuarioTeste();

        eventoRequest.setCidade(faker.address().cityName());
        eventoRequest.setDataEvento(LocalDate.of(2021, Month.AUGUST, random.nextInt(27) + 1));
        eventoRequest.setDescricao(RandomStringUtils.random(60, true, false));
        eventoRequest.setIngressoValor(200);
        eventoRequest.setNome(faker.app().name());
        eventoRequest.setPais(faker.address().country());
        eventoRequest.setUsuarioId(user.getId());

        return eventoRequest;
    }
}
