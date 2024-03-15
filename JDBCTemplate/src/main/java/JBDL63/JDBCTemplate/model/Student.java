package JBDL63.JDBCTemplate.model;

import jakarta.validation.constraints.DecimalMin;
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

    private String name;

    private String department;

    @DecimalMin(value="60",message = "Minimum marks required is 60")
    private Double marks;

}
