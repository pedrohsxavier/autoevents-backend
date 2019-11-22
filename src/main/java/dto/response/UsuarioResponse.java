package dto.response;

import lombok.Data;
import model.Usuario;
import org.springframework.data.domain.Page;

import java.util.Date;

@Data
public class UsuarioResponse {
    private Long id;
    private String nome;
    private Date dataNascimento;
    private String email;
    private String senha;

    public static UsuarioResponse from (Usuario usuario) {
        UsuarioResponse usuarioResponse = new UsuarioResponse();
        usuarioResponse.setDataNascimento(usuario.getDataNascimento());
        usuarioResponse.setNome(usuario.getNome());
        usuarioResponse.setId(usuario.getId());
        usuarioResponse.setEmail(usuario.getEmail());
        usuarioResponse.setSenha(usuario.getSenha());

        return usuarioResponse;
    }

    public static Page<UsuarioResponse> from (Page<Usuario> usuarios) {
        Page<UsuarioResponse> usuariosResponse = usuarios.map(usuario -> {
            UsuarioResponse usuarioResponse = new UsuarioResponse();

            usuarioResponse.setDataNascimento(usuario.getDataNascimento());
            usuarioResponse.setNome(usuario.getNome());
            usuarioResponse.setId(usuario.getId());
            usuarioResponse.setEmail(usuario.getEmail());
            usuarioResponse.setSenha(usuario.getSenha());

            return usuarioResponse;
        });
        return usuariosResponse;
    }
}
