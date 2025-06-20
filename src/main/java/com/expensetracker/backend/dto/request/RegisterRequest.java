package com.expensetracker.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {
    @NotBlank(message = "Please enter your email")
    private String email;

    @NotBlank(message = "Please enter a password")
    @Size(min = 12, message = "Password must be 12 characters or more")
    private String password;

    @NotBlank(message ="Please enter your full name")
    private String fullName;
}
