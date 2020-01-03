package com.br.edu.ifpb.deps.autoevents.service;

import com.br.edu.ifpb.deps.autoevents.dto.request.CarroRequest;
import com.br.edu.ifpb.deps.autoevents.model.Carro;
import com.br.edu.ifpb.deps.autoevents.model.Montadora;
import com.br.edu.ifpb.deps.autoevents.repository.CarroRepository;
import com.br.edu.ifpb.deps.autoevents.repository.MontadoraRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CarroService {
    private CarroRepository carroRepository;
    private MontadoraRepository montadoraRepository;

    public CarroService(CarroRepository carroRepository, MontadoraRepository montadoraRepository) {
        this.carroRepository = carroRepository;
        this.montadoraRepository = montadoraRepository;
    }

    public Carro cadastrarCarro(CarroRequest request){
        Carro carro = new Carro();

        Montadora montadora = this.montadoraRepository.findById(request.getMontadoraId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Montadora inexistente"));

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(request, carro);

        return this.carroRepository.save(carro);
    }

    public Carro atualizarCarro(Long id, CarroRequest request){
        Carro carro = this.carroRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carro inexistente"));
        Montadora montadora = this.montadoraRepository.findById(request.getMontadoraId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Montadora inexistente"));

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(request, carro);

        return this.carroRepository.save(carro);
    }

    public Page<Carro> listarCarros(Pageable pageable){
        return this.carroRepository.findAll(pageable);
    }

    public Carro buscarCarro(Long id){
        Carro carro = this.carroRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carro inexistente"));
        return carro;
    }

    public void removerCarro(Long id){
        Carro carro = this.carroRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carro inexistente"));
        this.carroRepository.delete(carro);
    }
}
