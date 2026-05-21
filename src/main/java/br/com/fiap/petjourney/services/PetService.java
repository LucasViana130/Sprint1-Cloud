package br.com.fiap.petjourney.services;

import br.com.fiap.petjourney.dtos.response.PetResponse;
import br.com.fiap.petjourney.exceptions.ResourceNotFoundException;
import br.com.fiap.petjourney.repositories.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository repository;

    @Cacheable(value = "pets", key = "#name + '-' + #pageable.pageNumber")
    public Page<PetResponse> findAll(String name, Pageable pageable) {
        if (name != null && !name.isBlank()) {
            return repository.findByNameContainingIgnoreCase(name, pageable).map(PetResponse::fromEntity);
        }

        return repository.findAll(pageable).map(PetResponse::fromEntity);
    }

    public PetResponse findById(Long id) {
        return repository.findById(id)
                .map(PetResponse::fromEntity)
                .orElseThrow(() -> new ResourceNotFoundException("Pet não encontrado"));
    }
}