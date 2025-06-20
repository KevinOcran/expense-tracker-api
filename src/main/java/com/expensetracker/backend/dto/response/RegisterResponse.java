package com.expensetracker.backend.dto.response;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterResponse {
    private String email;

    private Long id;

    private String message;

    private String timeStamp;
}
