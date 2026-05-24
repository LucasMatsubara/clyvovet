package br.com.fiap.clyvopaws.service;

import br.com.fiap.clyvopaws.dto.*;
import br.com.fiap.clyvopaws.model.*;
import br.com.fiap.clyvopaws.repository.*;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ConsultaService {
    private final ConsultaRepository consultaRepository;
    private final PetRepository petRepository;
    private final PetService petService;

    @CacheEvict(value = "historicoConsultas", allEntries = true)
    @Transactional
    public ConsultaResponseDTO cadastrar(ConsultaRequestDTO request) {
        Pet pet = petRepository.findById(request.petId()).orElseThrow(() -> new EntityNotFoundException("Pet não encontrado."));
        Consulta consulta = new Consulta();
        consulta.setDataConsulta(request.dataConsulta());
        consulta.setClinica(request.clinica());
        consulta.setNomeVeterinario(request.nomeVeterinario());
        consulta.setLaudo(request.laudo());
        consulta.setPet(pet);
        return toResponseDTO(consultaRepository.save(consulta));
    }

    @Transactional(readOnly = true)
    public ConsultaResponseDTO buscarPorId(Long id) {
        Consulta consulta = consultaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada."));
        return toResponseDTO(consulta);
    }

    @Transactional(readOnly = true)
    public Page<ConsultaResponseDTO> listarTodas(Pageable pageable) {
        return consultaRepository.findAll(pageable).map(this::toResponseDTO);
    }

    @Cacheable(value = "historicoConsultas")
    @Transactional(readOnly = true)
    public Page<ConsultaResponseDTO> listarHistoricoPorPet(Long petId, Pageable pageable) {
        return consultaRepository.findByPetIdOrderByDataConsultaDesc(petId, pageable).map(this::toResponseDTO);
    }

    @CacheEvict(value = "historicoConsultas", allEntries = true)
    @Transactional
    public ConsultaResponseDTO atualizar(Long id, ConsultaRequestDTO request) {
        Consulta consulta = consultaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada."));
        consulta.setDataConsulta(request.dataConsulta());
        consulta.setClinica(request.clinica());
        consulta.setNomeVeterinario(request.nomeVeterinario());
        consulta.setLaudo(request.laudo());
        return toResponseDTO(consultaRepository.save(consulta));
    }

    @CacheEvict(value = "historicoConsultas", allEntries = true)
    @Transactional
    public void excluir(Long id) {
        if (!consultaRepository.existsById(id)) throw new EntityNotFoundException("Consulta não encontrada.");
        consultaRepository.deleteById(id);
    }

    public ConsultaResponseDTO toResponseDTO(Consulta consulta) {
        PetResponseDTO petDTO = petService.toResponseDTO(consulta.getPet());
        return new ConsultaResponseDTO(consulta.getId(), consulta.getDataConsulta(), consulta.getClinica(), consulta.getNomeVeterinario(), consulta.getLaudo(), petDTO);
    }
}