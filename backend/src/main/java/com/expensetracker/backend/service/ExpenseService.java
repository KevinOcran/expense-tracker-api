package com.expensetracker.backend.service;

import com.expensetracker.backend.dto.request.CreateExpenseRequest;
import com.expensetracker.backend.dto.request.UpdateExpenseRequest;
import com.expensetracker.backend.dto.response.ExpenseResponse;
import org.springframework.data.domain.Page;

import java.util.UUID;


public interface ExpenseService {
     ExpenseResponse createExpense(CreateExpenseRequest request);

     Page<ExpenseResponse> getExpensesByUser(Long userId, int page, int size);

     ExpenseResponse updateExpense(Long Id, UpdateExpenseRequest request);

     ExpenseResponse getExpenseById(Long id);

     void deleteExpense(Long id);
}
