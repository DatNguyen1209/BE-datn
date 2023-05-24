package com.bezkoder.springjwt.service;


import com.bezkoder.springjwt.dto.FeedbackDTO;
import com.bezkoder.springjwt.dto.PageDTO;
import com.bezkoder.springjwt.dto.RoomDTO;

public interface FeedbackService {
    void save(FeedbackDTO dto);
    PageDTO<FeedbackDTO> findAllWithPageable(int page, int size);
}
