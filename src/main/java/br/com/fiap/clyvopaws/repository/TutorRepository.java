package br.com.fiap.clyvopaws.repository;

import br.com.fiap.clyvopaws.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long> {
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
}