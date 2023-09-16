package devis.springboot.app.services;

import devis.springboot.app.entity.Cart;
import devis.springboot.app.exceptions.EntityNotFoundException;
import devis.springboot.app.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository repository;

    public Cart saveCart(Cart cart){
        return repository.save(cart);
    }

    public Cart getCart(Long id) throws EntityNotFoundException {
        Optional<Cart> cart =repository.findById(id);
        if (cart.isEmpty()) throw new EntityNotFoundException("Cart doesn't exist");
        return cart.get();
    }
    @Modifying
    @Transactional
    public String deleteCart(Long id) throws EntityNotFoundException {
        Optional<Cart> cart=repository.findById(id);
        if (cart.isEmpty()) throw new EntityNotFoundException("Cart doesn't exist");
        repository.deleteById(id);
        return "Success";
    }
}
