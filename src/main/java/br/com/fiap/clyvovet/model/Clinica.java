package br.com.fiap.clyvovet.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_clinicas")
@Data
@NoArgsConstructor
public class Clinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nomeUnidade;

    @Column(nullable = false, length = 100)
    private String redeFranquia;

    @Column(length = 20)
    private String cnpj;

    @Column(length = 150)
    private String cidade;
}