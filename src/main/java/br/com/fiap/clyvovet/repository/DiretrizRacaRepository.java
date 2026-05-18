package br.com.fiap.clyvovet.repository;

import br.com.fiap.clyvovet.model.DiretrizRaca;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DiretrizRacaRepository extends JpaRepository<DiretrizRaca, Long> {
    List<DiretrizRaca> findByRacaIgnoreCase(String raca);
}