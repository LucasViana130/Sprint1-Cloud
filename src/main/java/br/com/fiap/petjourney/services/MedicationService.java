package br.com.fiap.petjourney.services;

import br.com.fiap.petjourney.dtos.request.MedicationRequest;
import br.com.fiap.petjourney.dtos.response.MedicationResponse;
import br.com.fiap.petjourney.exceptions.ResourceNotFoundException;
import br.com.fiap.petjourney.models.Medication;
import br.com.fiap.petjourney.models.Pet;
import br.com.fiap.petjourney.models.Veterinarian;
import br.com.fiap.petjourney.repositories.MedicationRepository;
import br.com.fiap.petjourney.repositories.PetRepository;
import br.com.fiap.petjourney.repositories.VeterinarianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicationService {

    private final MedicationRepository repository;
    private final PetRepository petRepository;
    private final VeterinarianRepository veterinarianRepository;

    public Page<MedicationResponse> findByPetId(Long petId, Pageable pageable) {
        return repository.findByPetId(petId, pageable).map(MedicationResponse::fromEntity);
    }

    public MedicationResponse findById(Long id) {
        return repository.findById(id)
                .map(MedicationResponse::fromEntity)
                .orElseThrow(() -> new ResourceNotFoundException("Medicamento não encontrado"));
    }

    public MedicationResponse create(MedicationRequest request) {
        Pet pet = petRepository.findById(request.petId())
                .orElseThrow(() -> new ResourceNotFoundException("Pet não encontrado"));

        Veterinarian veterinarian = veterinarianRepository.findById(request.veterinarianId())
                .orElseThrow(() -> new ResourceNotFoundException("Veterinário não encontrado"));

        return MedicationResponse.fromEntity(repository.save(new Medication(request, pet, veterinarian)));
    }

    public MedicationResponse update(Long id, MedicationRequest request) {
        Medication medication = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medicamento não encontrado"));

        Pet pet = petRepository.findById(request.petId())
                .orElseThrow(() -> new ResourceNotFoundException("Pet não encontrado"));

        Veterinarian veterinarian = veterinarianRepository.findById(request.veterinarianId())
                .orElseThrow(() -> new ResourceNotFoundException("Veterinário não encontrado"));

        medication.updateFrom(request, pet, veterinarian);

        return MedicationResponse.fromEntity(repository.save(medication));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Medicamento não encontrado");
        }

        repository.deleteById(id);
    }
}