package JPAImpl1.JPAImpl1.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adId;

    private Integer pin;

    private String city;

    @OneToOne
    @JoinColumn(name="studId")
    private Student student;
}
