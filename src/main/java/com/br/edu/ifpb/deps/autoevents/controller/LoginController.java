package com.br.edu.ifpb.deps.autoevents.controller;

import com.br.edu.ifpb.deps.autoevents.model.Usuario;
import com.br.edu.ifpb.deps.autoevents.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class LoginController {
    private LoginService service;

    public LoginController(LoginService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Usuario> realizarLogin(@Valid @RequestBody String login, @Valid @RequestBody String senha){
        Usuario usuario = this.service.login(login, senha);
        return ResponseEntity.ok(usuario);
    }
}
