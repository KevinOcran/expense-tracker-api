package com.expensetracker.backend.service.implementation;


import com.expensetracker.backend.dto.request.CreateExpenseRequest;
import com.expensetracker.backend.dto.request.UpdateExpenseRequest;
import com.expensetracker.backend.dto.response.ExpenseResponse;
import com.expensetracker.backend.entity.Category;
import com.expensetracker.backend.entity.Expense;
import com.expensetracker.backend.entity.User;
import com.expensetracker.backend.exception.ResourceNotFoundException;
import com.expensetracker.backend.repository.CategoryRepo;
import com.expensetracker.backend.repository.ExpenseRepo;
import com.expensetracker.backend.repository.UserRepo;
import com.expensetracker.backend.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor // (Lombok to inject final fields automatically)
public class ExpenseServiceImplementation implements ExpenseService {
    private final ExpenseRepo expenseRepository;
    private final CategoryRepo categoryRepository;
    private final UserRepo userRepository;


    private Expense fromDto(CreateExpenseRequest request) {
        return Expense.builder()
                .user(userRepository.findById(request.getUserId())
                        .orElseThrow(() -> new ResourceNotFoundException("User not found")))
                .category(categoryRepository.findById(request.getCategoryId())
                        .orElseThrow(() -> new ResourceNotFoundException("Category not found")))
                .amount(request.getAmount())
                .description(request.getDescription())
                .date(request.getDate())
                .build();
    }

    private void updateFromDto(Expense expense, UpdateExpenseRequest request){

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        expense.setUser(user);

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        expense.setCategory(category);
        expense.setAmount(request.getAmount());
        expense.setDescription(request.getDescription());
        expense.setDate(request.getDate());
    }


    private ExpenseResponse toDto(Expense expense){

        return ExpenseResponse.builder()
                                        .id(expense.getId())
                                        .categoryName(expense.getCategory().getName())
                                        .expenseAmount(expense.getAmount())
                                        .description(expense.getDescription())
                                        .date(expense.getDate())
                                        .build();

    }

    @Override
    public ExpenseResponse createExpense(CreateExpenseRequest request) {
        Expense expense = fromDto(request);


        Expense savedExpense = expenseRepository.save(expense);


        return toDto(savedExpense);
    }

    @Override
    public Page<ExpenseResponse> getExpensesByUser(Long userId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        Page<Expense> expensePage = expenseRepository.findByUserId(userId, pageRequest);

        return expensePage.map(this::toDto);

    }

    @Override
    public ExpenseResponse updateExpense(Long id, UpdateExpenseRequest request) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found"));

        updateFromDto(expense, request);

        Expense updatedExpense = expenseRepository.save(expense);

        return toDto(updatedExpense);
    }

    @Override
    public void deleteExpense(Long id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found"));
        expenseRepository.delete(expense);
    }

    @Override
    public ExpenseResponse getExpenseById(Long id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found"));

        return toDto(expense);
    }
}

