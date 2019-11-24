package service;

import dto.request.UsuarioRequest;
import model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import repository.UsuarioRepository;

import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario criarUsuario(UsuarioRequest request) {
        Usuario usuario = this.usuarioRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "E-mail já cadastrado na base de dados"));

        usuario.setDataNascimento(request.getDataNascimento());
        usuario.setEmail(request.getEmail());
        usuario.setNome(request.getNome());
        usuario.setSenha(request.getSenha());

        return usuario;
    }

    public Usuario atualizarUsuario(Long id, UsuarioRequest request) {
        Usuario usuario = this.usuarioRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário inexistente"));

        Optional<Usuario> busca = this.usuarioRepository.findByEmail(request.getEmail());

        if(!busca.isPresent()) {
            usuario.setEmail(request.getEmail());
        }

        usuario.setDataNascimento(request.getDataNascimento());
        usuario.setNome(request.getNome());
        usuario.setSenha(request.getSenha());

        return usuario;
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
