package devis.springboot.app.services;

import devis.springboot.app.entity.Product;
import devis.springboot.app.exceptions.EntityNotFoundException;
import devis.springboot.app.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public Product saveProduct(Product product){
        return repository.save(product);
    }
    public Product getProduct(Long id) throws EntityNotFoundException {
        Optional<Product> product= repository.findById(id);
        if (product.isEmpty()) throw new EntityNotFoundException("Product doesn't exist");
        return product.get();
    }

    @Modifying
    @Transactional
    public String deleteProduct(Long id) throws EntityNotFoundException {
        Optional<Product> product=repository.findById(id);
        if (product.isEmpty()) throw new EntityNotFoundException("Product doesn't exist");
        repository.deleteById(id);
        return "Success";
    }
    @Modifying
    @Transactional
    public Product updateProduct(Product product,Long id) throws EntityNotFoundException {
        Optional<Product> productToBeUpdated=repository.findById(id);
        if (productToBeUpdated.isEmpty()) throw new EntityNotFoundException("Product doesn't exist");
        if (product.getDescription()!=null&&product.getDescription()!=productToBeUpdated.get().getDescription()){
            productToBeUpdated.get().setDescription(product.getDescription());
        }
         Double price=(Double) product.getPrice();
        if ((price.equals(productToBeUpdated.get().getPrice()))){
            productToBeUpdated.get().setPrice(product.getPrice());
        }
        if (product.getName()!=null&&product.getName()!=productToBeUpdated.get().getName()){
            productToBeUpdated.get().setName(product.getName());
        }
        return repository.save(productToBeUpdated.get());
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }
}
