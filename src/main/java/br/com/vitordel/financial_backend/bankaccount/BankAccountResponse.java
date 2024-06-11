package br.com.vitordel.financial_backend.bankaccount;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankAccountResponse {
    private Long id;
    private String bankName;
    private BigDecimal totalAmount;
}
