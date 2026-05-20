package br.com.fiap.clyvopaws.service;

import br.com.fiap.clyvopaws.dto.PetRequestDTO;
import br.com.fiap.clyvopaws.dto.PetResponseDTO;
import br.com.fiap.clyvopaws.model.Pet;
import br.com.fiap.clyvopaws.model.Tutor;
import br.com.fiap.clyvopaws.repository.PetRepository;
import br.com.fiap.clyvopaws.repository.TutorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final TutorRepository tutorRepository;

    @Transactional
    public PetResponseDTO cadastrar(PetRequestDTO request) {
        Tutor tutor = tutorRepository.findById(request.tutorId())
                .orElseThrow(() -> new EntityNotFoundException("Tutor não encontrado com o ID: " + request.tutorId()));

        Pet pet = new Pet();
        pet.setNome(request.nome());
        pet.setEspecie(request.especie());
        pet.setRaca(request.raca());
        pet.setPeso(request.peso());
        pet.setSexo(request.sexo());
        pet.setDataNascimento(request.dataNascimento());
        pet.setDescricao(request.descricao());
        pet.setFotoUrl(request.fotoUrl());
        pet.setTutor(tutor);

        pet = petRepository.save(pet);

        return toResponseDTO(pet);
    }

    public PetResponseDTO buscarPorId(Long id) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pet não encontrado com o ID: " + id));
        return toResponseDTO(pet);
    }

    public List<PetResponseDTO> listarPorTutor(Long tutorId) {
        return petRepository.findByTutorId(tutorId).stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    private PetResponseDTO toResponseDTO(Pet pet) {
        return new PetResponseDTO(
                pet.getId(),
                pet.getNome(),
                pet.getEspecie(),
                pet.getRaca(),
                pet.getPeso(),
                pet.getSexo(),
                pet.getDataNascimento(),
                pet.getDescricao(),
                pet.getFotoUrl(),
                pet.getTutor().getId()
        );
    }
}