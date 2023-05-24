package com.bezkoder.springjwt.serviceimpl;

import com.bezkoder.springjwt.converter.FeedbackConverter;
import com.bezkoder.springjwt.dto.FeedbackDTO;
import com.bezkoder.springjwt.dto.PageDTO;
import com.bezkoder.springjwt.models.Feedback;
import com.bezkoder.springjwt.models.Room;
import com.bezkoder.springjwt.repository.FeedbackRepository;
import com.bezkoder.springjwt.repository.HotelRepository;
import com.bezkoder.springjwt.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class FeedbackImpl implements FeedbackService {
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    FeedbackRepository feedbackRepository;
    @Autowired
    FeedbackConverter converter;

    @Override
    public void save(FeedbackDTO dto) {
        try{
            Feedback entity = null;
            entity.setId(dto.getId());
            entity.setContent(dto.getContent());
            entity.setRating(dto.getRating());
            entity.setHotelId(hotelRepository.getReferenceById(dto.getHotelId()));
            entity.setImage(dto.getImage());
            feedbackRepository.save(entity);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public PageDTO<FeedbackDTO> findAllWithPageable(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("id").descending());
        Page<Feedback> feedbacksList = feedbackRepository.findAll(pageable);

        return PageDTO.of(feedbacksList, feedbacksList.stream().map(feedback -> converter.toDTO(feedback)).collect(Collectors.toList()));
    }
}
