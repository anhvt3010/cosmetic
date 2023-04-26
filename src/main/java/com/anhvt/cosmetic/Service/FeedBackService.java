package com.anhvt.cosmetic.Service;

import com.anhvt.cosmetic.Entity.Feedback;
import com.anhvt.cosmetic.Repository.FeedBackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class FeedBackService implements IService<Feedback> {
    private final FeedBackRepository feedBackRepository;
    @Autowired
    public FeedBackService(FeedBackRepository feedBackRepository) {
        this.feedBackRepository = feedBackRepository;
    }

    public Page<Feedback> findAll(Pageable pageable){
        return feedBackRepository.findAll(pageable);
    }

    @Override
    public void save(Feedback feedback){
        feedBackRepository.save(feedback);
    }

    public Iterable<Feedback> findAll(Integer pageNo, Integer pageSize, String sort){
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sort));
        Page<Feedback> feedbackPage = feedBackRepository.findAll(paging);
        if(feedbackPage.hasContent()) {
            return feedbackPage.getContent();
        } else {
            return null;
        }
    }
}
