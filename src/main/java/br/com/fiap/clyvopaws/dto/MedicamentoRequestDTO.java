package br.com.fiap.clyvopaws.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record MedicamentoRequestDTO(
        @NotBlank(message = "O nome do medicamento é obrigatório")
        String nome,

        @NotBlank(message = "A dosagem é obrigatória")
        String dosagem,

        @NotBlank(message = "A frequência é obrigatória")
        String frequencia,

        @NotNull(message = "A data de início é obrigatória")
        LocalDate dataInicio,

        Integer duracaoDias,

        @NotNull(message = "O ID da consulta é obrigatório")
        Long consultaId
) {}