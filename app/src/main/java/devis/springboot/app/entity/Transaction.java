package devis.springboot.app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Entity
@Component
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @SequenceGenerator(name = "transaction_id_seq",sequenceName = "transaction_id_seq",
    allocationSize = 1)
    @Id
    @GeneratedValue(generator = "transaction_id_seq",strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER,
    optional = false)
    @JoinColumn(name = "member_id",referencedColumnName = "id"
    )
    private Member member;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER,
            optional = false)
    @JoinColumn(name = "product_id",referencedColumnName = "id"
    )
    private Product product;
    private int quantity;
    private String ApprovalStatus;


}
