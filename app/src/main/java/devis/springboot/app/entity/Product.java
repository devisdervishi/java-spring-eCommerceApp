package devis.springboot.app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.stereotype.Component;

@Entity
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @SequenceGenerator(name = "product_id_sequence",
    allocationSize = 1,
    sequenceName = "product_id_sequence")
    @Id
    @GeneratedValue(generator = "product_id_sequence",
    strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotNull
    private String name;
    private String description;
    @NotNull
    private String type;
    private int stock;
    private double price;
    private String image_source;


}
