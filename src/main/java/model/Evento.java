package model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false)
    private String pais;

    @Column(nullable = false)
    private LocalDateTime dataEvento;

    @Column(nullable = false)
    private BigDecimal ingressoValor;

    @ManyToOne
    @JoinColumn(name = "usuario_id", foreignKey = @ForeignKey(name = "fk_evento_usuario_id"))
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "montadora_id", foreignKey = @ForeignKey(name = "fk_montadora_usuario_id"))
    private Montadora montadora;
}
