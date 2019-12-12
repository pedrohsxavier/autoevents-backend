package com.br.edu.ifpb.deps.autoevents.controller;

import com.br.edu.ifpb.deps.autoevents.dto.request.CarroRequest;
import com.br.edu.ifpb.deps.autoevents.dto.response.CarroResponse;
import com.br.edu.ifpb.deps.autoevents.model.Carro;
import com.br.edu.ifpb.deps.autoevents.service.CarroService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/carros")
public class CarroController {
    private CarroService carroService;

    public CarroController(CarroService carroService) {
        this.carroService = carroService;
    }

    public ResponseEntity<CarroResponse> cadastrarCarro(@Valid @RequestBody CarroRequest request){
        Carro carro = this.carroService.cadastrarCarro(request);
        return ResponseEntity.ok(CarroResponse.from(carro));
    }

    public ResponseEntity<Page<CarroResponse>> listarCarros(Pageable pageable){
        Page<Carro> carros = this.carroService.listarCarros(pageable);
        return ResponseEntity.ok(CarroResponse.from(carros));
    }

    public ResponseEntity<CarroResponse> buscarCarro(@Valid @PathVariable(value = "id") Long id){
        Carro carro = this.carroService.buscarCarro(id);
        return ResponseEntity.ok(CarroResponse.from(carro));
    }

    public ResponseEntity<CarroResponse> atualizarCarro(@Valid @PathVariable(value = "id")Long id,
                                                        @Valid @RequestBody CarroRequest request){
        Carro carro = this.carroService.atualizarCarro(id, request);
        return ResponseEntity.ok(CarroResponse.from(carro));
    }

    public ResponseEntity<Void> removerCarro(@Valid @PathVariable(value = "id")Long id){
        this.carroService.removerCarro(id);
        return ResponseEntity.ok().build();
    }
}
