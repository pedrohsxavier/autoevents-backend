package dto.request;

import lombok.Data;
import model.Carro;

import java.util.List;

@Data
public class MontadoraRequest {
    private String nome;
    private String pais;
    private Long carroId;
}
