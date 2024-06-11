package br.com.vitordel.financial_backend.transaction;

import br.com.vitordel.financial_backend.transactiontype.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByBankAccountId(Long bankAccountId);
    List<Transaction> findByTransactionType(TransactionType transactionType);

    List<Transaction> findByBankAccountIdIn(List<Long> bankAccountIds);
}
