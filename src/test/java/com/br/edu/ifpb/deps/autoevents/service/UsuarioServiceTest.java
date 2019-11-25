package com.br.edu.ifpb.deps.autoevents.service;

import com.br.edu.ifpb.deps.autoevents.dto.request.UsuarioRequest;
import com.br.edu.ifpb.deps.autoevents.model.Usuario;
import com.br.edu.ifpb.deps.autoevents.repository.UsuarioRepository;
import com.github.javafaker.Faker;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.Month;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UsuarioServiceTest {

    @Autowired
    private UsuarioService service;

    private Faker faker;

    private UsuarioRequest request;
    private Usuario criar;

    @Before
    public void config(){
        criar = this.service.criarUsuario(requestTeste());
    }

    @After
    public void end(){
        this.service.removerUsuario(criar.getId());
    }

    @Test
    public void cadastrarUsuario(){
        request = requestTeste();
        Usuario usuario = this.service.criarUsuario(request);

        Assert.assertEquals(request.getNome(), usuario.getNome());
        Assert.assertEquals(request.getDataNascimento(), usuario.getDataNascimento());
        Assert.assertTrue(request.getNome().length() < 50);
        Assert.assertTrue(request.getNome().length() > 2);
    }

    @Test
    public void dataIncorretaImpossivelCadastro(){
        try{
            request = requestTeste();
            request.setDataNascimento(LocalDate.of(2022, Month.FEBRUARY, 10));
            this.service.criarUsuario(request);
        }catch (ResponseStatusException e){
            String erro = "400 BAD_REQUEST \"Data inválida para ser cadastrada.\"";
            Assert.assertEquals(e.getMessage(), erro);
        }
    }

    @Test(expected = ResponseStatusException.class)
    public void cadastrarFalhaEmailIgualTest(){
        request = requestTeste();
        this.service.criarUsuario(request);
        this.service.criarUsuario(request);
    }

    @Test
    public void listarUsuarios(){
        Assert.assertFalse(service.listarUsuarios(PageRequest.of(0, 20)).getContent().isEmpty());
    }

    @Test
    public void buscarUsuario(){
        Usuario usuario = this.service.buscarUsuario(criar.getId());
        Assert.assertNotNull(usuario.getNome());
        Assert.assertEquals(criar.getEmail(), usuario.getEmail());
    }

    @Test(expected = ResponseStatusException.class)
    public void falhaBuscaIdErrado(){
        this.service.buscarUsuario(Long.MIN_VALUE);
    }

    @Test
    public void atualizarUsuario(){
        UsuarioRequest usuarioRequest = requestTeste();
        Usuario usuarioTeste = this.service.criarUsuario(usuarioRequest);
        usuarioRequest.setNome(faker.name().firstName());
        Usuario usuarioAtualizado = this.service.atualizarUsuario(usuarioTeste.getId(), usuarioRequest);

        Assert.assertEquals(usuarioAtualizado.getNome(), usuarioRequest.getNome());
        Assert.assertTrue(usuarioRequest.getNome().length() < 50);
        Assert.assertTrue(usuarioRequest.getNome().length() > 2);
    }

    @Test(expected = ResponseStatusException.class)
    public void atualizarComFalhaId(){
        this.service.atualizarUsuario(Long.MIN_VALUE, requestTeste());
    }

    public void dataIncorretaImpossivelAtualizar(){
        try{
            request = requestTeste();
            Usuario user = this.service.criarUsuario(request);
            request.setDataNascimento(LocalDate.of(2022, Month.FEBRUARY, 10));
            this.service.atualizarUsuario(user.getId(), request);
        }catch (ResponseStatusException e){
            String erro = "400 BAD_REQUEST \"Data inválida para ser alterada.\"";
            Assert.assertEquals(e.getMessage(), erro);
        }
    }

    @Test
    public void removerUsuario(){
        Usuario usuario = this.service.criarUsuario(requestTeste());
        this.service.removerUsuario(usuario.getId());
    }

    @Test(expected = ResponseStatusException.class)
    public void removerIncorretoIdFalha(){
        this.service.removerUsuario(Long.MIN_VALUE);
    }

    private UsuarioRequest requestTeste(){
        UsuarioRequest usuarioRequest = new UsuarioRequest();
        faker = new Faker();

        usuarioRequest.setDataNascimento(LocalDate.now());
        usuarioRequest.setEmail(faker.internet().emailAddress());
        usuarioRequest.setSenha(faker.funnyName().name());
        usuarioRequest.setNome(faker.name().firstName());
        return usuarioRequest;
    }
}
