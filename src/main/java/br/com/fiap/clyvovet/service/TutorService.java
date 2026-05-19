package br.com.fiap.clyvovet.service;

import br.com.fiap.clyvovet.dto.TutorRequestDTO;
import br.com.fiap.clyvovet.dto.TutorResponseDTO;
import br.com.fiap.clyvovet.model.Tutor;
import br.com.fiap.clyvovet.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TutorService {

    @Autowired
    private TutorRepository tutorRepository;

    @Transactional
    public TutorResponseDTO cadastrar(TutorRequestDTO dto) {
        Tutor tutor = new Tutor();
        tutor.setNome(dto.getNome());
        tutor.setTelefone(dto.getTelefone());
        tutor.setEmail(dto.getEmail());
        tutor.setCpf(dto.getCpf());
        tutor.setEndereco(dto.getEndereco());

        Tutor tutorSalvo = tutorRepository.save(tutor);
        return mapperParaDto(tutorSalvo);
    }

    public List<TutorResponseDTO> listarTodos() {
        return tutorRepository.findAll().stream()
                .map(this::mapperParaDto)
                .collect(Collectors.toList());
    }

    public TutorResponseDTO buscarPorId(Long id) {
        Tutor tutor = tutorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tutor não encontrado no sistema."));
        return mapperParaDto(tutor);
    }

    private TutorResponseDTO mapperParaDto(Tutor tutor) {
        return new TutorResponseDTO(
                tutor.getId(),
                tutor.getNome(),
                tutor.getTelefone(),
                tutor.getEmail(),
                tutor.getCpf(),
                tutor.getEndereco()
        );
    }
}