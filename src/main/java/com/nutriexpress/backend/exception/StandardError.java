package com.nutriexpress.backend.exception;

import java.time.Instant;
import java.util.Map;

public record StandardError(
        Instant timestamp,
        Integer status,
        String error,
        String message,
        String path,
        Map<String, String> validationErrors
) {
    // Construtor utilitário para erros simples
    public static StandardError simple(Integer status, String error, String message, String path) {
        return new StandardError(Instant.now(), status, error, message, path, null);
    }

    // Construtor utilitário para erros de validação (@Valid)
    public static StandardError validation(Integer status, String error, String message, String path, Map<String, String> validationErrors) {
        return new StandardError(Instant.now(), status, error, message, path, validationErrors);
    }
}