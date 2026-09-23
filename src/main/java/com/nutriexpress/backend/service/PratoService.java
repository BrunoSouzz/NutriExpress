package com.nutriexpress.backend.service;

import com.nutriexpress.backend.dto.PratoRequestDTO;
import com.nutriexpress.backend.dto.PratoResponseDTO;
import com.nutriexpress.backend.exception.PratoNaoEncontradoException;
import com.nutriexpress.backend.model.Prato;
import com.nutriexpress.backend.repository.PratoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PratoService {

    private final PratoRepository repository;

    public PratoService(PratoRepository repository) {
        this.repository = repository;
    }

    public PratoResponseDTO criar(PratoRequestDTO dto) {
        // REGRA DE NEGÓCIO PRÓPRIA:
        // Não permite cadastrar dois pratos com o mesmo nome (ignorando maiúsculas/minúsculas).
        if (repository.existsByNomeIgnoreCase(dto.nome())) {
            throw new IllegalArgumentException("Já existe um prato cadastrado com este nome: " + dto.nome());
        }

        Prato prato = toEntity(dto);
        return toDTO(repository.save(prato));
    }

    public List<PratoResponseDTO> listarTodos() {
        return repository.findAll().stream().map(this::toDTO).toList();
    }

    public List<PratoResponseDTO> listarPorCategoria(String categoria) {
        return repository.findByCategoria(categoria).stream().map(this::toDTO).toList();
    }

    public PratoResponseDTO buscarPorId(Long id) {
        Prato prato = repository.findById(id)
                .orElseThrow(() -> new PratoNaoEncontradoException(id));
        return toDTO(prato);
    }

    public PratoResponseDTO atualizar(Long id, PratoRequestDTO dto) {
        Prato prato = repository.findById(id)
                .orElseThrow(() -> new PratoNaoEncontradoException(id));

        prato.setNome(dto.nome());
        prato.setDescricao(dto.descricao());
        prato.setValor(dto.valor());
        prato.setCategoria(dto.categoria());
        prato.setCalorias(dto.calorias());
        prato.setQuantidade(dto.quantidade());
        prato.setUnidadeMedida(dto.unidadeMedida());

        return toDTO(repository.save(prato));
    }

    public void remover(Long id) {
        if (!repository.existsById(id)) {
            throw new PratoNaoEncontradoException(id);
        }
        repository.deleteById(id);
    }

    // Métodos privados de conversão exigidos pela atividade
    private Prato toEntity(PratoRequestDTO dto) {
        Prato prato = new Prato();
        prato.setNome(dto.nome());
        prato.setDescricao(dto.descricao());
        prato.setValor(dto.valor());
        prato.setCategoria(dto.categoria());
        prato.setCalorias(dto.calorias());
        prato.setQuantidade(dto.quantidade());
        prato.setUnidadeMedida(dto.unidadeMedida());
        return prato;
    }

    private PratoResponseDTO toDTO(Prato prato) {
        return new PratoResponseDTO(
                prato.getId(),
                prato.getNome(),
                prato.getDescricao(),
                prato.getValor(),
                prato.getCategoria(),
                prato.getCalorias(),
                prato.getQuantidade(),
                prato.getUnidadeMedida()
        );
    }
}