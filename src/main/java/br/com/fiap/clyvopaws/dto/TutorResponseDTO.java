package br.com.fiap.clyvopaws.dto;

import java.util.List;

public record TutorResponseDTO(
        Long id,
        String nomeCompleto,
        String email,
        String telefone,
        String fotoUrl,
        EnderecoDTO endereco,
        List<PetResumoDTO> pets
) {}