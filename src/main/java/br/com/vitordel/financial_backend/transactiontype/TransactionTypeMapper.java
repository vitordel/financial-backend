package br.com.vitordel.financial_backend.transactiontype;

import org.springframework.stereotype.Service;

@Service
public class TransactionTypeMapper {
    public TransactionType toTransactionType(TransactionTypeRequest request) {
        return TransactionType.builder()
                .id(request.id())
                .name(request.name())
                .build();
    }

    public TransactionTypeResponse toTransactionTypeResponse(TransactionType transactionType) {
        return TransactionTypeResponse.builder()
                .id(transactionType.getId())
                .name(transactionType.getName())
                .build();
    }
}
