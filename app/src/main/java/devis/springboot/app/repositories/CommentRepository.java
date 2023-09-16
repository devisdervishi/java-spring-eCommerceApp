package devis.springboot.app.repositories;

import devis.springboot.app.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
@Query(nativeQuery = true,value = "select * from comment as c where c.product_id=:p_id")
    public List<Comment> getCommentsOfAProduct(@Param("p_id") Long productId);
}
