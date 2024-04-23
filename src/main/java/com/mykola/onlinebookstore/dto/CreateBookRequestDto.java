package com.mykola.onlinebookstore.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class CreateBookRequestDto {
    @NotNull(message = "Title cannot be null")
    @NotBlank(message = "Title cannot be empty")
    private String title;
    @NotNull(message = "Author cannot be null")
    @NotBlank(message = "Author cannot be empty")
    private String author;
    @NotNull(message = "ISBN cannot be null")
    @NotBlank(message = "ISBN cannot be empty")
    private String isbn;
    @NotNull(message = "Price cannot be null")
    @Min(0)
    private BigDecimal price;
    private String description;
    private String coverImage;
}
