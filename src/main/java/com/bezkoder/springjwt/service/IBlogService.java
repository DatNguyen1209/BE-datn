package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.dto.BlogDTO;
import com.bezkoder.springjwt.dto.HotelDTO;
import com.bezkoder.springjwt.dto.PageDTO;

public interface IBlogService {
    void save(BlogDTO dto);
    BlogDTO update(BlogDTO dto , Long id);
    PageDTO<BlogDTO> findAllWithPageable(int page, int size);
    void delete (Long id);
}
