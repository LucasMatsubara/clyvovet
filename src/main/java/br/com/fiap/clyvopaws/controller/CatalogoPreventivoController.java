package br.com.fiap.clyvopaws.controller;

import br.com.fiap.clyvopaws.dto.*;
import br.com.fiap.clyvopaws.service.CatalogoPreventivoService;

import io.swagger.v3.oas.annotations.Parameter;
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
import java.util.List;

@RestController
@RequestMapping("/planos-preventivos")
@RequiredArgsConstructor
@Tag(name = "Catálogo Preventivo", description = "Diretrizes médicas de predição baseadas em espécie")
public class CatalogoPreventivoController {
    private final CatalogoPreventivoService catalogoPreventivoService;

    @GetMapping
    public ResponseEntity<Page<CatalogoPreventivoResponseDTO>> listarTodos(
            @ParameterObject @PageableDefault(size = 10, sort = "especie") Pageable pageable) {
        return ResponseEntity.ok(catalogoPreventivoService.listarTodos(pageable));
    }

    @GetMapping("/busca")
    public ResponseEntity<List<CatalogoPreventivoResponseDTO>> buscarPlanoPreventivo(
            @Parameter(description = "Digite a espécie (ex: CACHORRO, GATO)")
            @RequestParam("especie") String especie) {
        return ResponseEntity.ok(catalogoPreventivoService.buscarPlanoPreventivo(especie));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatalogoPreventivoResponseDTO> buscarPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(catalogoPreventivoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<CatalogoPreventivoResponseDTO> cadastrar(@Valid @RequestBody CatalogoPreventivoRequestDTO request) {
        CatalogoPreventivoResponseDTO response = catalogoPreventivoService.cadastrar(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CatalogoPreventivoResponseDTO> atualizar(@PathVariable("id") Long id, @Valid @RequestBody CatalogoPreventivoRequestDTO request) {
        return ResponseEntity.ok(catalogoPreventivoService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        catalogoPreventivoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}