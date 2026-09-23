package com.nutriexpress.backend.exception;

public class PratoNaoEncontradoException extends RuntimeException {
    public PratoNaoEncontradoException(Long id) {
        super("Prato não encontrado com o ID: " + id);
    }
}