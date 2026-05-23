package br.com.fiap.clyvopaws.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "tb_consulta")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dataConsulta;

    @Column(nullable = false, length = 100)
    private String clinica;

    @Column(length = 100)
    private String nomeVeterinario;

    @Column(length = 4000)
    private String laudo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    @OneToMany(mappedBy = "consulta", cascade = CascadeType.ALL)
    private List<Medicamento> medicamentos;

    @OneToMany(mappedBy = "consulta", cascade = CascadeType.ALL)
    private List<Agendamento> agendamentos;
}