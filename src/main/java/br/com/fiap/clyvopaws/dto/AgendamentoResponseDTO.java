package br.com.fiap.clyvopaws.dto;

import java.time.LocalDateTime;

public record AgendamentoResponseDTO(
        Long id, LocalDateTime dataHora, String titulo, String descricao,
        ConsultaResponseDTO consulta
) {}