package br.com.vitordel.financial_backend.transactiontype;

import br.com.vitordel.financial_backend.transaction.Transaction;
import br.com.vitordel.financial_backend.transaction.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction-types")
public class TransactionTypeController {

    @Autowired
    private TransactionTypeService transactionTypeService;

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<String> createTransactionType(
            @Valid @RequestBody TransactionTypeRequest request,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(transactionTypeService.save(request, connectedUser));
    }

    @GetMapping()
    public ResponseEntity<List<TransactionTypeResponse>> findAllTransactionTypesByUser(
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(transactionTypeService.findAllTransactionTypesByUser(connectedUser));
    }

    @GetMapping("/{transactionTypeId}/transactions")
    public ResponseEntity<List<Transaction>> getTransactionsByTransactionType(@PathVariable Long transactionTypeId) {
        List<Transaction> transactions = transactionService.getTransactionsByTransactionTypeId(transactionTypeId);
        return ResponseEntity.ok(transactions);
    }

    @PutMapping("/{id}")
    public TransactionType updateTransactionType(@PathVariable Long id, @RequestBody TransactionType transactionType) {
        return transactionTypeService.updateTransactionType(id, transactionType);
    }

    @DeleteMapping("/{id}")
    public void deleteTransactionType(@PathVariable Long id) {
        transactionTypeService.deleteTransactionType(id);
    }
}