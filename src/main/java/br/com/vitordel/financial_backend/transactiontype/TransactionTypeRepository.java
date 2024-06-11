package br.com.vitordel.financial_backend.transactiontype;

import br.com.vitordel.financial_backend.user.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface TransactionTypeRepository extends JpaRepository<TransactionType, Long> {
    List<TransactionType> findByUser(User user);

    TransactionType findById(long id);
}