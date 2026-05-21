package br.com.fiap.petjourney.repositories;

import br.com.fiap.petjourney.models.MedicalRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    Page<MedicalRecord> findByPetId(Long petId, Pageable pageable);
    Page<MedicalRecord> findByRegistrationDateBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);
}
