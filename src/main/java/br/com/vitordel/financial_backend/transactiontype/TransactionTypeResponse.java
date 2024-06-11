package br.com.vitordel.financial_backend.transactiontype;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionTypeResponse {
    private Long id;
    private String name;
}
