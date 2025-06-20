package com.expensetracker.backend.controller;


import com.expensetracker.backend.dto.request.CreateExpenseRequest;
import com.expensetracker.backend.dto.response.ExpenseResponse;
import com.expensetracker.backend.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;

import java.util.UUID;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<ExpenseResponse> createExpense(
            @Valid @RequestBody CreateExpenseRequest request
    ) {
        ExpenseResponse response = expenseService.createExpense(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }




    @GetMapping
    public ResponseEntity<Page<ExpenseResponse>> getExpensesForUser(
            @RequestParam Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Page<ExpenseResponse> response = expenseService.getExpensesByUser(userId, page, size);
        return ResponseEntity.ok(response);
    }


}
