package com.nutriexpress.backend.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record PratoRequestDTO(
        @NotBlank(message = "O nome é obrigatório")
        String nome,

        String descricao,

        @NotNull(message = "O valor é obrigatório")
        @Positive(message = "O valor deve ser positivo")
        BigDecimal valor,

        @NotBlank(message = "A categoria é obrigatória")
        String categoria, // ex: vegano, low carb, fitness, sobremesa saudável

        @Positive(message = "As calorias devem ser um número positivo")
        Integer calorias,

        @Positive(message = "A quantidade deve ser positiva")
        Double quantidade,

        @NotBlank(message = "A unidade de medida é obrigatória")
        String unidadeMedida // "g" ou "ml"
) {}