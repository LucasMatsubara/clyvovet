package br.com.fiap.clyvopaws.dto;

import java.time.LocalDate;

public record PetResponseDTO(
        Long id,
        String nome,
        String especie,
        String raca,
        Double peso,
        String sexo,
        LocalDate dataNascimento,
        String descricao,
        String fotoUrl,
        Long tutorId
) {}