package com.br.edu.ifpb.deps.autoevents.service;

import com.br.edu.ifpb.deps.autoevents.dto.request.EventoRequest;
import com.br.edu.ifpb.deps.autoevents.dto.request.UsuarioRequest;
import com.br.edu.ifpb.deps.autoevents.model.Evento;
import com.br.edu.ifpb.deps.autoevents.model.Usuario;
import com.br.edu.ifpb.deps.autoevents.repository.UsuarioRepository;
import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;
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
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EventoServiceTest {
    @Autowired
    private EventoService service;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository repository;

    private Usuario usuario;

    private Faker faker;

    private EventoRequest request;
    private Evento criar;

    @Before
    public void config(){
        criar = this.service.cadastrarEvento(requestTeste());
    }

    @After
    public void end(){
        this.service.removerEvento(criar.getId());
        this.repository.deleteAll();
    }

    @Test
    public void cadastrarEvento(){
        request = requestTeste();
        Evento evento = this.service.cadastrarEvento(request);

        Assert.assertEquals(request.getNome(), evento.getNome());
        Assert.assertEquals(request.getPais(), evento.getPais());
        Assert.assertEquals(request.getDescricao(), evento.getDescricao());
        Assert.assertTrue(request.getNome().length() < 50);
    }

    @Test
    public void cadastrarErradoComLugarDataIguais(){
        try{
            request = requestTeste();
            Evento eventoSalvo = this.service.cadastrarEvento(request);
            request.setNome(faker.funnyName().name());
            Evento eventoErrado = this.service.cadastrarEvento(request);
        }
        catch (ResponseStatusException e){
            String erroCidadeData = "400 BAD_REQUEST \"Impossível haver dois eventos no mesmo dia e local.\"";
            Assert.assertEquals(e.getMessage(), erroCidadeData);
        }
    }

    @Test
    public void cadastrarErradoComNomesIguais(){
        try{
            request = requestTeste();
            System.out.println(request.getNome());
            Evento eventoSalvo = this.service.cadastrarEvento(request);

            request.setCidade(faker.address().cityName());
            System.out.println(request.getNome());
            Evento eventoErrado = this.service.cadastrarEvento(request);
            System.out.println();
        }
        catch (ResponseStatusException e){
            String erroNome = "400 BAD_REQUEST \"Impossível haver dois eventos com o mesmo nome\"";
            Assert.assertEquals(e.getMessage(), erroNome);
        }
    }

    @Test
    public void listarEvento(){
        Assert.assertFalse(service.listarEventos(PageRequest.of(0, 20)).getContent().isEmpty());
    }

    @Test
    public void buscarEvento(){
        Evento evento = this.service.buscarEvento(criar.getId());
        Assert.assertNotNull(evento.getNome());
        Assert.assertEquals(criar.getPais(), evento.getPais());
        Assert.assertEquals(criar.getNome(), evento.getNome());
    }

    @Test(expected = ResponseStatusException.class)
    public void falhaBuscaIdErrado(){
        this.service.buscarEvento(Long.MIN_VALUE);
    }

    @Test
    public void atualizarEvento(){
        EventoRequest eventoRequest = requestTeste();
        Evento evento = this.service.cadastrarEvento(eventoRequest);
        eventoRequest.setCidade(faker.address().cityName());
        eventoRequest.setDataEvento(LocalDate.of(2022, Month.APRIL, 22));

        evento = this.service.atualizarEvento(evento.getId(), eventoRequest);
        Assert.assertEquals(evento.getCidade(), eventoRequest.getCidade());
        Assert.assertEquals(evento.getDataEvento(), eventoRequest.getDataEvento());
        Assert.assertTrue(eventoRequest.getNome().length() < 50);
    }

    @Test(expected = ResponseStatusException.class)
    public void atualizarErradoIdFalha(){
        this.service.atualizarEvento(Long.MAX_VALUE, requestTeste());
    }

    @Test
    public void removerEvento(){
        Evento evento = this.service.cadastrarEvento(requestTeste());
        this.service.removerEvento(evento.getId());
    }

    @Test(expected = ResponseStatusException.class)
    public void removerIdIncorretoFalha(){
        this.service.removerEvento(Long.MIN_VALUE);
    }

    private EventoRequest requestTeste(){
        EventoRequest eventoRequest = new EventoRequest();
        faker = new Faker();
        Random random = new Random();

        Usuario usuario = this.usuarioService.criarUsuario(criarUsuario());

        eventoRequest.setCidade(faker.address().cityName());
        eventoRequest.setDataEvento(LocalDate.of(2021, Month.APRIL, random.nextInt(27) + 1));
        eventoRequest.setDescricao(RandomStringUtils.random(60, true, false));
        eventoRequest.setIngressoValor(200);
        eventoRequest.setNome(faker.app().name());
        eventoRequest.setPais(faker.address().country());
        eventoRequest.setUsuarioId(usuario.getId());

        return eventoRequest;
    }

    private UsuarioRequest criarUsuario(){

        Faker faker = new Faker();

        UsuarioRequest usuario = new UsuarioRequest();
        usuario.setDataNascimento(LocalDate.of(1982, Month.JUNE, 1));
        usuario.setEmail(faker.internet().emailAddress());
        usuario.setNome("admin");
        usuario.setSenha(faker.funnyName().name());

        return usuario;
    }
}
