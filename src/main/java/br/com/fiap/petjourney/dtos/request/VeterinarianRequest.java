package br.com.fiap.petjourney.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VeterinarianRequest(
    @NotBlank(message = "O nome é obrigatório")
    String name,

    @NotBlank(message = "O CRMV é obrigatório")
    String crmv,

    String phone,

    @Email
    String email,
    String specialty,

    @NotNull(message = "O ID da clínica é obrigatório")
    Long clinicId
) {}
