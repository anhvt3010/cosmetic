package com.anhvt.cosmetic.Controller;

import com.anhvt.cosmetic.DTO.FeedbackDTO;
import com.anhvt.cosmetic.Entity.Feedback;
import com.anhvt.cosmetic.Service.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static com.anhvt.cosmetic.Mapper.FeedbackConverter.*;

@Controller
@RequestMapping("/admin/feedbacks")
public class FeedbackController {
    @Autowired
    private FeedBackService feedBackService;

    @RequestMapping("/list")
    public ModelAndView getAll(@RequestParam(defaultValue = "0") int page){
        Pageable pageable = PageRequest.of(page, 2, Sort.by("created"));
        ModelAndView modelAndView = new ModelAndView("admin/feedback/list");
        Page<Feedback> feedbacks = feedBackService.findAll(pageable);
        Iterable<FeedbackDTO> feedbackDTOS = convertToFeedbackDTOs(feedbacks);
        modelAndView.addObject("feedbacks", feedbackDTOS);
        modelAndView.addObject("currentPage", page);
        modelAndView.addObject("totalPages", feedbacks.getTotalPages());
        return modelAndView;
    }

}
