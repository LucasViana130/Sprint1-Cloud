package br.com.fiap.petjourney.dtos.request;

import br.com.fiap.petjourney.models.enums.MedicationStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record MedicationRequest(
    @NotBlank(message = "O nome do medicamento é obrigatório")
    String name,

    String dosage,
    String frequency,
    LocalDate startDate,
    LocalDate endDate,
    String observations,
    MedicationStatus status,

    @NotNull(message = "O ID do pet é obrigatório")
    Long petId,

    @NotNull(message = "O ID do veterinário é obrigatório")
    Long veterinarianId
) {}
