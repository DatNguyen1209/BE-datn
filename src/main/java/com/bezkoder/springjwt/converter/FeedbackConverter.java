package com.bezkoder.springjwt.converter;

import com.bezkoder.springjwt.dto.FeedbackDTO;
import com.bezkoder.springjwt.models.Feedback;
import org.springframework.stereotype.Component;

@Component
public class FeedbackConverter {
    public FeedbackDTO toDTO(Feedback entity){
        FeedbackDTO dto = new FeedbackDTO();
        if(entity.getId() != null){
            dto.setId(entity.getId());
        }
        dto.setContent(entity.getContent());
        dto.setRating(entity.getRating());
        dto.setImage(entity.getImage());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setHotelId(entity.getHotelId().getId());
        return dto;
    }
}
