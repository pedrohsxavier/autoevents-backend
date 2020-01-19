package com.br.edu.ifpb.deps.autoevents.service;

import com.br.edu.ifpb.deps.autoevents.dto.request.MontadoraRequest;
import com.br.edu.ifpb.deps.autoevents.model.Montadora;
import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.server.ResponseStatusException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MontadoraServiceTest {

    private Faker faker;

    @Autowired
    private MontadoraService service;

    private MontadoraRequest montadoraRequest;

    private Montadora criar;

    @Before
    public void config(){
        criar = this.service.cadastrarMontadora(requestTeste());
    }

    @Test
    public void cadastrarMontadora(){
        montadoraRequest = requestTeste();

        Montadora montadora = this.service.cadastrarMontadora(montadoraRequest);

        Assert.assertEquals(montadoraRequest.getNome(), montadora.getNome());
        Assert.assertEquals(montadoraRequest.getPais(), montadora.getPais());
    }

    @Test
    public void listarMontadoras(){
        Assert.assertFalse(service.listarMontadoras(PageRequest.of(0, 20)).getContent().isEmpty());
    }

    @Test
    public void buscarMontadora(){
        Montadora montadora = this.service.buscarMontadora(criar.getId());
        Assert.assertNotNull(montadora.getNome());
        Assert.assertEquals(criar.getNome(), montadora.getNome());
    }

    @Test(expected = ResponseStatusException.class)
    public void buscarIdComFalhaMontadora(){
        this.service.buscarMontadora(Long.MIN_VALUE);
    }

    @Test
    public void atualizarMontadora(){
        MontadoraRequest request = requestTeste();
        Montadora montadora = this.service.cadastrarMontadora(request);
        request.setNome("Atualizado");
        Montadora montadoraAtualizada = this.service.atualizarMontadora(montadora.getId(), request);

        Assert.assertEquals(montadoraAtualizada.getNome(), request.getNome());
    }

    @Test(expected = ResponseStatusException.class)
    public void removerMontadoraIdErrado(){
        this.service.removerMontadora(Long.MAX_VALUE);
    }

    @Test
    public void removerMontadora(){
        Montadora montadora = this.service.cadastrarMontadora(requestTeste());
        this.service.removerMontadora(montadora.getId());
    }

    private MontadoraRequest requestTeste(){
        MontadoraRequest request = new MontadoraRequest();
        faker = new Faker();

        request.setPais(faker.address().country());
        request.setNome(faker.gameOfThrones().city());

        return request;
    }
}
