package br.com.fiap.clyvopaws.repository;

import br.com.fiap.clyvopaws.enums.Especie;
import br.com.fiap.clyvopaws.model.CatalogoPreventivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogoPreventivoRepository extends JpaRepository<CatalogoPreventivo, Long> {
    List<CatalogoPreventivo> findByEspecie(Especie especie);
}
