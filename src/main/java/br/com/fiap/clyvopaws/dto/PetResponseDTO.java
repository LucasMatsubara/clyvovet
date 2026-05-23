package br.com.fiap.clyvopaws.dto;

import br.com.fiap.clyvopaws.enums.*;

import java.time.LocalDate;

public record PetResponseDTO(
        Long id, String nome, Especie especie, String raca, Double peso,
        Sexo sexo, LocalDate dataNascimento, String descricao, String fotoUrl,
        TutorResumoDTO tutor
) {}