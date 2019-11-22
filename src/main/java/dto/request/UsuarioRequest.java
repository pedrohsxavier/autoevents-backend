package dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class UsuarioRequest {
    private String nome;
    private Date dataNascimento;
    private String email;
    private String senha;
}
