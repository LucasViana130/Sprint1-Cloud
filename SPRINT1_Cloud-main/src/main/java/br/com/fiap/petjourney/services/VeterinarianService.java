package br.com.fiap.petjourney.services;

import br.com.fiap.petjourney.dtos.request.VeterinarianRequest;
import br.com.fiap.petjourney.dtos.response.VeterinarianResponse;
import br.com.fiap.petjourney.exceptions.ResourceNotFoundException;
import br.com.fiap.petjourney.models.Clinic;
import br.com.fiap.petjourney.models.Veterinarian;
import br.com.fiap.petjourney.repositories.ClinicRepository;
import br.com.fiap.petjourney.repositories.VeterinarianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VeterinarianService {

    private final VeterinarianRepository repository;
    private final ClinicRepository clinicRepository;

    public Page<VeterinarianResponse> findAll(String name, Pageable pageable) {
        if (name != null && !name.isBlank()) {
            return repository.findByNameContainingIgnoreCase(name, pageable).map(VeterinarianResponse::fromEntity);
        }

        return repository.findAll(pageable).map(VeterinarianResponse::fromEntity);
    }

    public VeterinarianResponse findById(Long id) {
        return repository.findById(id)
                .map(VeterinarianResponse::fromEntity)
                .orElseThrow(() -> new ResourceNotFoundException("Veterinário não encontrado"));
    }

    public VeterinarianResponse create(VeterinarianRequest request) {
        Clinic clinic = clinicRepository.findById(request.clinicId())
                .orElseThrow(() -> new ResourceNotFoundException("Clínica não encontrada"));

        return VeterinarianResponse.fromEntity(repository.save(new Veterinarian(request, clinic)));
    }

    public VeterinarianResponse update(Long id, VeterinarianRequest request) {
        Veterinarian veterinarian = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Veterinário não encontrado"));

        Clinic clinic = clinicRepository.findById(request.clinicId())
                .orElseThrow(() -> new ResourceNotFoundException("Clínica não encontrada"));

        veterinarian.updateFrom(request, clinic);

        return VeterinarianResponse.fromEntity(repository.save(veterinarian));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Veterinário não encontrado");
        }

        repository.deleteById(id);
    }
}