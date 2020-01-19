package com.br.edu.ifpb.deps.autoevents.service;

import com.br.edu.ifpb.deps.autoevents.dto.request.LoginRequest;
import com.br.edu.ifpb.deps.autoevents.dto.request.UsuarioRequest;
import com.br.edu.ifpb.deps.autoevents.model.Usuario;
import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.Month;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LoginServiceTest {

    @Autowired
    private LoginService loginService;
    private LoginRequest request;

    private Faker faker;

    @Autowired
    private UsuarioService usuarioService;

    @Test
    public void logar(){
        Usuario usuario = usuarioService.criarUsuario(usuarioTeste());
        loginService.login(usuario.getEmail(), "123");

        Assert.assertNotNull(usuario.getToken());
    }

    @Test(expected = ResponseStatusException.class)
    public void invalido(){
        loginService.login("wololo@gmail.com", "123");
    }

    private UsuarioRequest usuarioTeste(){
        UsuarioRequest usuario = new UsuarioRequest();
        faker = new Faker();
        usuario.setDataNascimento(LocalDate.of(1982, Month.JUNE, 1));
        usuario.setEmail(faker.internet().emailAddress());
        usuario.setNome("admin");
        usuario.setSenha("123");

        return usuario;
    }


}
