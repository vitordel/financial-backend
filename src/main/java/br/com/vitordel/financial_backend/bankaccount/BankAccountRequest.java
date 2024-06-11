package br.com.vitordel.financial_backend.bankaccount;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record BankAccountRequest(
        Long id,

        @NotNull(message = "100")
        @NotEmpty(message = "100")
        String bankName
) {
}