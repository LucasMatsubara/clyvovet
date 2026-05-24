package br.com.fiap.clyvopaws.service;

import br.com.fiap.clyvopaws.dto.*;
import br.com.fiap.clyvopaws.model.*;
import br.com.fiap.clyvopaws.repository.*;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MedicamentoService {
    private final MedicamentoRepository medicamentoRepository;
    private final ConsultaRepository consultaRepository;
    private final HistoricoDoseRepository historicoDoseRepository;
    private final ConsultaService consultaService;

    @Transactional
    public MedicamentoResponseDTO cadastrar(MedicamentoRequestDTO request) {
        Consulta consulta = consultaRepository.findById(request.consultaId()).orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada."));
        Medicamento medicamento = new Medicamento();
        medicamento.setNome(request.nome());
        medicamento.setDosagem(request.dosagem());
        medicamento.setFrequencia(request.frequencia());
        medicamento.setDataInicio(request.dataInicio());
        medicamento.setDuracaoDias(request.duracaoDias());
        medicamento.setStatus(request.status());
        medicamento.setConsulta(consulta);
        return toResponseDTO(medicamentoRepository.save(medicamento));
    }

    @Transactional(readOnly = true)
    public MedicamentoResponseDTO buscarPorId(Long id) {
        Medicamento med = medicamentoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Medicamento não encontrado."));
        return toResponseDTO(med);
    }

    @Transactional(readOnly = true)
    public Page<MedicamentoResponseDTO> listarTodos(Pageable pageable) {
        return medicamentoRepository.findAll(pageable).map(this::toResponseDTO);
    }

    @Transactional(readOnly = true)
    public Page<MedicamentoResponseDTO> listarPorConsulta(Long consultaId, Pageable pageable) {
        return medicamentoRepository.findByConsultaId(consultaId, pageable).map(this::toResponseDTO);
    }

    @Transactional
    public HistoricoDoseResponseDTO registrarDose(HistoricoDoseRequestDTO request) {
        Medicamento medicamento = medicamentoRepository.findById(request.medicamentoId()).orElseThrow(() -> new EntityNotFoundException("Medicamento não encontrado."));
        HistoricoDose dose = new HistoricoDose();
        dose.setDataHoraToma(request.dataHoraToma());
        dose.setMedicamento(medicamento);
        dose = historicoDoseRepository.save(dose);
        return new HistoricoDoseResponseDTO(dose.getId(), dose.getDataHoraToma(), toResponseDTO(medicamento));
    }

    @Transactional
    public MedicamentoResponseDTO atualizar(Long id, MedicamentoRequestDTO request) {
        Medicamento med = medicamentoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Medicamento não encontrado."));
        med.setDosagem(request.dosagem());
        med.setFrequencia(request.frequencia());
        med.setStatus(request.status());
        return toResponseDTO(medicamentoRepository.save(med));
    }

    @Transactional
    public void excluir(Long id) {
        if (!medicamentoRepository.existsById(id)) throw new EntityNotFoundException("Medicamento não encontrado.");
        medicamentoRepository.deleteById(id);
    }

    private MedicamentoResponseDTO toResponseDTO(Medicamento medicamento) {
        ConsultaResponseDTO consultaDTO = consultaService.toResponseDTO(medicamento.getConsulta());
        return new MedicamentoResponseDTO(medicamento.getId(), medicamento.getNome(), medicamento.getDosagem(), medicamento.getFrequencia(), medicamento.getDataInicio(), medicamento.getDuracaoDias(), medicamento.getStatus(), consultaDTO);
    }
}