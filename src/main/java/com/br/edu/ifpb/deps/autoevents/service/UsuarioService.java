package com.br.edu.ifpb.deps.autoevents.service;

import com.br.edu.ifpb.deps.autoevents.dto.request.UsuarioRequest;
import com.br.edu.ifpb.deps.autoevents.model.Usuario;
import com.br.edu.ifpb.deps.autoevents.security.DatabaseEncryptionComponent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.br.edu.ifpb.deps.autoevents.repository.UsuarioRepository;

import java.time.LocalDate;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final TokenService tokenService;
    private final DatabaseEncryptionComponent databaseEncryptionComponent;


    public UsuarioService(UsuarioRepository usuarioRepository, TokenService tokenService,
                          DatabaseEncryptionComponent databaseEncryptionComponent) {
        this.usuarioRepository = usuarioRepository;
        this.tokenService = tokenService;
        this.databaseEncryptionComponent = databaseEncryptionComponent;
    }

    public Usuario criarUsuario(UsuarioRequest request) {
        Usuario usuario = new Usuario();

        if (request.getDataNascimento().compareTo(LocalDate.now()) > 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data inválida para ser cadastrada.");
        } else {
            usuario.setDataNascimento(request.getDataNascimento());
        }

        usuario.setEmail(request.getEmail());
        usuario.setNome(request.getNome());
        usuario.setSenha(databaseEncryptionComponent.encryptPassword(request.getSenha()));
        usuario.setToken(tokenService.generateToken(usuario));

        return this.usuarioRepository.save(usuario);
    }

    public Usuario atualizarUsuario(Long id, UsuarioRequest request) {
        Usuario usuario = this.usuarioRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário inexistente"));

        if (request.getDataNascimento().compareTo(LocalDate.now()) > 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data inválida para ser alterada");
        } else {
            usuario.setDataNascimento(request.getDataNascimento());
        }

        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        usuario.setSenha(databaseEncryptionComponent.encryptPassword(request.getSenha()));

        return this.usuarioRepository.save(usuario);
    }

    public Page<Usuario> listarUsuarios(Pageable pageable) {
        return this.usuarioRepository.findAll(pageable);
    }

    public Usuario buscarUsuario(Long id) {
        Usuario usuario = this.usuarioRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND ,"Usuário inexistente"));
        return usuario;
    }

    public void removerUsuario(Long id) {
        Usuario usuario = this.usuarioRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário inexistente"));
        this.usuarioRepository.delete(usuario);
    }
}
