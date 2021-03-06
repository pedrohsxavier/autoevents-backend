package com.br.edu.ifpb.deps.autoevents.controller;

import com.br.edu.ifpb.deps.autoevents.dto.request.UsuarioRequest;
import com.br.edu.ifpb.deps.autoevents.dto.response.UsuarioResponse;
import com.br.edu.ifpb.deps.autoevents.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.br.edu.ifpb.deps.autoevents.service.UsuarioService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UsuarioController {
    private UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> cadastrarUsuario(@Valid @RequestBody UsuarioRequest request) throws InterruptedException {
    	System.out.println(request);
        Usuario usuario = this.service.criarUsuario(request);
        return ResponseEntity.ok(UsuarioResponse.from(usuario));
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioResponse>> listarUsuarios(Pageable pageable) {
        Page<Usuario> usuarios = this.service.listarUsuarios(pageable);
        return ResponseEntity.ok(UsuarioResponse.from(usuarios));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscarUsuario(@Valid @PathVariable("id") Long id) throws InterruptedException{
        Usuario usuario = this.service.buscarUsuario(id);
        return ResponseEntity.ok(UsuarioResponse.from(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizarUsuario(@Valid @PathVariable(value = "id") Long id,
                                                            @Valid @RequestBody UsuarioRequest request) throws InterruptedException {
        Usuario usuario = this.service.atualizarUsuario(id, request);
        return ResponseEntity.ok(UsuarioResponse.from(usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerUsuario(@Valid @PathVariable(value = "id") Long id) {
        this.service.removerUsuario(id);
        return ResponseEntity.ok().build();
    }
}
