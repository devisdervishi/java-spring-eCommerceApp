package devis.springboot.app.repositories;

import devis.springboot.app.entity.Comment;
import devis.springboot.app.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Long> {

    @Query(value = "select r from Rating r where r.product.id=?1")
     List<Rating> getRatingsOfAProduct(Long productId);
}


