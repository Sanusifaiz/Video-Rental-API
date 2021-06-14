package com.learningjava.expensetrackerapi.services;

import com.learningjava.expensetrackerapi.entity.Transaction;
import com.learningjava.expensetrackerapi.exceptions.EtBadRequestException;
import com.learningjava.expensetrackerapi.exceptions.EtResourceNotFoundException;
import com.learningjava.expensetrackerapi.repositories.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TransactonServiceImpl implements TransactionService{

    @Autowired
    TransactionsRepository transactionsRepository;

    @Override
    public List<Transaction> fetchAllTransactions(Integer userId, Integer categoryId) {
        try {
            if((userId != null) && (categoryId !=null)) {
                var getTransactions = transactionsRepository.findAllByUserIdAndCategoryId(userId, categoryId);
                if (!getTransactions.isEmpty()){
                    return getTransactions;
                }
                return null;
            }
            throw new EtBadRequestException("userId/CategoryId cannot be null");
        }catch (Exception e){
            throw new EtBadRequestException("An Error has occurred");
        }
    }

    @Override
    public Transaction fetchTransactionById(Integer userId, Integer categoryId, Integer transactionId) throws EtResourceNotFoundException {
        try {
            var transaction = transactionsRepository.findByUserIdAndCategoryIdAndTransactionId(userId, categoryId, transactionId);
            if (transaction != null){
                return transaction;
            }
            return null;
        }catch (Exception e){
            throw new EtBadRequestException("An error has occurred");
        }
    }

    @Override
    public String addTransaction(Transaction transaction) throws EtBadRequestException {
        try {
            if (transaction != null){
                var checkTransaction = transactionsRepository.findOneByUserIdAndCategoryIdAndNoteAndAmountAndTransactionDate(
                        transaction.getUserId(),transaction.getCategoryId(),transaction.getNote(),
                        transaction.getAmount(),transaction.getTransactionDate());
                if (checkTransaction == null){
                    transactionsRepository.save(transaction);
                    return "Transaction Saved";
                }
                 return "Transaction Already Exists";
            }
            throw new EtBadRequestException("Transacton cannot be null");
        }catch (Exception e){
            throw new EtBadRequestException("An Error has occurred");
        }
    }

    @Override
    public String updateTransaction(Transaction transaction) throws EtBadRequestException {
        return null;
    }

    @Override
    public String removeTransaction(Integer userId, Integer categoryId, Integer transactionId) throws EtResourceNotFoundException {
        return null;
    }
}
