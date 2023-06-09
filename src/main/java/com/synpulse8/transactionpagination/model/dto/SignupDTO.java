package com.synpulse8.transactionpagination.model.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class SignupDTO {

    @NotBlank
    @Size(min = 3, max = 30)
    private String username;

    private String name;

    @NotBlank
    @Size(max = 60)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 60)
    private String password;

    private Set<String> accountIban;
}