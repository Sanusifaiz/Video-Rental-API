package com.learningjava.expensetrackerapi.services;

import com.learningjava.expensetrackerapi.entity.Transaction;
import com.learningjava.expensetrackerapi.exceptions.EtBadRequestException;
import com.learningjava.expensetrackerapi.exceptions.EtResourceNotFoundException;

import java.util.List;

public interface TransactionService {
    List<Transaction> fetchAllTransactions(Integer userId, Integer categoryId);

    Transaction fetchTransactionById(Integer userId, Integer categoryId, Integer transactionId) throws
            EtResourceNotFoundException;

    String addTransaction(Transaction transaction) throws
            EtBadRequestException;

    String updateTransaction(Transaction transaction) throws
            EtBadRequestException;

    String removeTransaction(Integer userId, Integer categoryId, Integer transactionId) throws
            EtResourceNotFoundException;
}