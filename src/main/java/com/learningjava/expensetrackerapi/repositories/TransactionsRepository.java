package com.learningjava.expensetrackerapi.repositories;

import com.learningjava.expensetrackerapi.entity.Transaction;
import com.learningjava.expensetrackerapi.exceptions.EtResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionsRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findAllByUserIdAndCategoryId(Integer userId, Integer categoryId) throws
            EtResourceNotFoundException;

    Transaction findByUserIdAndCategoryIdAndTransactionId(Integer userId, Integer categoryId, Integer transactionId) throws
            EtResourceNotFoundException;

    String deleteByUserIdAndCategoryIdAndTransactionId(Integer userId, Integer categoryId, Integer transactionId) throws
            EtResourceNotFoundException;

    String deleteAllByUserIdAndCategoryId(Integer userId, Integer categoryId) throws
            EtResourceNotFoundException;

    Transaction findOneByUserIdAndCategoryIdAndNoteAndAmountAndTransactionDate(Integer userId, Integer categoryId, String note, double amount, long transactionDate) throws
            EtResourceNotFoundException;

}
