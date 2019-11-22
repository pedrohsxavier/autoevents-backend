package model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Montadora {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String pais;
}
