package br.com.fiap.petjourney.dtos.request;

import br.com.fiap.petjourney.models.enums.AppointmentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record AppointmentRequest(
    @NotBlank(message = "O título é obrigatório")
    String title,

    String description,

    @NotNull(message = "A data e hora são obrigatórias")
    LocalDateTime dateTime,

    AppointmentStatus status,

    @NotNull(message = "O ID do pet é obrigatório")
    Long petId,

    @NotNull(message = "O ID do veterinário é obrigatório")
    Long veterinarianId,

    Long clinicId
) {}
