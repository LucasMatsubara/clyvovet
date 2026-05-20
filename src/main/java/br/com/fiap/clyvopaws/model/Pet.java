package br.com.fiap.clyvopaws.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "tb_pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 50)
    private String especie; // "CACHORRO" ou "GATO"

    @Column(nullable = false, length = 50)
    private String raca;

    private Double peso;

    @Column(length = 20)
    private String sexo;

    @Column(nullable = false)
    private LocalDate dataNascimento; // Para calcular a idade dinamicamente

    @Column(length = 500)
    private String descricao;

    private String fotoUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_id", nullable = false)
    private Tutor tutor;

    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
    private List<Consulta> historicoConsultas;
}