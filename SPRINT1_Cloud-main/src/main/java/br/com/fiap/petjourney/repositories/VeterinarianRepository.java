package br.com.fiap.petjourney.repositories;

import br.com.fiap.petjourney.models.Veterinarian;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeterinarianRepository extends JpaRepository<Veterinarian, Long> {
    Page<Veterinarian> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
