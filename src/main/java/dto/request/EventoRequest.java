package dto.request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class EventoRequest {
    private String nome;
    private String cidade;
    private String pais;
    private Long usuarioId;
    private Long montadoraId;
    private LocalDateTime dataEvento;
    private BigDecimal ingressoValor;
}
