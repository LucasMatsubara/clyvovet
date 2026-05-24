package br.com.fiap.clyvopaws.controller;

import br.com.fiap.clyvopaws.dto.*;
import br.com.fiap.clyvopaws.service.ConsultaService;

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
@RequestMapping("/consultas")
@RequiredArgsConstructor
@Tag(name = "Consultas", description = "Endpoints de consultas médicas e histórico veterinário")
public class ConsultaController {
    private final ConsultaService consultaService;

    @PostMapping
    public ResponseEntity<ConsultaResponseDTO> cadastrar(@Valid @RequestBody ConsultaRequestDTO request) {
        ConsultaResponseDTO response = consultaService.cadastrar(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaResponseDTO> buscarPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(consultaService.buscarPorId(id));
    }

    @GetMapping("/pet/{petId}")
    public ResponseEntity<Page<ConsultaResponseDTO>> listarHistorico(
            @PathVariable("petId") Long petId,
            @ParameterObject @PageableDefault(size = 5, sort = "dataConsulta") Pageable pageable) {
        return ResponseEntity.ok(consultaService.listarHistoricoPorPet(petId, pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultaResponseDTO> atualizar(@PathVariable("id") Long id, @Valid @RequestBody ConsultaRequestDTO request) {
        return ResponseEntity.ok(consultaService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        consultaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}