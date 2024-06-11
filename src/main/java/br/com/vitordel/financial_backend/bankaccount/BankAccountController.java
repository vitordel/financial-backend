package br.com.vitordel.financial_backend.bankaccount;

import br.com.vitordel.financial_backend.transaction.Transaction;
import br.com.vitordel.financial_backend.transaction.TransactionRequest;
import br.com.vitordel.financial_backend.transaction.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank-accounts")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<String> createBankAccount(
            @Valid @RequestBody BankAccountRequest request,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(bankAccountService.save(request, connectedUser));
    }

    @GetMapping()
    public ResponseEntity<List<BankAccountResponse>> findAllBankAccountsByUser(
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(bankAccountService.findAllBankAccountsByUser(connectedUser));
    }

    @GetMapping("/{bankAccountId}/transactions")
    public ResponseEntity<List<Transaction>> getAllTransactionsByBankAccountId(@PathVariable Long bankAccountId) {
        List<Transaction> transactions = transactionService.findAllTransactionsByBankAccountId(bankAccountId);
        return ResponseEntity.ok(transactions);
    }

    @PostMapping("/{bankAccountId}/transactions")
    public ResponseEntity<String> createTransaction(@PathVariable Long bankAccountId,
                                                    @Valid @RequestBody TransactionRequest request
    ) {
        return ResponseEntity.ok(transactionService.createTransactionByBankAccount(bankAccountId, request));
    }

    @PutMapping("/{bankAccountId}")
    public BankAccount updateBankAccount(@PathVariable Long bankAccountId, @RequestBody BankAccount bankAccount) {
        return bankAccountService.updateBankAccount(bankAccountId, bankAccount);
    }

    @DeleteMapping("/{bankAccountId}")
    public void deleteBankAccount(@PathVariable Long bankAccountId) {
        bankAccountService.deleteBankAccount(bankAccountId);
    }
}
