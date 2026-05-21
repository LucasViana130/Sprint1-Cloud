package br.com.fiap.petjourney.services;

import br.com.fiap.petjourney.dtos.response.TutorResponse;
import br.com.fiap.petjourney.exceptions.ResourceNotFoundException;
import br.com.fiap.petjourney.repositories.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TutorService {

    private final TutorRepository repository;

    public Page<TutorResponse> findAll(String name, Pageable pageable) {
        if (name != null && !name.isBlank()) {
            return repository.findByNameContainingIgnoreCase(name, pageable).map(TutorResponse::fromEntity);
        }

        return repository.findAll(pageable).map(TutorResponse::fromEntity);
    }

    public TutorResponse findById(Long id) {
        return repository.findById(id)
                .map(TutorResponse::fromEntity)
                .orElseThrow(() -> new ResourceNotFoundException("Tutor não encontrado"));
    }
}