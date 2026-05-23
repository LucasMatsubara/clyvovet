package br.com.fiap.clyvopaws.controller;

import br.com.fiap.clyvopaws.dto.TutorRequestDTO;
import br.com.fiap.clyvopaws.dto.TutorResponseDTO;
import br.com.fiap.clyvopaws.service.TutorService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/tutores")
@RequiredArgsConstructor
@Tag(name = "Tutores", description = "Endpoints para gerenciamento de tutores e seus dados")
public class TutorController {
    private final TutorService tutorService;

    @PostMapping
    public ResponseEntity<TutorResponseDTO> cadastrar(@Valid @RequestBody TutorRequestDTO request) {
        TutorResponseDTO response = tutorService.cadastrar(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TutorResponseDTO> buscarPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(tutorService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TutorResponseDTO> atualizar(@PathVariable("id") Long id, @Valid @RequestBody TutorRequestDTO request) {
        return ResponseEntity.ok(tutorService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        tutorService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}