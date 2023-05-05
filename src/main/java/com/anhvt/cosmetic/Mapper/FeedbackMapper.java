package com.anhvt.cosmetic.Mapper;

import com.anhvt.cosmetic.DTO.FeedbackDTO;
import com.anhvt.cosmetic.Entity.Feedback;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.anhvt.cosmetic.Utils.ConvertDate.TimestampToDate;

public class FeedbackMapper {
    public static FeedbackDTO convertToFeedbackDTO(Feedback feedback){
        FeedbackDTO feedbackDTO = new FeedbackDTO();
        feedbackDTO.setId(feedback.getId());
        feedbackDTO.setName(feedback.getName());
        feedbackDTO.setContent(feedback.getContent());
        feedbackDTO.setPhone(feedback.getPhone());
        feedbackDTO.setEmail(feedback.getEmail());
        feedbackDTO.setCreated_at(TimestampToDate(feedback.getCreated()));
        return feedbackDTO;
    }

    public static Iterable<FeedbackDTO> convertToFeedbackDTOs(Iterable<Feedback> feedbacks) {
        return StreamSupport.stream(feedbacks.spliterator(), false)
                .map(FeedbackMapper::convertToFeedbackDTO)
                .collect(Collectors.toList());
    }
}
