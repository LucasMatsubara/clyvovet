package br.com.fiap.clyvopaws.controller;

import br.com.fiap.clyvopaws.dto.CatalogoPreventivoResponseDTO;
import br.com.fiap.clyvopaws.enums.Especie;
import br.com.fiap.clyvopaws.service.CatalogoPreventivoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/planos-preventivos")
@RequiredArgsConstructor
public class CatalogoPreventivoController {

    private final CatalogoPreventivoService catalogoPreventivoService;

    @GetMapping
    public ResponseEntity<List<CatalogoPreventivoResponseDTO>> buscarPlanoPreventivo(
            @RequestParam Especie especie,
            @RequestParam String raca) {

        List<CatalogoPreventivoResponseDTO> plano = catalogoPreventivoService.buscarPlanoPreventivo(especie, raca);
        return ResponseEntity.ok(plano);
    }
}
