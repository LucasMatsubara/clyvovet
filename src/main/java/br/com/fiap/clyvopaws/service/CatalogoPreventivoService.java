package br.com.fiap.clyvopaws.service;

import br.com.fiap.clyvopaws.dto.*;
import br.com.fiap.clyvopaws.enums.Especie;
import br.com.fiap.clyvopaws.model.CatalogoPreventivo;
import br.com.fiap.clyvopaws.repository.CatalogoPreventivoRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CatalogoPreventivoService {
    private final CatalogoPreventivoRepository catalogoPreventivoRepository;

    @Transactional(readOnly = true)
    public Page<CatalogoPreventivoResponseDTO> listarTodos(Pageable pageable) {
        return catalogoPreventivoRepository.findAll(pageable).map(this::toResponseDTO);
    }

    @Cacheable("planosPreventivos")
    @Transactional(readOnly = true)
    public List<CatalogoPreventivoResponseDTO> buscarPlanoPreventivo(String especieRecebida) {
        try {
            Especie especieEnum = Especie.valueOf(especieRecebida.toUpperCase());

            return catalogoPreventivoRepository.findByEspecie(especieEnum).stream()
                    .map(this::toResponseDTO)
                    .collect(Collectors.toList());

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Espécie inválida. Utilize CACHORRO ou GATO.");
        }
    }

    @Transactional(readOnly = true)
    public CatalogoPreventivoResponseDTO buscarPorId(Long id) {
        CatalogoPreventivo cat = catalogoPreventivoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Diretriz não encontrada."));
        return toResponseDTO(cat);
    }

    @CacheEvict(value = "planosPreventivos", allEntries = true)
    @Transactional
    public CatalogoPreventivoResponseDTO cadastrar(CatalogoPreventivoRequestDTO request) {
        CatalogoPreventivo cat = new CatalogoPreventivo();
        cat.setEspecie(request.especie());
        cat.setRaca(request.raca());
        cat.setDoencaPredisposta(request.doencaPredisposta());
        cat.setIdadeAlertaMeses(request.idadeAlertaMeses());
        cat.setDicaPrevencao(request.dicaPrevencao());
        cat.setCuidadosRecomendados(request.cuidadosRecomendados());
        return toResponseDTO(catalogoPreventivoRepository.save(cat));
    }

    @CacheEvict(value = "planosPreventivos", allEntries = true)
    @Transactional
    public CatalogoPreventivoResponseDTO atualizar(Long id, CatalogoPreventivoRequestDTO request) {
        CatalogoPreventivo cat = catalogoPreventivoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Diretriz não encontrada."));
        cat.setDoencaPredisposta(request.doencaPredisposta());
        cat.setIdadeAlertaMeses(request.idadeAlertaMeses());
        cat.setDicaPrevencao(request.dicaPrevencao());
        cat.setCuidadosRecomendados(request.cuidadosRecomendados());
        return toResponseDTO(catalogoPreventivoRepository.save(cat));
    }

    @CacheEvict(value = "planosPreventivos", allEntries = true)
    @Transactional
    public void excluir(Long id) {
        if (!catalogoPreventivoRepository.existsById(id)) {
            throw new EntityNotFoundException("Diretriz não encontrada.");
        }
        catalogoPreventivoRepository.deleteById(id);
    }

    private CatalogoPreventivoResponseDTO toResponseDTO(CatalogoPreventivo catalogo) {
        return new CatalogoPreventivoResponseDTO(
                catalogo.getId(),
                catalogo.getEspecie(),
                catalogo.getRaca(),
                catalogo.getDoencaPredisposta(),
                catalogo.getIdadeAlertaMeses(),
                catalogo.getDicaPrevencao(),
                catalogo.getCuidadosRecomendados()
        );
    }
}