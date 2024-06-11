package br.com.vitordel.financial_backend.transaction;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionRequest(
        Long id,

        @NotNull(message = "100")
        BigDecimal amount,

        Long type,

        String description,

        @NotNull(message = "100")
        LocalDate date
) {
}