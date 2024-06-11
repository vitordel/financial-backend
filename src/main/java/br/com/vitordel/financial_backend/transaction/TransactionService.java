package br.com.vitordel.financial_backend.transaction;

import br.com.vitordel.financial_backend.bankaccount.BankAccount;
import br.com.vitordel.financial_backend.bankaccount.BankAccountRepository;
import br.com.vitordel.financial_backend.transactiontype.TransactionType;
import br.com.vitordel.financial_backend.transactiontype.TransactionTypeRepository;
import br.com.vitordel.financial_backend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private TransactionTypeRepository transactionTypeRepository;

    @Autowired
    private TransactionMapper transactionMapper;

    public String createTransactionByBankAccount(Long bankAccountId, TransactionRequest request) {

        BankAccount bankAccount = bankAccountRepository.findById(bankAccountId)
                .orElseThrow(() -> new IllegalArgumentException("Bank account not found with id: " + bankAccountId));

        TransactionType transactionType = transactionTypeRepository.findById(request.type())
                .orElseThrow(() -> new IllegalArgumentException("Transaction type not found with id: " + request.type()));

        Transaction transaction = transactionMapper.toTransaction(request);

        transaction.setBankAccount(bankAccount);
        transaction.setTransactionType(transactionType);

//         Set created and last modified dates
//        transaction.setCreatedDate(LocalDateTime.now());
//        transaction.setLastModifiedDate(LocalDateTime.now());

        transactionRepository.save(transaction);

        return "Transaction created successfully.";
    }

    public List<Transaction> findAllUserTransactions(Authentication connectedUser) {

        User user = ((User) connectedUser.getPrincipal());

        List<BankAccount> bankAccounts = bankAccountRepository.findByUser(user);
        List<Long> bankAccountIds = bankAccounts.stream().map(BankAccount::getId).toList();

        return transactionRepository.findByBankAccountIdIn(bankAccountIds);
    }

    public List<Transaction> findAllTransactionsByBankAccountId(Long bankAccountId) {
        BankAccount bankAccount = bankAccountRepository.findById(bankAccountId)
                .orElseThrow(() -> new IllegalArgumentException("Bank account not found with id: " + bankAccountId));
        return transactionRepository.findByBankAccountId(bankAccountId);
    }

    public List<Transaction> getTransactionsByTransactionTypeId(Long transactionTypeId) {
        TransactionType transactionType = transactionTypeRepository.findById(transactionTypeId)
                .orElseThrow(() -> new IllegalArgumentException("Transaction type not found with id: " + transactionTypeId));
        return transactionRepository.findByTransactionType(transactionType);
    }

    public Optional<Transaction> getTransactionById(Long transactionId) {
        return transactionRepository.findById(transactionId);
    }

    public Transaction updateTransaction(Long transactionId, TransactionUpdateRequest request) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found with id: " + transactionId));

        transaction.setAmount(request.amount());
        transaction.setDescription(request.description());
        transaction.setDate(request.date());

        TransactionType transactionType = transactionTypeRepository.findById(request.type().getId())
                .orElseThrow(() -> new IllegalArgumentException("Transaction type not found with id: " + request.type().getId()));
        transaction.setTransactionType(transactionType);

        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(Long transactionId) {
        if (!transactionRepository.existsById(transactionId)) {
            throw new IllegalArgumentException("Transaction not found with id: " + transactionId);
        }
        transactionRepository.deleteById(transactionId);
    }

    public BigDecimal getTotalAmountByBankAccountId(Long bankAccountId) {
        List<Transaction> transactions = transactionRepository.findByBankAccountId(bankAccountId);

        BigDecimal totalAmount = BigDecimal.ZERO;
        for (Transaction transaction : transactions) {
            totalAmount = totalAmount.add(transaction.getAmount());
        }

        return totalAmount;
    }
}
