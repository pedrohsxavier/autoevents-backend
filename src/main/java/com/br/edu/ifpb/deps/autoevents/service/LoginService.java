package com.br.edu.ifpb.deps.autoevents.service;

import com.br.edu.ifpb.deps.autoevents.model.Usuario;
import com.br.edu.ifpb.deps.autoevents.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class LoginService {
    private UsuarioRepository usuarioRepository;

    public LoginService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario login(String email, String senha){
        Usuario usuario = this.usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe um usuário com esse email"));

        if((email.equalsIgnoreCase(usuario.getEmail())) && (senha.equals(usuario.getSenha()))){
            return usuario;
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Credenciais inválidas");
        }
    }
}
