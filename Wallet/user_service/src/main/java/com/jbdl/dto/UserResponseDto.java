package com.jbdl.dto;

import com.jbdl.model.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserResponseDto {

    String message;

    User user;
}
