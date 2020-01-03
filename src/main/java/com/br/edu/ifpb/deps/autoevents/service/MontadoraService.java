package com.br.edu.ifpb.deps.autoevents.service;

import com.br.edu.ifpb.deps.autoevents.dto.request.MontadoraRequest;
import com.br.edu.ifpb.deps.autoevents.model.Montadora;
import com.br.edu.ifpb.deps.autoevents.repository.MontadoraRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MontadoraService {
    private MontadoraRepository montadoraRepository;

    public MontadoraService(MontadoraRepository montadoraRepository) {
        this.montadoraRepository = montadoraRepository;
    }

    public Montadora cadastrarMontadora(MontadoraRequest request){
        Montadora montadora = new Montadora();

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(request, montadora);

        return this.montadoraRepository.save(montadora);
    }

    public Montadora atualizarMontadora(Long id, MontadoraRequest request){
        Montadora montadora = this.montadoraRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Montadora inexistente"));

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(request, montadora);

        return this.montadoraRepository.save(montadora);
    }

    public Page<Montadora> listarMontadoras(Pageable pageable){
        return this.montadoraRepository.findAll(pageable);
    }

    public Montadora buscarMontadora(Long id){
        Montadora montadora = this.montadoraRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Montadora inexistente"));

        return montadora;
    }

    public void removerMontadora(Long id){
        Montadora montadora = this.montadoraRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Montadora inexistente"));

        this.montadoraRepository.delete(montadora);
    }
}
