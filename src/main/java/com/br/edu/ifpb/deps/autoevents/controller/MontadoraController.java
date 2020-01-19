package com.br.edu.ifpb.deps.autoevents.controller;

import com.br.edu.ifpb.deps.autoevents.dto.request.MontadoraRequest;
import com.br.edu.ifpb.deps.autoevents.dto.response.MontadoraResponse;
import com.br.edu.ifpb.deps.autoevents.model.Montadora;
import com.br.edu.ifpb.deps.autoevents.service.MontadoraService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/montadoras")
@CrossOrigin(origins = "*", maxAge = 3600)
public class MontadoraController {
    private MontadoraService montadoraService;

    public MontadoraController(MontadoraService montadoraService) {
        this.montadoraService = montadoraService;
    }

    @PostMapping
    public ResponseEntity<MontadoraResponse> cadastrarMontadora(@Valid @RequestBody MontadoraRequest request){
        Montadora montadora = this.montadoraService.cadastrarMontadora(request);
        return ResponseEntity.ok(MontadoraResponse.from(montadora));
    }

    @GetMapping
    public ResponseEntity<Page<MontadoraResponse>> listarMontadoras(Pageable pageable){
        Page<Montadora> montadoras = this.montadoraService.listarMontadoras(pageable);
        return ResponseEntity.ok(MontadoraResponse.from(montadoras));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MontadoraResponse> buscarMontadora(@Valid @PathVariable(value = "id") Long id){
        Montadora montadora = this.montadoraService.buscarMontadora(id);
        return ResponseEntity.ok(MontadoraResponse.from(montadora));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MontadoraResponse> atualizarMontadora(@Valid @PathVariable(value = "id") Long id,
                                                                @Valid @RequestBody MontadoraRequest request){
        Montadora montadora = this.montadoraService.atualizarMontadora(id, request);
        return ResponseEntity.ok(MontadoraResponse.from(montadora));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerMontadora(@Valid @PathVariable(value = "id") Long id){
        this.montadoraService.removerMontadora(id);
        return ResponseEntity.ok().build();
    }

}
