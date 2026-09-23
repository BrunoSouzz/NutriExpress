package com.nutriexpress.backend.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record PedidoRequestDTO(
        @NotNull(message = "O ID do cliente é obrigatório")
        Long clienteId,

        @NotEmpty(message = "O pedido deve conter pelo menos um prato")
        List<Long> pratosIds
) {}