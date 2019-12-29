package com.br.edu.ifpb.deps.autoevents.service;

import com.br.edu.ifpb.deps.autoevents.exceptions.DataIntegrityException;
import com.br.edu.ifpb.deps.autoevents.exceptions.ExpiredTokenException;
import com.br.edu.ifpb.deps.autoevents.exceptions.InvalidTokenException;
import com.br.edu.ifpb.deps.autoevents.model.Usuario;
import com.br.edu.ifpb.deps.autoevents.repository.UsuarioRepository;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@Service
public class LoginService {
    private UsuarioRepository usuarioRepository;
    private TokenService tokenService;

    public LoginService(UsuarioRepository usuarioRepository, TokenService tokenService) {
        this.usuarioRepository = usuarioRepository;
        this.tokenService = tokenService;
    }

    public Usuario login(String email, String senha, String token){
        Usuario usuario = this.usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe um usuário com esse email"));

        if(senha.equals(usuario.getSenha()) && !token.isEmpty() && validate(token)){
            usuario.setToken(tokenService.generateToken(usuario));
            return usuario;
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Credenciais inválidas");
        }
    }

    public boolean validate(String token){
        try{
            String tokenTratado = token.replace("Bearer ", "");
            Claims claims = tokenService.decodeToken(tokenTratado);

            if (claims.getExpiration().before(new Date(System.currentTimeMillis()))){
                throw new ExpiredTokenException("Token Expirado");
            }
            return true;
        } catch (ExpiredTokenException e){
            e.printStackTrace();
            throw e;
        } catch (InvalidTokenException it){
            it.printStackTrace();
            throw new InvalidTokenException("Token inválido");
        }
    }
}
