package com.br.edu.ifpb.deps.autoevents.service;

import com.br.edu.ifpb.deps.autoevents.model.Usuario;
import com.br.edu.ifpb.deps.autoevents.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class LoginService {
    private UsuarioRepository usuarioRepository;
    private TokenService tokenService;

    public LoginService(UsuarioRepository usuarioRepository, TokenService tokenService) {
        this.usuarioRepository = usuarioRepository;
        this.tokenService = tokenService;
    }

    public Usuario login(String email, String senha){
        Usuario usuario = this.usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe um usuário com esse email"));

        if(senha.equals(usuario.getSenha())){
            usuario.setToken(tokenService.generateToken(usuario));
            return usuario;
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Credenciais inválidas");
        }
    }
}
