package br.com.fiap.clyvopaws.dto;

import java.time.LocalDateTime;

public record ConsultaResponseDTO(
        Long id, LocalDateTime dataConsulta, String clinica,
        String nomeVeterinario, String laudo,
        PetResponseDTO pet
) {}