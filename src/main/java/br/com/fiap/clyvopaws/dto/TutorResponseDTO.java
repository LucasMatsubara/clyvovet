package br.com.fiap.clyvopaws.dto;

public record TutorResponseDTO(
        Long id,
        String nomeCompleto,
        String email,
        String telefone,
        String fotoUrl
) {}