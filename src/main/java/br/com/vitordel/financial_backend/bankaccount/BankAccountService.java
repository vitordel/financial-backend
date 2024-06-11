package br.com.vitordel.financial_backend.bankaccount;

import br.com.vitordel.financial_backend.transaction.TransactionService;
import br.com.vitordel.financial_backend.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.persistence.EntityNotFoundException;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private BankAccountMapper bankAccountMapper;

    @Autowired
    private TransactionService transactionService;

    public String save(BankAccountRequest request, Authentication connectedUser) {
        User user = ((User) connectedUser.getPrincipal());
        BankAccount bankAccount = bankAccountMapper.toBankAccount(request);
        bankAccount.setUser(user);
        return "Bank Account created: " + bankAccountRepository.save(bankAccount).getBankName();
    }

    public List<BankAccountResponse> findAllBankAccountsByUser(Authentication connectedUser) {

        User user = ((User) connectedUser.getPrincipal());
        List<BankAccount> bankAccounts = bankAccountRepository.findByUser(user);


        return bankAccounts.stream()
                .map(bankAccount -> {
                    BankAccountResponse response = bankAccountMapper.toBankAccountResponse(bankAccount);
                    BigDecimal totalAmount = transactionService.getTotalAmountByBankAccountId(bankAccount.getId());
                    response.setTotalAmount(totalAmount);
                    return response;
                })
                .collect(Collectors.toList());
    }

    public BankAccount updateBankAccount(Long id, BankAccount bankAccount) {
        BankAccount existingBankAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bank Account not found with id: " + id));

        existingBankAccount.setBankName(bankAccount.getBankName());

        return bankAccountRepository.save(existingBankAccount);
    }

    public void deleteBankAccount(Long id) {
        BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TransactionType not found with id: " + id));

        bankAccountRepository.delete(bankAccount);
    }
}