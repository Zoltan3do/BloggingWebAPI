package com.zoltan.bloggingwebapi.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record LoginDTO(
        @NotEmpty
        @Email
        String email,
        @NotEmpty
        String password
) {
}
