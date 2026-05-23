package br.com.fiap.clyvopaws.dto;

import jakarta.validation.constraints.*;

public record TutorRequestDTO(
        @NotBlank String nomeCompleto,
        @NotBlank @Size(min=11, max=14) String cpf,
        String telefone,
        @NotBlank @Email String email,
        @NotBlank @Size(min=6) String senha,
        String fotoUrl,
        EnderecoDTO endereco
) {}