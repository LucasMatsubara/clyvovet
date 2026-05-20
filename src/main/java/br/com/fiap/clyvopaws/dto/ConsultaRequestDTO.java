package br.com.fiap.clyvopaws.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record ConsultaRequestDTO(
        @NotNull(message = "A data da consulta é obrigatória")
        LocalDateTime dataConsulta,

        @NotBlank(message = "O nome da clínica é obrigatório")
        String clinica,

        String nomeVeterinario,
        String laudo,

        @NotNull(message = "O ID do pet é obrigatório")
        Long petId
) {}
