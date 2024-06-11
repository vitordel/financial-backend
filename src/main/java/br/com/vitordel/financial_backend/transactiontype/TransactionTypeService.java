package br.com.vitordel.financial_backend.transactiontype;

import br.com.vitordel.financial_backend.user.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionTypeService {

    @Autowired
    private TransactionTypeRepository repository;

    @Autowired
    private TransactionTypeMapper transactionTypeMapper;

    public String save(TransactionTypeRequest request, Authentication connectedUser) {
        User user = ((User) connectedUser.getPrincipal());
        TransactionType transactionType = transactionTypeMapper.toTransactionType(request);
        transactionType.setUser(user);
        return "Transaction Type created: " + repository.save(transactionType).getName();
    }

    public List<TransactionTypeResponse> findAllTransactionTypesByUser(Authentication connectedUser) {

        User user = ((User) connectedUser.getPrincipal());
        List<TransactionType> transactionTypes = repository.findByUser(user);

        return transactionTypes.stream()
                .map(transactionTypeMapper::toTransactionTypeResponse)
                .collect(Collectors.toList());
    }

    public TransactionType updateTransactionType(Long id, TransactionType transactionType) {
        TransactionType existingTransactionType = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TransactionType not found with id: " + id));

        existingTransactionType.setName(transactionType.getName());

        return repository.save(existingTransactionType);
    }

    public void deleteTransactionType(Long id) {
        TransactionType transactionType = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TransactionType not found with id: " + id));

        repository.delete(transactionType);
    }
}