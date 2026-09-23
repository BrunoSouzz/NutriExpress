package com.nutriexpress.backend.dto;

import com.nutriexpress.backend.model.Pedido;

import java.util.List;

public record PedidoResponseDTO(
        Long id,
        ClienteResponseDTO cliente,
        List<PratoResponseDTO> pratos,
        String status,
        Double valorTotal
) {
    public static PedidoResponseDTO fromEntity(Pedido pedido) {
        return new PedidoResponseDTO(
                pedido.getId(),
                ClienteResponseDTO.fromEntity(pedido.getCliente()),
                pedido.getPratos().stream().map(PratoResponseDTO::fromEntity).toList(),
                pedido.getStatus(),
                pedido.getValorTotal()
        );
    }
}