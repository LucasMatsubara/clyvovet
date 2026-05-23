package br.com.fiap.clyvopaws.controller;

import br.com.fiap.clyvopaws.dto.AgendamentoRequestDTO;
import br.com.fiap.clyvopaws.dto.AgendamentoResponseDTO;
import br.com.fiap.clyvopaws.service.AgendamentoService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/agendamentos")
@RequiredArgsConstructor
@Tag(name = "Agendamentos", description = "Endpoints de retornos e lembretes de vacinas/avaliações")
public class AgendamentoController {
    private final AgendamentoService agendamentoService;

    @PostMapping
    public ResponseEntity<AgendamentoResponseDTO> cadastrar(@Valid @RequestBody AgendamentoRequestDTO request) {
        AgendamentoResponseDTO response = agendamentoService.cadastrar(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoResponseDTO> buscarPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(agendamentoService.buscarPorId(id));
    }

    @GetMapping("/consulta/{consultaId}")
    public ResponseEntity<List<AgendamentoResponseDTO>> listarPorConsulta(@PathVariable("consultaId") Long consultaId) {
        return ResponseEntity.ok(agendamentoService.listarPorConsulta(consultaId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendamentoResponseDTO> atualizar(@PathVariable("id") Long id, @Valid @RequestBody AgendamentoRequestDTO request) {
        return ResponseEntity.ok(agendamentoService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        agendamentoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}