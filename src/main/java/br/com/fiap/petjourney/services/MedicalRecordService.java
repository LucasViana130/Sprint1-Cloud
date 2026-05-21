package br.com.fiap.petjourney.services;

import br.com.fiap.petjourney.dtos.request.MedicalRecordRequest;
import br.com.fiap.petjourney.dtos.response.MedicalRecordResponse;
import br.com.fiap.petjourney.exceptions.ResourceNotFoundException;
import br.com.fiap.petjourney.models.MedicalRecord;
import br.com.fiap.petjourney.models.Pet;
import br.com.fiap.petjourney.models.Veterinarian;
import br.com.fiap.petjourney.repositories.MedicalRecordRepository;
import br.com.fiap.petjourney.repositories.PetRepository;
import br.com.fiap.petjourney.repositories.VeterinarianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MedicalRecordService {

    private final MedicalRecordRepository repository;
    private final PetRepository petRepository;
    private final VeterinarianRepository veterinarianRepository;

    @Cacheable(value = "medicalRecords", key = "#petId + '-' + #pageable.pageNumber")
    public Page<MedicalRecordResponse> findByPetId(Long petId, Pageable pageable) {
        return repository.findByPetId(petId, pageable).map(MedicalRecordResponse::fromEntity);
    }

    public Page<MedicalRecordResponse> findByDateRange(LocalDateTime start, LocalDateTime end, Pageable pageable) {
        return repository.findByRegistrationDateBetween(start, end, pageable).map(MedicalRecordResponse::fromEntity);
    }

    public MedicalRecordResponse findById(Long id) {
        return repository.findById(id)
                .map(MedicalRecordResponse::fromEntity)
                .orElseThrow(() -> new ResourceNotFoundException("Prontuário não encontrado"));
    }

    @CacheEvict(value = "medicalRecords", allEntries = true)
    public MedicalRecordResponse create(MedicalRecordRequest request) {
        Pet pet = petRepository.findById(request.petId())
                .orElseThrow(() -> new ResourceNotFoundException("Pet não encontrado"));

        Veterinarian veterinarian = veterinarianRepository.findById(request.veterinarianId())
                .orElseThrow(() -> new ResourceNotFoundException("Veterinário não encontrado"));

        return MedicalRecordResponse.fromEntity(repository.save(new MedicalRecord(request, pet, veterinarian)));
    }

    @CacheEvict(value = "medicalRecords", allEntries = true)
    public MedicalRecordResponse update(Long id, MedicalRecordRequest request) {
        MedicalRecord medicalRecord = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prontuário não encontrado"));

        Pet pet = petRepository.findById(request.petId())
                .orElseThrow(() -> new ResourceNotFoundException("Pet não encontrado"));

        Veterinarian veterinarian = veterinarianRepository.findById(request.veterinarianId())
                .orElseThrow(() -> new ResourceNotFoundException("Veterinário não encontrado"));

        medicalRecord.updateFrom(request, pet, veterinarian);

        return MedicalRecordResponse.fromEntity(repository.save(medicalRecord));
    }

    @CacheEvict(value = "medicalRecords", allEntries = true)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Prontuário não encontrado");
        }

        repository.deleteById(id);
    }
}