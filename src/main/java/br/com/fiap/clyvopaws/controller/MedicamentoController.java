package br.com.fiap.clyvopaws.controller;

import br.com.fiap.clyvopaws.dto.*;
import br.com.fiap.clyvopaws.service.MedicamentoService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/medicamentos")
@RequiredArgsConstructor
@Tag(name = "Medicamentos", description = "Endpoints de tratamentos e registro de doses (checks)")
public class MedicamentoController {
    private final MedicamentoService medicamentoService;

    @PostMapping
    public ResponseEntity<MedicamentoResponseDTO> cadastrar(@Valid @RequestBody MedicamentoRequestDTO request) {
        MedicamentoResponseDTO response = medicamentoService.cadastrar(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicamentoResponseDTO> buscarPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(medicamentoService.buscarPorId(id));
    }

    @GetMapping("/consulta/{consultaId}")
    public ResponseEntity<Page<MedicamentoResponseDTO>> listarPorConsulta(
            @PathVariable("consultaId") Long consultaId,
            @ParameterObject @PageableDefault(size = 10, sort = "dataInicio") Pageable pageable) {
        return ResponseEntity.ok(medicamentoService.listarPorConsulta(consultaId, pageable));
    }

    @PostMapping("/doses/check")
    public ResponseEntity<HistoricoDoseResponseDTO> registrarDose(@Valid @RequestBody HistoricoDoseRequestDTO request) {
        HistoricoDoseResponseDTO response = medicamentoService.registrarDose(request);
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicamentoResponseDTO> atualizar(@PathVariable("id") Long id, @Valid @RequestBody MedicamentoRequestDTO request) {
        return ResponseEntity.ok(medicamentoService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        medicamentoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}