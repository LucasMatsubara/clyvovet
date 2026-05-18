package br.com.fiap.clyvovet.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_consultas")
@Data
@NoArgsConstructor
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @Column(name = "peso_na_consulta", nullable = false)
    private Double pesoNaConsulta;

    @Column(columnDefinition = "VARCHAR2(1000)", nullable = false)
    private String queixaPrincipal;

    @Column(columnDefinition = "VARCHAR2(1000)")
    private String diagnostico;

    @Column(columnDefinition = "VARCHAR2(1000)")
    private String prescricao;

    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = false)
    @ToString.Exclude
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "clinica_id", nullable = false)
    @ToString.Exclude
    private Clinica clinica;
}