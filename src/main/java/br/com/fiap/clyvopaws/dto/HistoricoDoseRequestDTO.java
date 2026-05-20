package br.com.fiap.clyvopaws.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record HistoricoDoseRequestDTO(
        @NotNull(message = "A data e hora são obrigatórias")
        LocalDateTime dataHoraToma,

        @NotNull(message = "O ID do medicamento é obrigatório")
        Long medicamentoId
) {}