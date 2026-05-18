package br.com.fiap.clyvovet.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_diretrizes_raca")
@Data
@NoArgsConstructor
public class DiretrizRaca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String raca;

    @Column(nullable = false, length = 150)
    private String doencaRelacionada;

    @Column(nullable = false)
    private Integer idadeAlertaMeses;

    @Column(columnDefinition = "VARCHAR2(1000)", nullable = false)
    private String dicaPrevencao;

    @Column(length = 255)
    private String cuidadosRecomendados;
}