package br.com.fiap.petjourney.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CNPJ;

public record ClinicRequest(
    @NotBlank(message = "O nome é obrigatório")
    String name,

    @NotBlank(message = "O CNPJ é obrigatório")
    @CNPJ
    String cnpj,

    String phone,
    @Email
    String email,
    String address
) {}
