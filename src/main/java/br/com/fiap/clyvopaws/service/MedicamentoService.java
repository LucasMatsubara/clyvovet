package br.com.fiap.clyvopaws.service;

import br.com.fiap.clyvopaws.dto.HistoricoDoseRequestDTO;
import br.com.fiap.clyvopaws.dto.HistoricoDoseResponseDTO;
import br.com.fiap.clyvopaws.dto.MedicamentoRequestDTO;
import br.com.fiap.clyvopaws.dto.MedicamentoResponseDTO;
import br.com.fiap.clyvopaws.model.Consulta;
import br.com.fiap.clyvopaws.model.HistoricoDose;
import br.com.fiap.clyvopaws.model.Medicamento;
import br.com.fiap.clyvopaws.repository.ConsultaRepository;
import br.com.fiap.clyvopaws.repository.HistoricoDoseRepository;
import br.com.fiap.clyvopaws.repository.MedicamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicamentoService {

    private final MedicamentoRepository medicamentoRepository;
    private final ConsultaRepository consultaRepository;
    private final HistoricoDoseRepository historicoDoseRepository;

    @Transactional
    public MedicamentoResponseDTO cadastrar(MedicamentoRequestDTO request) {
        Consulta consulta = consultaRepository.findById(request.consultaId())
                .orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada. ID: " + request.consultaId()));

        Medicamento medicamento = new Medicamento();
        medicamento.setNome(request.nome());
        medicamento.setDosagem(request.dosagem());
        medicamento.setFrequencia(request.frequencia());
        medicamento.setDataInicio(request.dataInicio());
        medicamento.setDuracaoDias(request.duracaoDias());
        medicamento.setStatus(request.status());
        medicamento.setConsulta(consulta);

        medicamento = medicamentoRepository.save(medicamento);
        return toResponseDTO(medicamento);
    }

    public List<MedicamentoResponseDTO> listarPorConsulta(Long consultaId) {
        return medicamentoRepository.findByConsultaId(consultaId).stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public HistoricoDoseResponseDTO registrarDose(HistoricoDoseRequestDTO request) {
        Medicamento medicamento = medicamentoRepository.findById(request.medicamentoId())
                .orElseThrow(() -> new EntityNotFoundException("Medicamento não encontrado."));

        HistoricoDose dose = new HistoricoDose();
        dose.setDataHoraToma(request.dataHoraToma());
        dose.setMedicamento(medicamento);

        dose = historicoDoseRepository.save(dose);
        return new HistoricoDoseResponseDTO(dose.getId(), dose.getDataHoraToma(), medicamento.getId());
    }

    private MedicamentoResponseDTO toResponseDTO(Medicamento medicamento) {
        return new MedicamentoResponseDTO(
                medicamento.getId(),
                medicamento.getNome(),
                medicamento.getDosagem(),
                medicamento.getFrequencia(),
                medicamento.getDataInicio(),
                medicamento.getDuracaoDias(),
                medicamento.getStatus(),
                medicamento.getConsulta().getId()
        );
    }
}