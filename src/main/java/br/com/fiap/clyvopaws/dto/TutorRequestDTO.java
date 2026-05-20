package br.com.fiap.clyvopaws.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TutorRequestDTO(
        @NotBlank(message = "O nome é obrigatório")
        String nomeCompleto,

        @NotBlank(message = "O CPF é obrigatório")
        @Size(min = 11, max = 14, message = "O CPF deve ter tamanho válido")
        String cpf,

        String telefone,

        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "Formato de e-mail inválido")
        String email,

        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
        String senha,

        String fotoUrl
) {}