package br.com.fiap.clyvovet.repository;

import br.com.fiap.clyvovet.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    List<Consulta> findByPetIdOrderByDataHoraDesc(Long petId);
}