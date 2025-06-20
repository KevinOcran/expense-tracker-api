package com.expensetracker.backend.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CreateExpenseRequest {
    @NotNull(message ="Amount is required")
    @DecimalMin(value="0.1" ,inclusive=true, message="Amount must be greater than 0.09")
    private BigDecimal amount;


    @NotBlank(message = "Description is required") // ensures it’s not null/empty/whitespace
    @Size(max = 225, message = "Description must be less than 225 characters")
    private String description;

    @NotNull(message="You must enter a date")
    private LocalDateTime date;

    @NotNull(message="Category id is required")
    private Long categoryId;

    @NotNull
    private Long userId;
}
