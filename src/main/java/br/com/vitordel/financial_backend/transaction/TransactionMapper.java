package br.com.vitordel.financial_backend.transaction;

import org.springframework.stereotype.Service;

@Service
public class TransactionMapper {
    public Transaction toTransaction(TransactionRequest request) {
        return Transaction.builder()
                .id(request.id())
                .description(request.description())
                .amount(request.amount())
                .date(request.date())
                .build();
    }

    public TransactionResponse toTransactionResponse(Transaction transaction) {
        return TransactionResponse.builder()
                .id(transaction.getId())
                .bankAccountName(transaction.getBankAccount().getBankName())
                .description(transaction.getDescription())
                .amount(transaction.getAmount())
                .type(transaction.getTransactionType().getName())
                .date(transaction.getDate())
                .build();
    }
}