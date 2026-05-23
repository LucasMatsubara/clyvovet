package br.com.fiap.clyvopaws.controller;

import br.com.fiap.clyvopaws.dto.AgendamentoRequestDTO;
import br.com.fiap.clyvopaws.dto.AgendamentoResponseDTO;
import br.com.fiap.clyvopaws.service.AgendamentoService;
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
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @PostMapping
    public ResponseEntity<AgendamentoResponseDTO> cadastrar(@Valid @RequestBody AgendamentoRequestDTO request) {
        AgendamentoResponseDTO response = agendamentoService.cadastrar(request);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/consulta/{consultaId}")
    public ResponseEntity<List<AgendamentoResponseDTO>> listarPorConsulta(@PathVariable Long consultaId) {
        return ResponseEntity.ok(agendamentoService.listarPorConsulta(consultaId));
    }
}