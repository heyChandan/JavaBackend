package com.jbdl63.library.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateAuthorDto {
    @NotNull(message = "Id must not be null")
    private Integer authorId;
    @NotBlank(message = "Address must not be blank or null")
    private String address;
}
