package devis.springboot.app.repositories;

import devis.springboot.app.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    ProductRepository repository;
    @Autowired
    TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {
        Product product= Product.builder()
                .name("table")
                .type("furniture")
                .build();
        testEntityManager.persist(product);
    }
    @Test
    @DisplayName(("when right id is given to repository right data is returned"))
    void whenRightIdGivenRightDataReturned(){
        Optional<Product> product=repository.findById(1l);
        assertEquals(product.get().getName(),"table");
        assertEquals(product.get().getType(),"furniture");
    }
@Test
@DisplayName("When entity is given to repository it gets saved successfully")
    void saveEntityTest(){
    repository.save(Product.builder().name("chair").type("furniture").build());
}




}