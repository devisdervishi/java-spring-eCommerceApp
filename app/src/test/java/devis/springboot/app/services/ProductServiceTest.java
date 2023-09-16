package devis.springboot.app.services;

import devis.springboot.app.entity.Product;
import devis.springboot.app.exceptions.EntityNotFoundException;
import devis.springboot.app.repositories.ProductRepository;
import devis.springboot.app.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    ProductService productService;

    @MockBean
    ProductRepository repository;

    @BeforeEach
    void setUp() {
        Product product = Product.builder()
                .name("table")
                .description("big table")
                .price(50.00).stock(5).build();
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(product));
    }

    @Test
    void getProduct() throws EntityNotFoundException {
        Optional<Product> product = Optional.of(productService.getProduct(1L));
        assertEquals(product.get().getName(), "table");
    }
}
