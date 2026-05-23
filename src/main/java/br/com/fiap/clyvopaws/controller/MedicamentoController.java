package br.com.fiap.clyvopaws.controller;

import br.com.fiap.clyvopaws.dto.HistoricoDoseRequestDTO;
import br.com.fiap.clyvopaws.dto.HistoricoDoseResponseDTO;
import br.com.fiap.clyvopaws.dto.MedicamentoRequestDTO;
import br.com.fiap.clyvopaws.dto.MedicamentoResponseDTO;
import br.com.fiap.clyvopaws.service.MedicamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/medicamentos")
@RequiredArgsConstructor
public class MedicamentoController {

    private final MedicamentoService medicamentoService;

    @PostMapping
    public ResponseEntity<          MedicamentoResponseDTO> cadastrar(@Valid @RequestBody MedicamentoRequestDTO request) {
        MedicamentoResponseDTO response = medicamentoService.cadastrar(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/consulta/{consultaId}")
    public ResponseEntity<List<MedicamentoResponseDTO>> listarPorConsulta(@PathVariable Long consultaId) {
        return ResponseEntity.ok(medicamentoService.listarPorConsulta(consultaId));
    }

    @PostMapping("/doses/check")
    public ResponseEntity<HistoricoDoseResponseDTO> registrarDose(@Valid @RequestBody HistoricoDoseRequestDTO request) {
        HistoricoDoseResponseDTO response = medicamentoService.registrarDose(request);
        return ResponseEntity.status(201).body(response);
    }
}
