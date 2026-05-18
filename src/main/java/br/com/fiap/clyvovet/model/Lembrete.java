package br.com.fiap.clyvovet.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_lembretes")
@Data
@NoArgsConstructor
public class Lembrete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dataHoraAlerta;

    @Column(nullable = false, length = 500)
    private String mensagemPersonalizada;

    @ManyToOne
    @JoinColumn(name = "plano_id", nullable = false)
    @ToString.Exclude
    private PlanoTratamento planoTratamento;
}