package devis.springboot.app.services;

import devis.springboot.app.entity.Comment;
import devis.springboot.app.exceptions.EntityNotFoundException;
import devis.springboot.app.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository repository;

    public Comment saveComment(Comment comment){
        return repository.save(comment);
    }
    @Modifying
    @Transactional
    public Comment updateComment(String message,Long comment_id) throws EntityNotFoundException {
        Optional<Comment> commentToBeUpdated=repository.findById(comment_id);
        if (commentToBeUpdated.isEmpty()) throw new EntityNotFoundException("Comment doesn't exist");

         else commentToBeUpdated.get().setMessage(message);
         return repository.save(commentToBeUpdated.get());
    }
    @Modifying
    @Transactional
    public String deleteComment(Long id) throws EntityNotFoundException {
        Optional<Comment> comment=repository.findById(id);
        if (comment.isEmpty())throw new EntityNotFoundException("Comment doesn't exist");
        else repository.deleteById(id);
        return "Success";
    }
    public Comment getComment(Long id) throws EntityNotFoundException {
        Optional<Comment> comment=repository.findById(id);
        if (comment.isEmpty()) throw new EntityNotFoundException("Comment doesn't exist");
         return comment.get();
    }
    public List<Comment> getCommentsOfProduct(Long product_id){
        return repository.getCommentsOfAProduct(product_id);
    }

}
