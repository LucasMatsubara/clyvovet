package br.com.fiap.clyvopaws.service;

import br.com.fiap.clyvopaws.dto.*;
import br.com.fiap.clyvopaws.model.*;
import br.com.fiap.clyvopaws.repository.TutorRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TutorService {
    private final TutorRepository tutorRepository;

    @Transactional
    public TutorResponseDTO cadastrar(TutorRequestDTO request) {
        if (tutorRepository.existsByEmail(request.email())) throw new IllegalArgumentException("E-mail já cadastrado.");
        if (tutorRepository.existsByCpf(request.cpf())) throw new IllegalArgumentException("CPF já cadastrado.");

        Tutor tutor = new Tutor();
        tutor.setNomeCompleto(request.nomeCompleto());
        tutor.setCpf(request.cpf());
        tutor.setTelefone(request.telefone());
        tutor.setEmail(request.email());
        tutor.setSenha(request.senha());
        tutor.setFotoUrl(request.fotoUrl());

        if (request.endereco() != null) {
            Endereco endereco = new Endereco();
            endereco.setRua(request.endereco().rua());
            endereco.setNumero(request.endereco().numero());
            endereco.setComplemento(request.endereco().complemento());
            endereco.setCep(request.endereco().cep());
            endereco.setCidade(request.endereco().cidade());
            endereco.setEstado(request.endereco().estado());
            tutor.setEndereco(endereco);
        }

        return toResponseDTO(tutorRepository.save(tutor));
    }

    @Transactional(readOnly = true)
    public TutorResponseDTO buscarPorId(Long id) {
        Tutor tutor = tutorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tutor não encontrado."));
        return toResponseDTO(tutor);
    }

    @Transactional(readOnly = true)
    public Page<TutorResponseDTO> listarTodos(Pageable pageable) {
        return tutorRepository.findAll(pageable).map(this::toResponseDTO);
    }

    @Transactional
    public TutorResponseDTO atualizar(Long id, TutorRequestDTO request) {
        Tutor tutor = tutorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tutor não encontrado."));
        tutor.setNomeCompleto(request.nomeCompleto());
        tutor.setTelefone(request.telefone());
        tutor.setFotoUrl(request.fotoUrl());

        if (request.endereco() != null) {
            if (tutor.getEndereco() == null) tutor.setEndereco(new Endereco());
            tutor.getEndereco().setRua(request.endereco().rua());
            tutor.getEndereco().setNumero(request.endereco().numero());
            tutor.getEndereco().setComplemento(request.endereco().complemento());
            tutor.getEndereco().setCep(request.endereco().cep());
            tutor.getEndereco().setCidade(request.endereco().cidade());
            tutor.getEndereco().setEstado(request.endereco().estado());
        }

        return toResponseDTO(tutorRepository.save(tutor));
    }

    @Transactional
    public void excluir(Long id) {
        if (!tutorRepository.existsById(id)) throw new EntityNotFoundException("Tutor não encontrado.");
        tutorRepository.deleteById(id);
    }

    private TutorResponseDTO toResponseDTO(Tutor tutor) {
        EnderecoDTO enderecoDTO = null;
        if (tutor.getEndereco() != null) {
            enderecoDTO = new EnderecoDTO(
                    tutor.getEndereco().getRua(), tutor.getEndereco().getNumero(),
                    tutor.getEndereco().getComplemento(), tutor.getEndereco().getCep(),
                    tutor.getEndereco().getCidade(), tutor.getEndereco().getEstado()
            );
        }

        List<PetResumoDTO> petDTOs = List.of();
        if (tutor.getPets() != null && !tutor.getPets().isEmpty()) {
            petDTOs = tutor.getPets().stream().map(pet ->
                    new PetResumoDTO(pet.getId(), pet.getNome())
            ).toList();
        }

        return new TutorResponseDTO(tutor.getId(), tutor.getNomeCompleto(), tutor.getEmail(),
                tutor.getTelefone(), tutor.getFotoUrl(), enderecoDTO, petDTOs);
    }
}