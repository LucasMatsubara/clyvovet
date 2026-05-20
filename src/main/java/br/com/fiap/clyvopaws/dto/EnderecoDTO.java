package br.com.fiap.clyvopaws.dto;

public record EnderecoDTO(
        String rua,
        String numero,
        String complemento,
        String cep,
        String cidade,
        String estado
) {}