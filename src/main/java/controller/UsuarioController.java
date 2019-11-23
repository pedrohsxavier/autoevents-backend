package controller;

import dto.request.UsuarioRequest;
import dto.response.UsuarioResponse;
import model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.UsuarioService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> cadastrarUsuario(@Valid @RequestBody UsuarioRequest request) {
        Usuario usuario = this.service.criarUsuario(request);
        return ResponseEntity.ok(UsuarioResponse.from(usuario));
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioResponse>> listarUsuarios(Pageable pageable) {
        Page<Usuario> usuarios = this.service.listarUsuarios(pageable);
        return ResponseEntity.ok(UsuarioResponse.from(usuarios));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizarUsuario(@Valid @PathVariable(value = "id") Long id,
                                                            @Valid @RequestBody UsuarioRequest request) {
        Usuario usuario = this.service.atualizarUsuario(id, request);
        return ResponseEntity.ok(UsuarioResponse.from(usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerUsuario(@Valid @PathVariable(value = "id") Long id) {
        this.service.removerUsuario(id);
        return ResponseEntity.ok().build();
    }
}
