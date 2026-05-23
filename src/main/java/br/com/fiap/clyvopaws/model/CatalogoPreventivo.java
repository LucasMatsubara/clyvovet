package br.com.fiap.clyvopaws.model;

import br.com.fiap.clyvopaws.enums.Especie;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_catalogo_preventivo")
public class CatalogoPreventivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Especie especie;

    @Column(nullable = false, length = 50)
    private String raca;

    @Column(nullable = false, length = 150)
    private String doencaPredisposta;

    private Integer idadeAlertaMeses;

    @Column(length = 4000)
    private String dicaPrevencao;

    @Column(length = 4000)
    private String cuidadosRecomendados;
}