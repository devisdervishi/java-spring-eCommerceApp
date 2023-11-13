package devis.springboot.app.controllers;

import devis.springboot.app.entity.Comment;
import devis.springboot.app.entity.Product;
import devis.springboot.app.entity.Rating;
import devis.springboot.app.exceptions.EntityNotFoundException;
import devis.springboot.app.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("app/user")
public class NormalMemberController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CommentService commentService;
    @Autowired
    CartService cartService;
    @Autowired
    TransactionService transactionService;
    @Autowired
    RatingService ratingService;

    @GetMapping("/dashboard")
    public String userDashboard(){
        return "hello user";
    }
    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }
    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable("id") Long id) throws EntityNotFoundException {
        return productService.getProduct(id);
    }

    @GetMapping("/products/{id}/comments")
    public List<Comment> getAllCommentsOfaProduct(@PathVariable("id")Long product_id){
        return commentService.getCommentsOfProduct(product_id);
    }
    @GetMapping("products/{id}/ratings")
    public List<Rating> getAllRatingsOfaProduct(@PathVariable("id") Long product_id){
        return ratingService.getAllRatingsOfaProduct(product_id);
    }


}
