package com.learningjava.expensetrackerapi.controller;


import com.learningjava.expensetrackerapi.entity.Category;
import com.learningjava.expensetrackerapi.entity.Transaction;
import com.learningjava.expensetrackerapi.services.TransactionService;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transactions/{categoryId}")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @GetMapping("/getTransactions")
    public ResponseEntity<Map<String, Var>> GetTransactions(HttpServletRequest request, @PathVariable Integer categoryId, @RequestParam Integer userId ) {
        var transactionList= transactionService.fetchAllTransactions(userId, categoryId);
        if (transactionList != null){
            Map<String, List> map = new HashMap<>();
            map.put("Result", transactionList);
            return new ResponseEntity(map, HttpStatus.OK);
        }
        Map<String, String> map = new HashMap<>();
        map.put("Result", "Category has no transaction");
        return new ResponseEntity(map, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addTransaction")
    public ResponseEntity<Map<String, Var>> AddTransactions(@Valid @RequestBody Transaction transaction){
        var response = transactionService.addTransaction(transaction);
        if (response == "Transaction Saved"){
            Map<String, String> map = new HashMap<>();
            map.put("Result", response);
            return new ResponseEntity(map, HttpStatus.CREATED);
        }
        Map<String, String> map = new HashMap<>();
        map.put("Result", response);
        return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getTransaction/{userId}")
    public ResponseEntity<Map<String, Var>> GetTransactions(HttpServletRequest request,
            @PathVariable Integer categoryId, @PathVariable Integer userId, @RequestParam Integer transactionId) {
        var transaction= transactionService.fetchTransactionById(userId, categoryId, transactionId);
        if (transaction != null){
            Map<String, Transaction> map = new HashMap<>();
            map.put("Result", transaction);
            return new ResponseEntity(map, HttpStatus.OK);
        }
        Map<String, String> map = new HashMap<>();
        map.put("Result", "Transaction not found");
        return new ResponseEntity(map, HttpStatus.NOT_FOUND);
    }

}
