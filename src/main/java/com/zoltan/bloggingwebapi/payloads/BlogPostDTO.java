package com.zoltan.bloggingwebapi.payloads;

import com.zoltan.bloggingwebapi.entities.User;
import com.zoltan.bloggingwebapi.entities.enums.PostCategories;
import com.zoltan.bloggingwebapi.payloads.validationGroups.Create;
import jakarta.validation.constraints.*;

public record BlogPostDTO(
        @NotNull(message = "Deve avere una categoria", groups = Create.class)
        PostCategories category,
        @NotEmpty(message = "Deve avere un titolo", groups = Create.class)
        @Size(min = 3, max = 16, message = "La lunghezza deve essere compresa tra 3 e 16 caratteri")
        String title,
        @NotEmpty(message = "Deve avere un contenuto", groups = Create.class)
        @Size(min = 16, max = 150, message = "La lunghezza del contenuto deve essere tra i 16 e i 255 caratteri")
        String content
) {


}
