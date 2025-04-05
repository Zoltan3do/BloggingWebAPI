package com.zoltan.bloggingwebapi.payloads;

import com.zoltan.bloggingwebapi.payloads.validationGroups.Create;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record UserDTO(
        @NotEmpty(message = "Il nome è obbligatorio", groups = Create.class)
        @Size(min = 3, max = 16, message = "Il nome deve essere compreso tra 3 e 16 caratteri!")
        String name,
        @NotEmpty(message = "Il cognome è obbligatorio", groups = Create.class)
        @Size(min = 3, max = 16, message = "Il cognome deve essere compreso tra 3 e 16 caratteri!")
        String surname,
        @NotEmpty(message = "La mail è obbligatoria", groups = Create.class)
        @Email(message = "L'email inserita non è valida")
        @Size(min = 6, message = "La mail deve contenere almeno 6 caratteri")
        String email,
        @NotNull(message = "La data di nascita è obbligatoria", groups = Create.class)
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate birthday,
        @NotEmpty(message = "La password è obbligatoria", groups = Create.class)
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d)(?=.*[@$!%*?&])[A-Za-z\\\\d@$!%*?&]{8,}$", message = "La password non segue i criteri comuni")
        String password
) {
}
