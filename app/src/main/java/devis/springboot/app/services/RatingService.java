package devis.springboot.app.services;

import devis.springboot.app.entity.Rating;
import devis.springboot.app.exceptions.EntityNotFoundException;
import devis.springboot.app.repositories.RatingRepository;
import org.apache.tomcat.util.http.fileupload.util.LimitedInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public Rating saveRating(Rating rating){
        return ratingRepository.save(rating);
    }

    public Rating getRating(Long id) throws EntityNotFoundException {
        Optional<Rating> rating= ratingRepository.findById(id);
        if (rating.isEmpty()) throw new EntityNotFoundException("Rating doesn't exist");
        return rating.get();
    }
    @Modifying
    @Transactional
    public Rating updateRating(int value,Long id) throws EntityNotFoundException {
        Optional<Rating> rating=ratingRepository.findById(id);
        if (rating.isEmpty()) throw new EntityNotFoundException("Rating doesn't exist");
        rating.get().setValue(value);
        return ratingRepository.save(rating.get());
    }
    @Modifying
    @Transactional
    public String deleteRating(Long id) throws EntityNotFoundException {
        Optional<Rating> rating=ratingRepository.findById(id);
        if (rating.isEmpty()) throw new EntityNotFoundException("Rating doesn't exist");
        ratingRepository.deleteById(id);
        return "Success";
    }

    public List<Rating> getAllRatingsOfaProduct(Long product_id){
        return ratingRepository.getRatingsOfAProduct(product_id);
    }
}
