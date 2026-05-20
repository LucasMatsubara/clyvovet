package br.com.fiap.clyvopaws.dto;

import java.time.LocalDateTime;

public record HistoricoDoseResponseDTO(
        Long id,
        LocalDateTime dataHoraToma,
        Long medicamentoId
) {}