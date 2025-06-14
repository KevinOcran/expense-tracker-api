package com.expensetracker.backend.dto.response;


import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime date;

    private String categoryName;
}
