package br.com.fiap.clyvovet.service;

import br.com.fiap.clyvovet.dto.PetRequestDTO;
import br.com.fiap.clyvovet.dto.PetResponseDTO;
import br.com.fiap.clyvovet.model.Pet;
import br.com.fiap.clyvovet.model.Tutor;
import br.com.fiap.clyvovet.repository.PetRepository;
import br.com.fiap.clyvovet.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private TutorRepository tutorRepository;

    public PetResponseDTO cadastrar(PetRequestDTO dto) {
        Tutor tutor = tutorRepository.findById(dto.getTutorId())
                .orElseThrow(() -> new RuntimeException("Tutor não encontrado com o ID informado"));

        Pet pet = new Pet();
        pet.setNome(dto.getNome());
        pet.setRaca(dto.getRaca());

        pet.setEspecie(dto.getEspecie());
        pet.setFaseVida(dto.getFaseVida());
        pet.setPorte(dto.getPorte());

        pet.setDescricao(dto.getDescricao());
        pet.setFotoUrl(dto.getFotoUrl());
        pet.setDataNascimento(dto.getDataNascimento());
        pet.setTutor(tutor);

        Pet petSalvo = petRepository.save(pet);
        return converterParaDto(petSalvo);
    }

    public List<PetResponseDTO> listarPorTutor(Long tutorId) {
        List<Pet> pets = petRepository.findByTutorId(tutorId);
        return pets.stream().map(this::converterParaDto).collect(Collectors.toList());
    }

    private PetResponseDTO converterParaDto(Pet pet) {
        int idadeMeses = (int) ChronoUnit.MONTHS.between(pet.getDataNascimento(), LocalDate.now());

        return new PetResponseDTO(
                pet.getId(),
                pet.getNome(),
                pet.getRaca(),
                idadeMeses,
                pet.getFotoUrl()
        );
    }
}