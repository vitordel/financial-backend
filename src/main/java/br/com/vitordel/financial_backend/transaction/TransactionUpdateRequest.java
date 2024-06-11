package br.com.vitordel.financial_backend.transaction;

import br.com.vitordel.financial_backend.transactiontype.TransactionType;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionUpdateRequest(

        @NotNull(message = "100")
        @NotEmpty(message = "100")
        BigDecimal amount,

        TransactionType type,

        String description,

        @NotNull
        @NotEmpty(message = "100")
        LocalDate date
) {
}