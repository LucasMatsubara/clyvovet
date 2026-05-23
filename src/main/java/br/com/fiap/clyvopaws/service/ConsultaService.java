package br.com.fiap.clyvopaws.service;

import br.com.fiap.clyvopaws.dto.ConsultaRequestDTO;
import br.com.fiap.clyvopaws.dto.ConsultaResponseDTO;
import br.com.fiap.clyvopaws.model.Consulta;
import br.com.fiap.clyvopaws.model.Pet;
import br.com.fiap.clyvopaws.repository.ConsultaRepository;
import br.com.fiap.clyvopaws.repository.PetRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PetRepository petRepository;

    @Transactional
    public ConsultaResponseDTO cadastrar(ConsultaRequestDTO request) {
        Pet pet = petRepository.findById(request.petId())
                .orElseThrow(() -> new EntityNotFoundException("Pet não encontrado com ID: " + request.petId()));

        Consulta consulta = new Consulta();
        consulta.setDataConsulta(request.dataConsulta());
        consulta.setClinica(request.clinica());
        consulta.setNomeVeterinario(request.nomeVeterinario());
        consulta.setLaudo(request.laudo());
        consulta.setPet(pet);

        consulta = consultaRepository.save(consulta);
        return toResponseDTO(consulta);
    }

    public List<ConsultaResponseDTO> listarHistoricoPorPet(Long petId) {
        return consultaRepository.findByPetIdOrderByDataConsultaDesc(petId).stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    private ConsultaResponseDTO toResponseDTO(Consulta consulta) {
        return new ConsultaResponseDTO(
                consulta.getId(),
                consulta.getDataConsulta(),
                consulta.getClinica(),
                consulta.getNomeVeterinario(),
                consulta.getLaudo(),
                consulta.getPet().getId()
        );
    }
}