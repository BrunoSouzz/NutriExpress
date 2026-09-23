package com.nutriexpress.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClienteRequestDTO(
        @NotBlank(message = "O nome é obrigatório")
        @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
        String nome,

        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "E-mail inválido")
        String email,

        @Pattern(regexp = "\\d{10,11}", message = "O telefone deve conter apenas números (10 ou 11 dígitos)")
        String telefone,

        @NotBlank(message = "O endereço é obrigatório")
        String endereco
) {}