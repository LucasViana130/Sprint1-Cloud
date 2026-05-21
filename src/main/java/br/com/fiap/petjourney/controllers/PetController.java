package br.com.fiap.petjourney.controllers;

import br.com.fiap.petjourney.dtos.response.PetResponse;
import br.com.fiap.petjourney.services.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/pets")
@RequiredArgsConstructor
@Tag(name = "Pet", description = "Endpoints para consulta de pets")
public class PetController {

    private final PetService service;

    @GetMapping
    @Operation(summary = "Listar todos os pets com busca por nome, paginação e cache")
    public ResponseEntity<Page<PetResponse>> getAll(
            @RequestParam(required = false) String name,
            @PageableDefault(size = 10) @ParameterObject Pageable pageable) {
        Page<PetResponse> pets = service.findAll(name, pageable);
        pets.forEach(pet -> pet.add(linkTo(methodOn(PetController.class).getById(pet.getId())).withSelfRel()));
        return ResponseEntity.ok(pets);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar pet por ID")
    public ResponseEntity<PetResponse> getById(@PathVariable Long id) {
        PetResponse pet = service.findById(id);
        pet.add(linkTo(methodOn(PetController.class).getById(id)).withSelfRel());
        pet.add(linkTo(methodOn(PetController.class).getAll(null, Pageable.unpaged())).withRel("all-pets"));
        return ResponseEntity.ok(pet);
    }
}
