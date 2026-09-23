package com.nutriexpress.backend.service;

import com.nutriexpress.backend.dto.PedidoRequestDTO;
import com.nutriexpress.backend.dto.PedidoResponseDTO;
import com.nutriexpress.backend.model.Cliente;
import com.nutriexpress.backend.model.Pedido;
import com.nutriexpress.backend.model.Prato;
import com.nutriexpress.backend.repository.ClienteRepository;
import com.nutriexpress.backend.repository.PedidoRepository;
import com.nutriexpress.backend.repository.PratoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final PratoRepository pratoRepository;

    public PedidoService(PedidoRepository pedidoRepository,
                         ClienteRepository clienteRepository,
                         PratoRepository pratoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.pratoRepository = pratoRepository;
    }

    public List<PedidoResponseDTO> listarTodos() {
        return pedidoRepository.findAll()
                .stream()
                .map(PedidoResponseDTO::fromEntity)
                .toList();
    }

    public PedidoResponseDTO buscarPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com o ID: " + id));
        return PedidoResponseDTO.fromEntity(pedido);
    }

    public PedidoResponseDTO criarPedido(PedidoRequestDTO dto) {
        // 1. Validar e carregar o cliente do banco de dados
        Cliente cliente = clienteRepository.findById(dto.clienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com o ID: " + dto.clienteId()));

        // 2. Carregar os pratos do banco e calcular o valor total
        List<Prato> pratosReais = new ArrayList<>();
        double valorTotal = 0.0;

        for (Long pratoId : dto.pratosIds()) {
            Prato pratoBanco = pratoRepository.findById(pratoId)
                    .orElseThrow(() -> new RuntimeException("Prato não encontrado com ID: " + pratoId));
            pratosReais.add(pratoBanco);
            if (pratoBanco.getValor() != null) {
                valorTotal += pratoBanco.getValor().doubleValue();
            }
        }

        // 3. Instanciar e preencher a entidade Pedido
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setPratos(pratosReais);
        pedido.setValorTotal(valorTotal);
        pedido.setStatus("Pendente");

        // 4. Persistir e retornar o DTO
        Pedido pedidoSalvo = pedidoRepository.save(pedido);
        return PedidoResponseDTO.fromEntity(pedidoSalvo);
    }

    public PedidoResponseDTO atualizarStatus(Long id, String novoStatus) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com o ID: " + id));

        pedido.setStatus(novoStatus);
        Pedido atualizado = pedidoRepository.save(pedido);
        return PedidoResponseDTO.fromEntity(atualizado);
    }

    public void deletar(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new RuntimeException("Pedido não encontrado!");
        }
        pedidoRepository.deleteById(id);
    }
}