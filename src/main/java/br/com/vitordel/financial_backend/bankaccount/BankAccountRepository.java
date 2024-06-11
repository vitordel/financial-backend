package br.com.vitordel.financial_backend.bankaccount;

import br.com.vitordel.financial_backend.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    List<BankAccount> findByUser(User user);
}
