package devis.springboot.app.services;

import devis.springboot.app.entity.Transaction;
import devis.springboot.app.exceptions.EntityNotFoundException;
import devis.springboot.app.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction saveTransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }

    public Transaction getTransaction(Long id) throws EntityNotFoundException {
        Optional<Transaction> transaction=transactionRepository.findById(id);
        if (transaction.isEmpty()) throw new EntityNotFoundException("Transaction doesn't exist");
        return transaction.get();
    }
}
