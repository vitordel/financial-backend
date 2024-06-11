package br.com.vitordel.financial_backend.transaction;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionResponse {
    private Long id;
    private String bankAccountName;
    private BigDecimal amount;
    private String description;
    private String type;
    private LocalDate date;
}