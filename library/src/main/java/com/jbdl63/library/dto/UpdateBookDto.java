package com.jbdl63.library.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateBookDto {

    @NotNull(message = "Name should not be null")
    String bookName;
    @NotEmpty(message = "Price must not be null or empty")
    Float bookPrice;
}
