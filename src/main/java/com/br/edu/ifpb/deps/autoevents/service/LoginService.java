package com.br.edu.ifpb.deps.autoevents.service;

import com.br.edu.ifpb.deps.autoevents.exceptions.DataIntegrityException;
import com.br.edu.ifpb.deps.autoevents.exceptions.ExpiredTokenException;
import com.br.edu.ifpb.deps.autoevents.exceptions.InvalidTokenException;
import com.br.edu.ifpb.deps.autoevents.model.Usuario;
import com.br.edu.ifpb.deps.autoevents.repository.UsuarioRepository;
import com.br.edu.ifpb.deps.autoevents.security.DatabaseEncryptionComponent;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@Service
public class LoginService {
    private UsuarioRepository usuarioRepository;
    private TokenService tokenService;
    private DatabaseEncryptionComponent databaseEncryptionComponent;

    public LoginService(UsuarioRepository usuarioRepository, TokenService tokenService,
                        DatabaseEncryptionComponent databaseEncryptionComponent) {
        this.usuarioRepository = usuarioRepository;
        this.tokenService = tokenService;
        this.databaseEncryptionComponent = databaseEncryptionComponent;
    }

    public Usuario login(String email, String senha){
        Usuario usuario = this.usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe um usuário com esse email"));

        usuario.setToken(tokenService.generateToken(usuario));

        if(senha.equals(databaseEncryptionComponent.decryptPassword(usuario.getSenha()))
                && !usuario.getToken().isEmpty() && validate(usuario.getToken())){
            usuario.setToken(tokenService.generateToken(usuario));
            return usuario;
        }else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciais inválidas");
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
