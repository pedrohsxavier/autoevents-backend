package com.br.edu.ifpb.deps.autoevents.service;

import com.br.edu.ifpb.deps.autoevents.dto.request.CarroRequest;
import com.br.edu.ifpb.deps.autoevents.dto.request.MontadoraRequest;
import com.br.edu.ifpb.deps.autoevents.model.Carro;
import com.br.edu.ifpb.deps.autoevents.model.Montadora;
import com.br.edu.ifpb.deps.autoevents.model.Usuario;
import com.br.edu.ifpb.deps.autoevents.repository.CarroRepository;
import com.br.edu.ifpb.deps.autoevents.repository.MontadoraRepository;
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

import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.xml.validation.Validator;
import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CarroServiceTest {
    private Faker faker;

    @Autowired
    private CarroService service;
    private CarroRequest carroRequest;

    @Autowired
    private MontadoraRepository repository;

    @Autowired
    private CarroRepository carroRepository;

    private Carro criar;
    private Validator validator;

    @Before
    public void config(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        factory.getValidator();
        criar = this.service.cadastrarCarro(requestTeste());
    }

    @After
    public void exit(){
        carroRepository.deleteAll();
        repository.deleteAll();
    }

    @Test
    public void cadastrarCarroTeste(){
        carroRequest = requestTeste();

        Carro carro = this.service.cadastrarCarro(carroRequest);

        Assert.assertEquals(carroRequest.getNome(), carro.getNome());
        Assert.assertNotNull(carroRequest.getValor().toString(), carro.getValor());
    }

    @Test(expected = ResponseStatusException.class)
    public void impossivelCadastrarComMontadoraInexistente(){
        carroRequest = requestTeste();

        carroRequest.setMontadoraId(Long.MIN_VALUE);
        this.service.cadastrarCarro(carroRequest);
    }

    @Test
    public void listarCarros(){
        Assert.assertFalse(service.listarCarros(PageRequest.of(0, 20)).getContent().isEmpty());
    }

    @Test
    public void buscarCarro(){
        Carro carro = this.service.buscarCarro(criar.getId());
        Assert.assertNotNull(carro.getNome());
    }

    @Test(expected = ResponseStatusException.class)
    public void falhaBuscaIdErrado(){
        this.service.buscarCarro(Long.MAX_VALUE);
    }

    @Test
    public void atualizarCarro(){
        CarroRequest request = requestTeste();
        Carro carro = this.service.cadastrarCarro(request);
        request.setNome(faker.ancient().hero());
        Carro carroAtualizado = this.service.atualizarCarro(carro.getId(), request);

        Assert.assertEquals(carroAtualizado.getNome(), request.getNome());
    }

    @Test(expected = ResponseStatusException.class)
    public void atualizarComFalhaId(){
        this.service.atualizarCarro(Long.MAX_VALUE, requestTeste());
    }

    @Test
    public void removerCarro(){
        Carro carro = this.service.cadastrarCarro(requestTeste());
        this.service.removerCarro(carro.getId());
    }

    @Test(expected = ResponseStatusException.class)
    public void removerIncorretoIdFalha(){
        this.service.removerCarro(Long.MIN_VALUE);
    }

    private CarroRequest requestTeste(){
        CarroRequest request = new CarroRequest();
        Montadora montadora = montadoraRequestTeste();
        faker = new Faker();

        request.setNome(faker.artist().name());
        request.setAno(2019);
        request.setValor(new BigDecimal("300000"));
        request.setMontadoraId(montadora.getId());

        return request;
    }

    private Montadora montadoraRequestTeste(){
        MontadoraRequest montadoraRequest = new MontadoraRequest();
        Faker faker = new Faker();
        montadoraRequest.setNome(faker.artist().name());
        montadoraRequest.setPais(faker.address().country());

        MontadoraService service = new MontadoraService(repository);

        Montadora montadora = service.cadastrarMontadora(montadoraRequest);
        return montadora;
    }
}
