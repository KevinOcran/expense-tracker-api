package com.expensetracker.backend.dto.response;


import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseResponse {
    private Long id;

    private BigDecimal expenseAmount;

    private String description;

    private LocalDateTime date;

    private String categoryName;
}
