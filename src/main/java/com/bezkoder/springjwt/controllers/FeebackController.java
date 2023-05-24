package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.dto.FeedbackDTO;
import com.bezkoder.springjwt.dto.PageDTO;
import com.bezkoder.springjwt.repository.FeedbackRepository;
import com.bezkoder.springjwt.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/feedback")
public class FeebackController {
    @Autowired
    FeedbackRepository feedbackRepository;
    @Autowired
    FeedbackService feedbackService;
    @PostMapping("/create")
    public void Create(@RequestBody FeedbackDTO dto){
        feedbackService.save(dto);
    }
    @CrossOrigin
    @GetMapping("/getallhotel")
    public PageDTO<FeedbackDTO> getAllHotel(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ){
        return feedbackService.findAllWithPageable(page,size);
    }
}
