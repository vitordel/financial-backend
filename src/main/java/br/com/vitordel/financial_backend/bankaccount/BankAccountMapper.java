package br.com.vitordel.financial_backend.bankaccount;

import org.springframework.stereotype.Service;

@Service
public class BankAccountMapper {
    public BankAccount toBankAccount(BankAccountRequest request) {
        return BankAccount.builder()
                .id(request.id())
                .bankName(request.bankName())
                .build();
    }

    public BankAccountResponse toBankAccountResponse(BankAccount bankAccount) {
        return BankAccountResponse.builder()
                .id(bankAccount.getId())
                .bankName(bankAccount.getBankName())
                .build();
    }
}
