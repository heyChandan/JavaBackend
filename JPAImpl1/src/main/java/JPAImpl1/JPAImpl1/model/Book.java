package JPAImpl1.JPAImpl1.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="books" ,
        uniqueConstraints =  {
            @UniqueConstraint(name = "bookName_Unique",
                columnNames = "bookName"
            )
        }
)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookId_Generator")
    @SequenceGenerator(name = "bookId_Generator", sequenceName = "bookId_sequence_table", allocationSize = 1, initialValue = 1)
    private int bookId;

    private String bookName;

    @Column(unique = true)
    private String author;

    @Column(name="year",unique = true)
    private String publicationYear;

    @CreationTimestamp
    private LocalDateTime insertTime;

    @UpdateTimestamp
    private LocalDateTime updationTime;
}
