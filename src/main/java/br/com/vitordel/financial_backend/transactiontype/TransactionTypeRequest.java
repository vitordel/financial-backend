package br.com.vitordel.financial_backend.transactiontype;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record TransactionTypeRequest(
        Long id,

        @NotNull(message = "100")
        @NotEmpty(message = "100")
        String name
) {
}
