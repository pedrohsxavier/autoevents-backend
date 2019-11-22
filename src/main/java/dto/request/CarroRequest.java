package dto.request;

import lombok.Data;
import model.Montadora;

import java.math.BigDecimal;

@Data
public class CarroRequest {
    private String nome;
    private int ano;
    private BigDecimal valor;
    private Long montadoraId;
}
