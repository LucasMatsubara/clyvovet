package br.com.fiap.clyvopaws.dto;

import br.com.fiap.clyvopaws.enums.StatusMedicamento;

import java.time.LocalDate;

public record MedicamentoResponseDTO(
        Long id, String nome, String dosagem, String frequencia,
        LocalDate dataInicio, Integer duracaoDias, StatusMedicamento status,
        ConsultaResponseDTO consulta
) {}