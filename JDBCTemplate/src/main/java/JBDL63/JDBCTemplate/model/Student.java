package JBDL63.JDBCTemplate.model;

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

    private Integer marks;

}
