package br.com.fiap.petjourney.services;

import br.com.fiap.petjourney.dtos.request.AppointmentRequest;
import br.com.fiap.petjourney.dtos.response.AppointmentResponse;
import br.com.fiap.petjourney.exceptions.ResourceNotFoundException;
import br.com.fiap.petjourney.models.Appointment;
import br.com.fiap.petjourney.models.Clinic;
import br.com.fiap.petjourney.models.Pet;
import br.com.fiap.petjourney.models.Veterinarian;
import br.com.fiap.petjourney.repositories.AppointmentRepository;
import br.com.fiap.petjourney.repositories.ClinicRepository;
import br.com.fiap.petjourney.repositories.PetRepository;
import br.com.fiap.petjourney.repositories.VeterinarianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository repository;
    private final PetRepository petRepository;
    private final VeterinarianRepository veterinarianRepository;
    private final ClinicRepository clinicRepository;

    public Page<AppointmentResponse> findByPetId(Long petId, Pageable pageable) {
        return repository.findByPetId(petId, pageable).map(AppointmentResponse::fromEntity);
    }

    public Page<AppointmentResponse> findByDateRange(LocalDateTime start, LocalDateTime end, Pageable pageable) {
        return repository.findByDateTimeBetween(start, end, pageable).map(AppointmentResponse::fromEntity);
    }

    public AppointmentResponse findById(Long id) {
        return repository.findById(id)
                .map(AppointmentResponse::fromEntity)
                .orElseThrow(() -> new ResourceNotFoundException("Agendamento não encontrado"));
    }

    public AppointmentResponse create(AppointmentRequest request) {
        Pet pet = petRepository.findById(request.petId())
                .orElseThrow(() -> new ResourceNotFoundException("Pet não encontrado"));

        Veterinarian veterinarian = veterinarianRepository.findById(request.veterinarianId())
                .orElseThrow(() -> new ResourceNotFoundException("Veterinário não encontrado"));

        Clinic clinic = request.clinicId() != null
                ? clinicRepository.findById(request.clinicId())
                .orElseThrow(() -> new ResourceNotFoundException("Clínica não encontrada"))
                : null;

        return AppointmentResponse.fromEntity(repository.save(new Appointment(request, pet, veterinarian, clinic)));
    }

    public AppointmentResponse update(Long id, AppointmentRequest request) {
        Appointment appointment = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agendamento não encontrado"));

        Pet pet = petRepository.findById(request.petId())
                .orElseThrow(() -> new ResourceNotFoundException("Pet não encontrado"));

        Veterinarian veterinarian = veterinarianRepository.findById(request.veterinarianId())
                .orElseThrow(() -> new ResourceNotFoundException("Veterinário não encontrado"));

        Clinic clinic = request.clinicId() != null
                ? clinicRepository.findById(request.clinicId())
                .orElseThrow(() -> new ResourceNotFoundException("Clínica não encontrada"))
                : null;

        appointment.updateFrom(request, pet, veterinarian, clinic);

        return AppointmentResponse.fromEntity(repository.save(appointment));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Agendamento não encontrado");
        }

        repository.deleteById(id);
    }
}