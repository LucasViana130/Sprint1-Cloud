package br.com.fiap.petjourney.repositories;

import br.com.fiap.petjourney.models.Tutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long> {
    Page<Tutor> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
