package com.br.edu.ifpb.deps.autoevents.controller;

import com.br.edu.ifpb.deps.autoevents.dto.request.LoginRequest;
import com.br.edu.ifpb.deps.autoevents.dto.response.LoginResponse;
import com.br.edu.ifpb.deps.autoevents.model.Usuario;
import com.br.edu.ifpb.deps.autoevents.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = "*", maxAge = 3600)
public class LoginController {
    private LoginService service;

    public LoginController(LoginService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<LoginResponse> realizarLogin(@Valid @RequestBody LoginRequest request) throws InterruptedException{    	
        Usuario usuario = this.service.login(request.getEmail(), request.getSenha());        
        return ResponseEntity.ok(LoginResponse.from(usuario, "Bearer "));
    }
}
