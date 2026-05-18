package br.com.fiap.clyvovet.repository;

import br.com.fiap.clyvovet.model.Clinica;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClinicaRepository extends JpaRepository<Clinica, Long> {
    List<Clinica> findByRedeFranquiaIgnoreCase(String redeFranquia);
}