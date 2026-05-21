package br.com.fiap.petjourney.services;

import br.com.fiap.petjourney.dtos.request.ClinicRequest;
import br.com.fiap.petjourney.dtos.response.ClinicResponse;
import br.com.fiap.petjourney.exceptions.ResourceNotFoundException;
import br.com.fiap.petjourney.models.Clinic;
import br.com.fiap.petjourney.repositories.ClinicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClinicService {

    private final ClinicRepository repository;

    public Page<ClinicResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(ClinicResponse::fromEntity);
    }

    public ClinicResponse findById(Long id) {
        return repository.findById(id)
                .map(ClinicResponse::fromEntity)
                .orElseThrow(() -> new ResourceNotFoundException("Clínica não encontrada"));
    }

    public ClinicResponse create(ClinicRequest request) {
        return ClinicResponse.fromEntity(repository.save(new Clinic(request)));
    }

    public ClinicResponse update(Long id, ClinicRequest request) {
        Clinic clinic = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Clínica não encontrada"));

        clinic.updateFrom(request);

        return ClinicResponse.fromEntity(repository.save(clinic));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Clínica não encontrada");
        }

        repository.deleteById(id);
    }
}