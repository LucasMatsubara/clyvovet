package br.com.fiap.clyvovet.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_planos_tratamento")
@Data
@NoArgsConstructor
public class PlanoTratamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(columnDefinition = "VARCHAR2(1000)")
    private String descricao;

    @Column(nullable = false)
    private LocalDate dataInicio;

    private LocalDate dataFim;

    private Boolean ativo = true;

    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = false)
    @ToString.Exclude
    private Pet pet;

    @OneToMany(mappedBy = "planoTratamento", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Lembrete> lembretes;
}