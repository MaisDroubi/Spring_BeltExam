package com.beltexam.beltexam.services;

import com.beltexam.beltexam.models.Review;
import com.beltexam.beltexam.models.Show;
import com.beltexam.beltexam.repositories.ReviewRepository;
import com.beltexam.beltexam.repositories.ShowRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ShowService {
    private final ShowRepository showRepository;
    private final ReviewRepository reviewRepository;


    public ShowService(ShowRepository showRepository, ReviewRepository reviewRepository) {
        this.showRepository = showRepository;
        this.reviewRepository = reviewRepository;
    }

    public Show createShow(Show myShow) {
        return showRepository.save(myShow);
    }
    public List<Show> getAll() {
        return (List<Show>) showRepository.findAll();
    }
    public Show findShow(Long id) {
        Optional<Show> myshow = showRepository.findById(id);
        if (myshow.isPresent()) {
            return myshow.get();
        }else {
            System.out.println("There is no TV show you are looking for");
            return null;
        }
    }
    public void deleteShow(Long myId) {
        showRepository.deleteById(myId);
    }
    public void updateShow(Show myShow) {
        showRepository.save(myShow);
    }

    public Review getReview(Long id) {
        Optional<Review> optionalReview = reviewRepository.findById(id);
        return optionalReview.isPresent() ? optionalReview.get() : null;
    }
    public Review create(Review newReview) {
        List<Review> matchingReviews = reviewRepository.matchingReviews(
                newReview.getUser().getId(),
                newReview.getShow().getId());
        if(matchingReviews.size() > 0) {
            return null;
        }
        newReview.setId(null);
        return reviewRepository.save(newReview);
    }
    public List<Review> getReviews() {
        return (List<Review>) reviewRepository.findAll();
    }

}