package JBDL63.JDBCTemplate.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Builder
public class Student {

    private Integer id;

    @NotBlank(message="Student name should not be null")
    private String name;

    @Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}", message = "PAN No is not validated")
    private String department;

    @DecimalMin(value="60",message = "Minimum marks required is 60")
    private Double marks;

}
