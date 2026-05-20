package br.com.fiap.clyvopaws.dto;

import java.time.LocalDate;

public record MedicamentoResponseDTO(
        Long id,
        String nome,
        String dosagem,
        String frequencia,
        LocalDate dataInicio,
        Integer duracaoDias,
        Long consultaId
) {}