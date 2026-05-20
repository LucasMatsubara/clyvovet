package br.com.fiap.clyvopaws.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record PetRequestDTO(
        @NotBlank(message = "O nome do pet é obrigatório")
        String nome,

        @NotBlank(message = "A espécie é obrigatória")
        String especie,

        @NotBlank(message = "A raça é obrigatória")
        String raca,

        Double peso,

        String sexo,

        @NotNull(message = "A data de nascimento é obrigatória para calcularmos o plano preventivo")
        LocalDate dataNascimento,

        String descricao,

        String fotoUrl,

        @NotNull(message = "O ID do tutor é obrigatório")
        Long tutorId
) {}