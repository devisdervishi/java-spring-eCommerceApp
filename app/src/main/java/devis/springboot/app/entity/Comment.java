package devis.springboot.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Entity
@Data
@Builder
@Component
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @SequenceGenerator(name = "comment_id_seq",
    sequenceName = "comment_id_seq",
    allocationSize = 1)
    @Id
    @GeneratedValue(generator = "comment_id_seq",strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL,
    fetch = FetchType.EAGER,
    optional = false)
    @JoinColumn(name = "member_id",referencedColumnName = "id")
    private Member member;
    @ManyToOne(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            optional = false)
    @JoinColumn(name = "product_id",
    referencedColumnName = "id")
    private Product product;
    private String message;
}
