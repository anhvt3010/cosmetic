package com.anhvt.cosmetic.Service;

import com.anhvt.cosmetic.Entity.Feedback;
import com.anhvt.cosmetic.Repository.FeedBackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedBackService implements IService<Feedback> {
    private final FeedBackRepository feedBackRepository;
    @Autowired
    public FeedBackService(FeedBackRepository feedBackRepository) {
        this.feedBackRepository = feedBackRepository;
    }

    public Iterable<Feedback> findAll(){
        return feedBackRepository.findAll();
    }

    @Override
    public void save(Feedback feedback){
        feedBackRepository.save(feedback);
    }
}
